package com.yo.GestPro.controller;

import com.yo.GestPro.models.client.ClientCreateDto;
import com.yo.GestPro.models.client.ClientLoginDto;
import com.yo.GestPro.models.token.TokenResponse;
import com.yo.GestPro.service.client.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    private ResponseEntity<Void> createClient(@RequestBody ClientCreateDto clientCreateDto) {
        clientService.createClient(clientCreateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    private ResponseEntity<TokenResponse> loginClient(@RequestBody ClientLoginDto clientLoginDto) throws Exception {
        TokenResponse isAuthenticated = clientService.authenticateClient(clientLoginDto);
        return ResponseEntity.ok(isAuthenticated);

    }

    @PutMapping
    private ResponseEntity<Void> updateClient(@RequestParam String idClient ,@RequestBody ClientCreateDto clientCreateDto) {
        clientService.updateClient(clientCreateDto, idClient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteClient(@RequestParam String idClient){
        clientService.deleteClient(idClient);
        return ResponseEntity.ok().build();
    }


}
