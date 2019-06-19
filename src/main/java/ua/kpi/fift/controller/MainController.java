package ua.kpi.fift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.fift.service.MainService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class MainController {

    @Value("${img}")
    private String img;

    @Autowired
    private MainService service;

    private ArrayList<String> matrix;

    private String answer = "";

    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @PostMapping("/")
    public String generateMatrix(@RequestParam(required = false, defaultValue = "1") int tool, @RequestParam(required = false, defaultValue = "1") int barrier, Model model){

        matrix = service.generateMatrix(tool, barrier);

        model.addAttribute("matrix", matrix);
        model.addAttribute("check", service.solvabilityСheck());

        return "main";
    }

    @GetMapping("/file")
    public String createMatrix(Model model){

        matrix = service.createMatrix();

        model.addAttribute("matrix", matrix);
        model.addAttribute("check", service.solvabilityСheck());

        return "main";
    }


    @GetMapping("/greedy")
    public String greedyAlgorithm(Model model){

        answer = service.greedyAlgorithm();

        model.addAttribute("answer", answer);
        model.addAttribute("matrix", matrix);
        return "main";
    }

    @GetMapping("/genetic")
    public String geneticAlgorithm(Model model){

        answer = service.geneticAlgorithm();

        model.addAttribute("answer", answer);
        model.addAttribute("matrix", matrix);
        return "main";
    }

    @GetMapping("/bee")
    public String beeAlgorithm(Model model){
        answer = service.beeAlgorithm();

        model.addAttribute("answer", answer);
        model.addAttribute("matrix", matrix);
        return "main";
    }
}
