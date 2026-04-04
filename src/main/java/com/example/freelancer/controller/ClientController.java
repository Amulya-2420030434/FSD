package com.example.freelancer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.freelancer.model.client;
import com.example.freelancer.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    public client addClient(@RequestBody client client){
        return clientService.saveClient(client);
    }

    @GetMapping("/all")
    public List<client> getAllClients(){
        return clientService.getAllClients();
    }
}