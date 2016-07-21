package appCars.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import appCars.Manager.TrajetManagerDB;
import appCars.Manager.ReservationManagerDB;
import appCars.Model.Reservation;
import appCars.Model.User;
import ch.qos.logback.core.pattern.Converter;

@Controller
public class ReservationController {
 
	private TrajetManagerDB managerTrajet = new TrajetManagerDB();
	private ReservationManagerDB managerReservation = new ReservationManagerDB();
	
	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public ModelAndView create(@Valid Reservation reservation, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request)
	{	
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") == null){
		  return new ModelAndView("redirect:/home");
	  }
	  ModelAndView model = new ModelAndView("redirect:/trajet");
	  User user = (User)session.getAttribute("user");
	  
	  if (bindingResult.hasErrors()) {
		  return model.addObject("error", "Champs non valide.");
      }
	  reservation.setUser_id(user.getId());
	  int placerestantes = managerTrajet.TrajetPlaces(reservation.getTrajet_id());
	  if(placerestantes >= reservation.getPlace()){
		  if(managerReservation.createReservation(reservation)){
			  return model.addObject("msg", "Reservation effectuee avec succes.");
		  }
		  return model.addObject("error", "La reservation n'a pas ete pris en compte.");
	  }
	  
	  if(placerestantes == 0){
		  return model.addObject("error", "Desole..plus de place disponible");
	  }
	  
	  return model.addObject("error", "Desole, place(s) disponibles : " + placerestantes );
	  
	}
	
	@RequestMapping(value = "/reservationdelete", method = RequestMethod.GET)
	public ModelAndView createdelete(HttpServletRequest request)
	{	
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") == null){
		  return new ModelAndView("redirect:/home");
	  }
	  ModelAndView model = new ModelAndView("redirect:/trajet");
	  User user = (User)session.getAttribute("user");
	  String idS = request.getParameter("id");
	  
	  if(idS != null){
		  model.addObject("msg", idS);
		  int id = Integer.parseInt(idS);	
		  Reservation r = managerReservation.getReservation(id);
		  model.addObject("msg", r);
		  if(r != null){
			if(managerReservation.DeleteReservation(r.getId())){
			  	model.addObject("msg", "Reservation supprimee");
	  		}
			else{
				 model.addObject("error", "Erreur de suppression");
			}
		  }
		  return model;
	  }
	  model.addObject("error", "Reservation inconnue");
	  
	  return model;
	}	
}