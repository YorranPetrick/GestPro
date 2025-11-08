package com.yo.GestPro.controller;

import com.yo.GestPro.models.client.ClientCreateDto;
import com.yo.GestPro.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    private ResponseEntity createClient(@RequestBody ClientCreateDto clientCreateDto) {
        clientService.createClient(clientCreateDto);
        return ResponseEntity.ok().build();
    }
}
