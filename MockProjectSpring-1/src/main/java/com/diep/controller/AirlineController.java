package com.diep.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diep.model.AirlineInformation;
import com.diep.service.AirlineService;

/**
 * This class handles requests related to airline information management.
 * Account: DiepDP1 Birthday: 1998-02-27
 */
@Controller
@RequestMapping("/airline")
public class AirlineController {
    @Autowired
    AirlineService service;
    private static final int PAGE_SIZE = 13;

    /**
     * Displays the form to add a new airline.
     * 
     * Account: DiepDP1 Birthday: 1998-02-27
     * 
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String addAirline(Model model) {
        model.addAttribute("airline", new AirlineInformation());
        return "add";
    }

    /**
     * Saves a new airline information after validation.
     * 
     * Account: DiepDP1 Birthday: 1998-02-27
     * 
     * @param airline
     * @param result
     * @param ra
     * @return
     */
    @PostMapping("/save")
    public String saveAirline(@ModelAttribute(name = "airline") @Valid AirlineInformation airline, BindingResult result,
            RedirectAttributes ra) {
        int checkExist = service.countAirlineCode(airline.getAirlineCode());
        if (checkExist > 0) {
            result.rejectValue("airlineCode", "duplicate",
                    "The airline code exist in the systerm. Please enter a new airline code.");
            return "add";
        }
        if (result.hasErrors()) {
            return "add";
        }
        service.save(airline);
        ra.addFlashAttribute("successfully", "Add Airline Information successfully");
        
        ra.addFlashAttribute("id", airline.getId());
        int page = service.updatePage(airline.getId());
        return "redirect:/airline/list?page=" + page;
    }

    /**
     * Displays the form to update an existing airline.
     * 
     * Account: DiepDP1 Birthday: 1998-02-27
     * 
     * @param model
     * @param id
     * @param airlineCode
     * @param airlineName
     * @param wholesalerPCC
     * @param contactNumber
     * @param email
     * @param ra
     * @return
     */
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable(name = "id") long id,
            @RequestParam(name = "airlineCode") String airlineCode,
            @RequestParam(name = "airlineName") String airlineName,
            @RequestParam(name = "wholesalerPCC") String wholesalerPCC,
            @RequestParam(name = "contactNumber") String contactNumber, @RequestParam(name = "email") String email,
            RedirectAttributes ra) {

        List<Long> listID = service.findAllId();
        if (!listID.contains(id)) {
            ra.addFlashAttribute("error", "The selected record has been removed by someone.");
            ra.addFlashAttribute("showModal", true);
            return "redirect:/airline/list";
        }
        AirlineInformation airline = service.findAirlineById(id);
        // @formatter:off
		if (airline != null && (!airline.getAirlineCode().equals(airlineCode)
				|| !airline.getAirlineName().equals(airlineName) 
				|| !airline.getWholesalerPCC().equals(wholesalerPCC)
				|| !airline.getContactNumber().equals(contactNumber) 
				|| !airline.getEmail().equals(email))) {
		// @formatter:on
            ra.addFlashAttribute("error",
                    "The selected record has been changed by another user! Please refresh to get the latest data for your update.");
            ra.addFlashAttribute("showModal", true);
            return "redirect:/airline/list";
        }
        model.addAttribute("airline", airline);
        return "update";
    }

    /**
     * Updates an existing airline information after validation.
     * 
     * Account: DiepDP1 Birthday: 1998-02-27
     * 
     * @param model
     * @param airline
     * @param result
     * @param ra
     * @return
     */
    @PostMapping("/update/{id}")
    public String updateAirline(Model model, @ModelAttribute("airline") @Valid AirlineInformation airline,
            BindingResult result, RedirectAttributes ra) {

        if (result.hasErrors()) {
            return "update";
        }
        service.save(airline);
        service.save(airline);
        ra.addFlashAttribute("successfully", "Update Airline Information successfully");

        ra.addFlashAttribute("id", airline.getId());
        int page = service.updatePage(airline.getId());
        return "redirect:/airline/list?page=" + page;
    }

    /**
     * Displays a list of airlines with pagination.
     * 
     * Account: DiepDP1 Birthday: 1998-02-27
     * 
     * @param model
     * @param page
     * @return
     */
    @GetMapping("/list")
    public String getAll(Model model, @RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<AirlineInformation> airlines = service.findAll(pageable);
        model.addAttribute("airlines", airlines.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", airlines.getTotalPages());
        model.addAttribute("listCode", service.findAirlineInformation());
        model.addAttribute("code", null);
        return "list";
    }

    /**
     * Filters and displays a list of airlines based on the provided code with
     * pagination.
     * 
     * Account: DiepDP1 Birthday: 1998-02-27
     * 
     * @param model
     * @param code
     * @param page
     * @return
     */
    @GetMapping("/filter")
    public String filter(Model model, @ModelAttribute("code") String code,
            @RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<AirlineInformation> airlines = service.findByAirlineCode(code, pageable);
        if (airlines.isEmpty()) {
            model.addAttribute("message", "No data found!");
        }
        model.addAttribute("airlines", airlines.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", airlines.getTotalPages());
        model.addAttribute("listCode", service.findAirlineInformation());
        model.addAttribute("code", code);
        return "filter";
    }
}
