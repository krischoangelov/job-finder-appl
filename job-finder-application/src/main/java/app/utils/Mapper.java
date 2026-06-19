package app.utils;

import app.model.dto.jobapplication.JobApplicationDTO;
import app.model.dto.joboffer.JobOfferDTO;
import app.model.dto.skill.SkillDTO;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserRegisterRequestDTO;
import app.model.entity.jobapplication.JobApplication;
import app.model.entity.joboffer.JobOffer;
import app.model.entity.skill.Skill;
import app.model.entity.user.User;
import app.model.enums.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public class Mapper {

    public static UserDTO toUserDTO(User user) {

        List<JobOfferDTO> jobOfferDTOList = user.getCreatedJobs()
                .stream()
                .map(Mapper::toJobOfferDTO)
                .toList();

        List<JobApplicationDTO> jobApplicationDTOList = user.getJobApplications()
                .stream()
                .map(Mapper::toJobApplicationDTO)
                .toList();

        List<SkillDTO> skillDTOList = user.getSkills()
                .stream()
                .map(Mapper::toSkillDTO)
                .toList();


        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName()).
                profilePicture(user.getProfilePicture())
                .email(user.getEmail())
                .role(user.getRole())
                .createdOn(user.getCreatedOn())
                .createdJobs(jobOfferDTOList)
                .jobApplications(jobApplicationDTOList)
                .skills(skillDTOList).build();

    }

    public static JobOfferDTO toJobOfferDTO(JobOffer jobOffer) {

        List<JobApplicationDTO> jobApplicationDTOList = jobOffer.getJobApplications()
                .stream()
                .map(Mapper::toJobApplicationDTO)
                .toList();

        return JobOfferDTO.builder()
                .id(jobOffer.getId())
                .title(jobOffer.getTitle())
                .company(jobOffer.getCompany())
                .location(jobOffer.getLocation())
                .description(jobOffer.getDescription())
                .salary(jobOffer.getSalary())
                .type(jobOffer.getType())
                .createdOn(jobOffer.getCreatedOn())
                .deadline(jobOffer.getDeadline())
                .recruiter(jobOffer.getRecruiter())
                .jobApplications(jobApplicationDTOList).build();
    }

    public static JobApplicationDTO toJobApplicationDTO(JobApplication jobApplication) {
        return JobApplicationDTO.builder()
                .id(jobApplication.getId())
                .motivationLetter(jobApplication.getMotivationLetter())
                .status(jobApplication.getStatus())
                .appliedAt(jobApplication.getAppliedAt())
                .candidate(jobApplication.getCandidate())
                .jobOffer(jobApplication.getJobOffer()).build();

    }

    public static SkillDTO toSkillDTO(Skill skill) {
        return SkillDTO.builder()
                .id(skill.getId())
                .name(skill.getName())
                .level(skill.getLevel())
                .user(skill.getUser())
                .acquiredAt(skill.getAcquiredAt()).build();
    }

    public static User toUserEntity(UserRegisterRequestDTO userRegisterRequest) {
        return User.builder()
                .username(userRegisterRequest.getUsername())
                .password(userRegisterRequest.getPassword())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .email(userRegisterRequest.getEmail())
                .password(userRegisterRequest.getPassword())
                .role(UserRole.CANDIDATE)
                .createdOn(LocalDateTime.now())
                .build();
    }
}
