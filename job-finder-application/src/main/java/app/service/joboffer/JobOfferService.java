package app.service.joboffer;

import app.repository.joboffer.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobOfferService {

    private JobOfferRepository jobOfferRepository;

    @Autowired
    public JobOfferService(JobOfferRepository jobOfferRepository ) {
        this.jobOfferRepository = jobOfferRepository;
    }
}
