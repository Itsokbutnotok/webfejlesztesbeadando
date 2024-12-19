package com.prz.buszbazis.controllers;


import com.prz.buszbazis.models.Busz;
import com.prz.buszbazis.models.BuszDTO;
import com.prz.buszbazis.services.BuszRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/buszok")
public class BuszController {
    @Autowired
    private BuszRepository buszRepository;

    @GetMapping({"", "/"})
    public String showBuszList(Model model) {
        List<Busz> buszok = buszRepository.findAll();
        model.addAttribute("buszok", buszok);
        return "buszok/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        BuszDTO buszDTO = new BuszDTO();
        model.addAttribute("buszDTO", buszDTO);
        return "buszok/createbusz";
    }

    @PostMapping("/create")
    public String createBusz(@Valid @ModelAttribute BuszDTO buszDTO, BindingResult bindingResult) throws IOException {
        if (buszDTO.getImage().isEmpty()) {
            bindingResult.addError(new FieldError("buszDTO", "image", "A kép nem lehet üres"));
        }

        if (bindingResult.hasErrors()) {
            return "buszok/createbusz";
        }

        //mentes
        MultipartFile image = buszDTO.getImage();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String updoadDir = "public/images/";
            Path uploadPath = Paths.get(updoadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(updoadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Busz busz = new Busz();
        busz.setRendszam(buszDTO.getRendszam());
        busz.setTipus(buszDTO.getTipus());
        busz.setMuszakierv(buszDTO.getMuszakierv());
        busz.setUlesekSzama(Integer.parseInt(buszDTO.getUlesekSzama()));
        busz.setImageFileName(storageFileName);

        buszRepository.save(busz);
        return "redirect:/buszok";
    }


    @GetMapping("/editbusz")
    public String showEditForm(Model model, @RequestParam int id) {
        try {
            Busz busz = buszRepository.findById(id).get();
            model.addAttribute("busz", busz);

            BuszDTO buszDTO = new BuszDTO();
            buszDTO.setRendszam(busz.getRendszam());
            buszDTO.setTipus(busz.getTipus());
            buszDTO.setMuszakierv(busz.getMuszakierv());
            buszDTO.setUlesekSzama(String.valueOf(busz.getUlesekSzama()));

            model.addAttribute("buszDTO", buszDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/buszok";
        }
        return "buszok/editbusz";
    }

    @PostMapping("/editbusz")
    public String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute BuszDTO buszDTO, BindingResult bindingResult) {
        try {
            Busz busz = buszRepository.findById(id).get();
            model.addAttribute("busz", busz);

            if (bindingResult.hasErrors()) {
                return "buszok/editbusz";
            }

            busz.setRendszam(buszDTO.getRendszam());
            busz.setTipus(buszDTO.getTipus());
            busz.setMuszakierv(buszDTO.getMuszakierv());
            busz.setUlesekSzama(Integer.parseInt(buszDTO.getUlesekSzama()));

            buszRepository.save(busz);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/buszok";
        }
        return "redirect:/buszok";
    }

    @GetMapping("/delete")
    public String deleteBusz(@RequestParam int id) {
        try {
            Busz busz = buszRepository.findById(id).get();

            Path path = Paths.get("public/images/" + busz.getImageFileName());
            try {
                Files.delete(path);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            buszRepository.delete(busz);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/buszok";
    }
}
