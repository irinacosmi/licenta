package ro.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.project.enums.Varianta;
import ro.project.repository.IntrebareRepository;
import ro.project.service.RaspunsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class QuizController {

    private static final String QUIZ = "quiz";

    @Autowired
    private IntrebareRepository intrebareRepository;

    @Autowired
    private RaspunsService raspunsService;

    @GetMapping("/q/{id}")
    public String quiz(Model model, HttpSession session, @PathVariable int id) {
        //log.info("ieeeeeeeeeeeeeeeeeeeeeeeeee {}", id);
        model.addAttribute("session", session.getId());
        model.addAttribute("question", intrebareRepository.getOne(id));
        model.addAttribute("variante", getVariante());
        model.addAttribute("responded", succesffulyDone(session.getId()));
        return QUIZ;
    }

    private Map<Integer, Varianta> getVariante() {
        Map<Integer, Varianta> variantaList = new HashMap<>();
        variantaList.put(Varianta.NU.label, Varianta.NU);
        variantaList.put(Varianta.DA.label, Varianta.DA);
        return variantaList;
    }

    @PostMapping("/variante")
    public ModelAndView record(@RequestParam String intrebareId, @RequestParam String varianta, HttpServletRequest request) {
        int intIntrebareId = Integer.parseInt(intrebareId);
        int intVarianta = Integer.parseInt(varianta);
        raspunsService.persistRaspuns(intIntrebareId, request.getSession().getId(), intVarianta);
        if (intIntrebareId < getTotalQuiz()) {
            return new ModelAndView("redirect:/q/" + ++intIntrebareId);
        }
        return new ModelAndView("redirect:/finish");
    }

    private Integer getTotalQuiz() {
        long total = intrebareRepository.count();
        return (int)total;
    }

    private Integer succesffulyDone(String sessionId) {
        int respondedQuiz = raspunsService.getCountRespondedQuizBySessionId(sessionId);
        if (respondedQuiz == 0) {
            return 0;
        }
        return  (int) Math.ceil( (respondedQuiz * 100) / getTotalQuiz());
    }
}
