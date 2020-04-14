package ro.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.project.service.RezultatService;

import javax.servlet.http.HttpSession;

@Controller
public class FinishController {

    @Autowired
    RezultatService rezultatService;

    @GetMapping("/finish")
    public String main(Model model, HttpSession session) {
        rezultatService.persistRezultat(session.getId());
        model.addAttribute("sessionId", session.getId());
        return "finish";
    }
}



