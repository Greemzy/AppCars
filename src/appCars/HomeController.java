package appCars;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
 
    @RequestMapping(value = "/page1", method = RequestMethod.GET)
    public ModelAndView firstPage(Model model) {
        model.addAttribute("firstPageMessage", "This is the first page");
        return new ModelAndView("home");
    }
 
    @RequestMapping(value = "/page2", method = RequestMethod.GET)
    public ModelAndView secondPage(Model model) {
        model.addAttribute("secondPageMessage", "This is the second page");
        return new ModelAndView("home2");
    }
}