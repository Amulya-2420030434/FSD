package com.example.freelancer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freelancer.model.client;
import com.example.freelancer.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public client saveClient(client client){
        return clientRepository.save(client);
    }

    public List<client> getAllClients(){
        return clientRepository.findAll();
    }
}