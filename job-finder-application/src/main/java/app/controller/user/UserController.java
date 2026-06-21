package app.controller.user;

import app.model.dto.user.UserDTO;
import app.model.dto.user.UserUpdateProfileRequest;
import app.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage(@PathVariable UUID id) {

        UserDTO user = userService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("userDTO", user);

        return modelAndView;
    }


    @PostMapping("/profile/{id}")
    public ModelAndView updateProfileSettings(@PathVariable UUID id, @ModelAttribute UserUpdateProfileRequest userUpdateProfileRequest) {
        UserDTO user = userService.updateProfile(id, userUpdateProfileRequest);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("userDTO", user);
        return modelAndView;
    }
}
