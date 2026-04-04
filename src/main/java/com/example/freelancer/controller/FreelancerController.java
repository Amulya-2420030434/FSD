package com.example.freelancer.controller;

import com.example.freelancer.model.Freelancer;
import com.example.freelancer.service.FreelancerService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/freelancers")
public class FreelancerController {

    private final FreelancerService service;

    public FreelancerController(FreelancerService service) {
        this.service = service;
    }

    // Add freelancer
    @PostMapping
    public Freelancer addFreelancer(@RequestBody Freelancer freelancer) {
        return service.addFreelancer(freelancer);
    }

    // Get all freelancers
    @GetMapping
    public List<Freelancer> getFreelancers() {
        return service.getAllFreelancers();
    }

    // Search by skill
    @GetMapping("/skill/{skill}")
    public List<Freelancer> searchFreelancers(@PathVariable String skill) {
        return service.searchBySkill(skill);
    }

    // Pagination
    @GetMapping("/page")
    public Page<Freelancer> getFreelancersPage(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getFreelancersPaginated(page, size);
    }

    // Analytics
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return service.getFreelancerStats();
    }

    // Upload resume
    @PostMapping("/uploadResume/{id}")
    public String uploadResume(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        try {

            String uploadDir = "uploads/";

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            Freelancer freelancer = service.getFreelancerById(id);

            freelancer.setResumeFile(fileName);

            service.addFreelancer(freelancer);

            return "Resume uploaded successfully";

        } catch (Exception e) {

            return "Error uploading file";
        }
    }

    // Download resume
    @GetMapping("/resume/{filename}")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable String filename) {

        try {

            Path filePath = Paths.get("uploads").resolve(filename);

            Resource resource = new UrlResource(filePath.toUri());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/rate/{id}")
    public Freelancer rateFreelancer(
            @PathVariable Long id,
            @RequestParam Double rating) {

        return service.updateRating(id, rating);
    }
    
    @DeleteMapping("/{id}")
    public String deleteFreelancer(@PathVariable Long id){

        service.deleteFreelancer(id);

        return "Freelancer deleted successfully";
    }
    
    
}