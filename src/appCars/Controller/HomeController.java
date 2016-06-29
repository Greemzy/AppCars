package appCars.Controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import appCars.Manager.UserManagerDB;
import appCars.Model.User;

@Controller
public class HomeController {
 
	private UserManagerDB manager = new UserManagerDB();
    /*@RequestMapping(value = "/page1", method = RequestMethod.GET)
    public ModelAndView firstPage(Model model) {
        model.addAttribute("firstPageMessage", "This is the first page");
        return new ModelAndView("home");
    }
 
    @RequestMapping(value = "/page2", method = RequestMethod.GET)
    public ModelAndView secondPage(Model model) {
        model.addAttribute("secondPageMessage", "This is the second page");
        return new ModelAndView("home2");
    }*/
	
	/*@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This is default page!");
	  model.setViewName("hello");
	  return model;

	}*/

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("admin");
	  return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("user")User user, 
		      BindingResult result, ModelMap model, HttpServletRequest request) {
		 ModelAndView modelview = new ModelAndView();
		 if (result.hasErrors()) {
			 modelview.addObject("error", "There are errors");
		 }
		 //model.addAttribute("email", user.getEmail());
		 //model.addAttribute("password", user.getPassword());
		 User user2 = manager.getUser(user.getEmail());
		 if(user2 != null)
		 {
			 if(BCrypt.checkpw(user.getPassword(), user2.getPassword())){
				 HttpSession session = request.getSession();
				 session.setAttribute( "user", user2 );
				 modelview.setViewName("home");
			 }
			 else{
				 modelview.addObject("error", user.getPassword());
				 modelview.addObject("msg", "email :" + user2.getStringUser());
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
	public ModelAndView login() {
	
	  ModelAndView model = new ModelAndView();
	  model.setViewName("login");

	  return model;

	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		
	  ModelAndView model = new ModelAndView();
	  model.setViewName("register");

	  return model;

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user")User user) {
		
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
	  }
	  else{
		  model.addObject("error", "Email existant");
	  }
	  
	  
	  model.setViewName("register");

	  return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("403");
	  return model;

	}
}