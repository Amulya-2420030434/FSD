package com.example.freelancer.repository;

import com.example.freelancer.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {

    List<Freelancer> findBySkill(String skill);

    Page<Freelancer> findAll(Pageable pageable);

    @Query("SELECT AVG(f.experience) FROM Freelancer f")
    Double getAverageExperience();

}