package com.example.freelancer.service;

import com.example.freelancer.model.Freelancer;
import com.example.freelancer.repository.FreelancerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class FreelancerService {

    private final FreelancerRepository repository;

    public FreelancerService(FreelancerRepository repository) {
        this.repository = repository;
    }

    // Add freelancer
    public Freelancer addFreelancer(Freelancer freelancer) {
        return repository.save(freelancer);
    }

    // Get all freelancers
    public List<Freelancer> getAllFreelancers() {
        return repository.findAll();
    }

    // Search by skill
    public List<Freelancer> searchBySkill(String skill) {
        return repository.findBySkill(skill);
    }

    // Pagination
    public Page<Freelancer> getFreelancersPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    // Analytics
    public Map<String, Object> getFreelancerStats() {

        Map<String, Object> stats = new HashMap<>();

        stats.put("totalFreelancers", repository.count());
        stats.put("averageExperience", repository.getAverageExperience());

        return stats;
    }

    // Get freelancer by ID
    public Freelancer getFreelancerById(Long id){
        return repository.findById(id).orElseThrow();
    }

    // Update rating
    public Freelancer updateRating(Long id, Double rating) {

        Freelancer freelancer = repository.findById(id).orElse(null);

        if(freelancer != null){
            freelancer.setRating(rating);
            return repository.save(freelancer);
        }

        return null;
    }
    
    public void deleteFreelancer(Long id){
        repository.deleteById(id);
    }
}