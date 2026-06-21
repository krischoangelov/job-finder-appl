package app.controller.skill;

import app.model.dto.skill.SkillDTO;
import app.model.entity.skill.CreateSkillRequest;
import app.service.skill.SkillService;
import app.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;
    private final UserService userService;

    public SkillController(SkillService skillService, UserService userService) {
        this.skillService = skillService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getSkillsPage(HttpSession httpSession) {

        List<SkillDTO> skills = skillService.getSkillByUser((UUID) httpSession.getAttribute("user_id"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("skills");
        modelAndView.addObject("skills", skills);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreateSkillPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-skill");
        modelAndView.addObject("skill", new CreateSkillRequest());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createSkill(
            @ModelAttribute CreateSkillRequest createSkillRequest,
            HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");

        skillService.createNewSkill(
                userId,
                createSkillRequest.getName(),
                createSkillRequest.getLevel()
        );

        return new ModelAndView("redirect:/skills");
    }
}
