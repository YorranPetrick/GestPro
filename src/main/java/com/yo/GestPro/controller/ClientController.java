package com.yo.GestPro.controller;

import com.yo.GestPro.models.client.ClientCreateDto;
import com.yo.GestPro.models.client.ClientLoginDto;
import com.yo.GestPro.models.token.TokenResponse;
import com.yo.GestPro.service.client.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    private ResponseEntity createClient(@RequestBody ClientCreateDto clientCreateDto) {
        clientService.createClient(clientCreateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    private ResponseEntity<TokenResponse> loginClient(@RequestBody ClientLoginDto clientLoginDto) throws Exception {
        TokenResponse isAuthenticated = clientService.authenticateClient(clientLoginDto);
        return ResponseEntity.ok(isAuthenticated);

    }


}
