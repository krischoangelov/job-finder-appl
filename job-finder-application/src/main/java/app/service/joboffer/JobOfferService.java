package app.service.joboffer;

import app.model.entity.jobapplication.JobApplication;
import app.model.entity.joboffer.JobOffer;
import app.model.entity.user.User;
import app.model.enums.EmploymentType;
import app.model.enums.UserRole;
import app.repository.joboffer.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobOfferService {

    private JobOfferRepository jobOfferRepository;

    @Autowired
    public JobOfferService(JobOfferRepository jobOfferRepository ) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public JobOffer createJobOffer(String title, String company, String location, String description,
                                   BigDecimal salary, EmploymentType type, User recruiter, List<JobApplication> applicationList) {

        if (recruiter.getRole().equals(UserRole.CANDIDATE)) {
            throw new RuntimeException("Candidates cannot create job offers");
        }

        LocalDateTime start = LocalDateTime.now();

        JobOffer jobOffer = JobOffer.builder()
                .title(title)
                .company(company)
                .location(location)
                .description(description)
                .salary(salary)
                .type(type)
                .createdOn(start)
                .deadline(start.plusMonths(1))
                .recruiter(recruiter)
                .jobApplications(applicationList)
                .build();


        return jobOfferRepository.save(jobOffer);
    }

    public void deleteJobOffer(UUID jobOfferId) {
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).orElse(null);

        if (jobOffer == null) {
            throw new RuntimeException("No such job offer exists");
        }

//        jobOfferRepository.de


    }
}
