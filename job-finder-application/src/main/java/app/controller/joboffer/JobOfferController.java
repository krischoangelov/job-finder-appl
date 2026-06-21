package app.controller.joboffer;

import app.model.dto.joboffer.CreateJobOfferRequest;
import app.model.dto.joboffer.JobOfferDTO;
import app.service.joboffer.JobOfferService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/jobs")
public class JobOfferController {

    private JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping
    public ModelAndView getOffersPage() {

        List<JobOfferDTO> jobs = jobOfferService.getAllJobOffers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jobs");
        modelAndView.addObject("jobs", jobs);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getJobDetailsPage(@PathVariable UUID id) {
        JobOfferDTO job = jobOfferService.getJobOfferById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("details-job");
        modelAndView.addObject("job", job);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreateJobPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-job");
        modelAndView.addObject("jobOfferRequest", new CreateJobOfferRequest());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createJobOffer(
            @ModelAttribute("jobOfferRequest") CreateJobOfferRequest createJobOfferRequest,
            HttpSession session) {

        UUID recruiterId = (UUID) session.getAttribute("user_id");

        jobOfferService.createJobOffer(recruiterId, createJobOfferRequest);

        return new ModelAndView("redirect:/jobs");
    }
}
