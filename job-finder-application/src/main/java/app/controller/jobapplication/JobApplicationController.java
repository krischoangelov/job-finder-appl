package app.controller.jobapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/applications")
public class JobApplicationController {

    @GetMapping
    public ModelAndView getApplicationsPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jobs");
        return modelAndView;
    }
}
