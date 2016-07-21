package appCars.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import appCars.Manager.ReservationManagerDB;
import appCars.Manager.TrajetManagerDB;
import appCars.Model.Reservation;
import appCars.Model.Trajet;
import appCars.Model.User;

@Controller
public class TrajetController {
 
	private TrajetManagerDB manager = new TrajetManagerDB();
	private ReservationManagerDB managerReservation = new ReservationManagerDB();
	
	@RequestMapping(value = "/trajet", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request) {
		
	  ModelAndView model = new ModelAndView();
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") == null){
		  return new ModelAndView("redirect:/home");
	  }
	  User user = (User)session.getAttribute("user");
	  
	  List<Trajet> trajets = manager.GetTrajetsDispo(user.getId());
	  model.addObject("trajets", trajets);
	  
	  List<Reservation> reservations = managerReservation.GetReservationPerso(user.getId());
	  model.addObject("reservations", reservations);
	  
	  String error = request.getParameter("error"); 
	  if(error != null){
		  model.addObject("error", error);
	  }
	  String msg = request.getParameter("msg"); 
	  if(msg != null){
		  model.addObject("msg", msg);
	  }
	  
	  model.setViewName("trajet.index");

	  return model;

	}
	
	@RequestMapping(value = "/trajetcreate", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request) {
		
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") == null){
		  return new ModelAndView("redirect:/home");
	  }

	  return new ModelAndView("trajet.create");
	}
	
	@RequestMapping(value = "/trajetcreate", method = RequestMethod.POST)
	public ModelAndView create(@Valid Trajet trajet, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request)
	{	
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") == null){
		  return new ModelAndView("redirect:/home");
	  }
	  ModelAndView model = new ModelAndView("trajet.create");
	  User user = (User)session.getAttribute("user");
	  
	  if (bindingResult.hasErrors()) {
		  model.addObject("error", "Champs non valide.");
          return model;
      }
	  trajet.setStatus(1);
	  trajet.setUser_id(user.getId());
	  
	  if(manager.createTrajet(trajet)){
		  return model.addObject("msg","Trajet créé");
	  }

	  return model;

	}
}