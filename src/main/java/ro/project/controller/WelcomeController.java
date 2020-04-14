package ro.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String main(Model model) {
        String message = "Start quiz";

        model.addAttribute("message", message);
        model.addAttribute("quiz", 1);

        return "welcome";
    }
}
