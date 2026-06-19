package app.controller.joboffer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jobs")
public class JobOfferController {

    @GetMapping
    public ModelAndView getJobsPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jobs");
        return modelAndView;
    }
}
