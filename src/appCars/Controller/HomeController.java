package appCars.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import appCars.Manager.UserManagerDB;
import appCars.Model.User;

@Controller
public class HomeController {
 
	private UserManagerDB manager = new UserManagerDB();

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("user")User user, 
		      BindingResult result, ModelMap model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		  if(session.getAttribute("user") != null){
			  return new ModelAndView("redirect:/home");
		  }
		 ModelAndView modelview = new ModelAndView();
		 if (result.hasErrors()) {
			 modelview.addObject("error", "There are errors");
		 }
		 
		 if(manager.EmailExist(user.getEmail()))
		 {
			 User user2 = manager.getUser(user.getEmail());
			 if(BCrypt.checkpw(user.getPassword(), user2.getPassword())){
				 session.setAttribute( "user", user2 );
				 modelview.setViewName("home");
			 }
			 else{
				 modelview.addObject("error", "mot de passe incorrect");
				 modelview.setViewName("login");
			 } 
		 }
		 else
		 {
			 modelview.addObject("error", "Utilisateur inconnu");
			 modelview.setViewName("login");
		 } 
	  return modelview;
	}
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
	
	  ModelAndView model = new ModelAndView();
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") != null){
		  return new ModelAndView("redirect:/home");
	  }
	  else{
		  model.setViewName("login");
	  }
	  

	  return model;

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request) {
		
	  ModelAndView model = new ModelAndView();
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") != null){
		  return new ModelAndView("redirect:/home");
	  }
	  model.setViewName("register");

	  return model;

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null){
		  return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("home");
	}

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,@ModelAttribute("user")User user) {
	HttpSession session = request.getSession();
	  if(session.getAttribute("user") != null){
		  return new ModelAndView("redirect:/home");
	  }
		  
	  ModelAndView model = new ModelAndView();
	  model.setViewName("register");
	  if(user.getFirstname() ==  "" || user.getLastname() == "" || user.getEmail() == "" || user.getPassword() == ""){
		  model.addObject("error", "Champs non valide");
		  return model;
	  }
	  User newUser = new User(user.getFirstname(),user.getLastname(),user.getEmail(),user.getPassword());
	  if(!manager.EmailExist(user.getEmail())){
		  if(manager.createUser(newUser)){
			  model.addObject("msg", "Utilisateur créé");
		  }
		  else{
			  model.addObject("error", "une erreur est survenue");
		  }
	  }
	  else{
		  model.addObject("error", "Email existant");
	  }
	  model.setViewName("login");

	  return model;

	}
}