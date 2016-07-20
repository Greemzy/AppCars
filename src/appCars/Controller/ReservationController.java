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
	  if(managerTrajet.TrajetPlaces(reservation.getTrajet_id()) >= reservation.getPlace()){
		  if(managerReservation.createReservation(reservation)){
			  return model.addObject("msg", "Reservation effectuee avec succes.");
		  }
		  return model.addObject("error", "La reservation n'a pas ete pris en compte.");
	  }
	  
	  return model.addObject("error", "Desole..plus de place disponible");
	}
}