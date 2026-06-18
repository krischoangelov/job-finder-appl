package app.model.entity.jobapplication;

import app.model.entity.joboffer.JobOffer;
import app.model.entity.user.User;
import app.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String motivationLetter;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private User candidate;
    @ManyToOne
    @JoinColumn(name = "jobOffer_id")
    private JobOffer jobOffer;
}
