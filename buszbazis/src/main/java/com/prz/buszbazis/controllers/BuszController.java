package com.prz.buszbazis.controllers;


import com.prz.buszbazis.models.Busz;
import com.prz.buszbazis.services.BuszRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buszok")
public class BuszController {
    @Autowired
    private BuszRepository buszRepository;

    @GetMapping({ "", "/"})
    public String showBuszList(Model model) {
        List<Busz> buszok = buszRepository.findAll();
        model.addAttribute("buszok", buszok);
        return "buszok/index";
    }
}
