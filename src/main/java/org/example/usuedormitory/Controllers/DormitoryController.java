package org.example.usuedormitory.Controllers;

import org.example.usuedormitory.Entities.*;
import org.example.usuedormitory.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private LaundryRepository laundryRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/main")
    public String getMain(Model model) {
        List<Announcement> announcements = announcementRepository.findAll().stream()
                .peek(announcement -> announcement.setFormattedDate(announcement.getFormattedDate()))
                .collect(Collectors.toList());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();
        String name = user.getSurname() + ' ' + user.getName().substring(0, 1) + '.' + user.getMiddleName().substring(0,1) + '.';
        model.addAttribute("user", name);
        model.addAttribute("announcements", announcements);
        return "main";
    }

    @PostMapping("/addAnnouncement")
    public ResponseEntity<?> addAnnouncement(@RequestBody Announcement announcement) {
        announcement.setDate(LocalDateTime.now());
        announcementRepository.save(announcement);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteAnnouncement/{announcementId}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Long announcementId) {
        announcementRepository.deleteById(announcementId);
        return ResponseEntity.ok().body("Успешно");
    }

    @GetMapping("/laundry")
    public String getLaundry(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();
        model.addAttribute("laundries", laundryRepository.findByUser(user));
        String name = user.getSurname() + ' ' + user.getName().substring(0, 1) + '.' + user.getMiddleName().substring(0,1) + '.';
        model.addAttribute("user", name);
        return "laundry";
    }

    @PostMapping("/write")
    public ResponseEntity<?> writeLaundry(@RequestBody Laundry laundry) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        laundry.setUser(userRepository.findByUsername(username).get());
        laundryRepository.save(laundry);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteLaundry/{laundryId}")
    public ResponseEntity<?> deleteLaundry(@PathVariable Long laundryId) {
        laundryRepository.deleteById(laundryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/applications")
    public String getApplications(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();
        model.addAttribute("appList", applicationRepository.findAllByUser(user));
        String name = user.getSurname() + ' ' + user.getName().substring(0, 1) + '.' + user.getMiddleName().substring(0,1) + '.';
        model.addAttribute("user", name);
        return "applications";
    }

    @PostMapping("/addApplication")
    public ResponseEntity<?> addApplication(@RequestBody Application application) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();
        application.setUser(user);
        applicationRepository.save(application);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calculator")
    public String getCalculator(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();
        String name = user.getSurname() + ' ' + user.getName().substring(0, 1) + '.' + user.getMiddleName().substring(0,1) + '.';
        model.addAttribute("pays", payRepository.findAllByUser(user));
        model.addAttribute("user", name);
        return "calculator";
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestBody Pay pay) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();
        pay.setUser(user);
        payRepository.save(pay);
        return ResponseEntity.ok().build();
    }
}