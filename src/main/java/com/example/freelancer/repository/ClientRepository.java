package com.example.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.freelancer.model.client;

public interface ClientRepository extends JpaRepository<client, Long> {
}