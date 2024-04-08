package com.tracer.welcomesystem.controller;


import com.tracer.welcomesystem.models.Announcement;
import com.tracer.welcomesystem.services.AnnouncementService;
import com.tracer.welcomesystem.utils.RespBean;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RequestMapping("/announcement")
@RestController
public class AnnouncementController {

    final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/list")
    public RespBean getAnnouncements() {
        return RespBean.ok("Announcements fetched successfully", announcementService.getAllAnnouncements());
    }

    @PostMapping("/add")
    public RespBean addAnnouncement(@RequestBody Map<String, String> announcement) {
        String title = announcement.get("title");
        String content = announcement.get("content");
        announcementService.saveAnnouncement(new Announcement(title, content));
        return RespBean.ok("Announcement added successfully");
    }

    @GetMapping("/delete")
    public RespBean deleteAnnouncement(@RequestParam(value = "id") Long id) {
        announcementService.deleteAnnouncement(id);
        return RespBean.ok("Announcement deleted successfully", id);
    }
}
