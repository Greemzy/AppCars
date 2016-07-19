package appCars.Controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

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

import appCars.Manager.TrajetManagerDB;
import appCars.Model.Trajet;

@Controller
public class TrajetController {
 
	private TrajetManagerDB manager = new TrajetManagerDB();
	
	@RequestMapping(value = "/trajet", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request) {
		
	  ModelAndView model = new ModelAndView();
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") != null){
		  return new ModelAndView("redirect:/home");
	  }
	  List<Trajet> trajets = manager.GetTrajetsDispo();
	  model.addObject("trajets", trajets);
	  
	  model.setViewName("trajet.index");

	  return model;

	}

}