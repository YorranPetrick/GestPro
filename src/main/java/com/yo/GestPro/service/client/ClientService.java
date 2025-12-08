package com.yo.GestPro.service.client;

import com.yo.GestPro.infra.security.SecurityConfiguration;
import com.yo.GestPro.infra.security.TokenJwt;
import com.yo.GestPro.models.client.Client;
import com.yo.GestPro.models.client.ClientCreateDto;
import com.yo.GestPro.models.client.ClientLoginDto;
import com.yo.GestPro.models.token.TokenResponse;
import com.yo.GestPro.repository.ClientRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final TokenJwt tokenJwt;
    private final AuthenticationManager authenticationManager;


    public ClientService(ClientRepository clientRepository, TokenJwt tokenJwt, AuthenticationManager authenticationManager) {
        this.clientRepository = clientRepository;
        this.tokenJwt = tokenJwt;
        this.authenticationManager = authenticationManager;
    }

    public void createClient(ClientCreateDto clientCreateDto) {
        try{

            Client client = new Client(
                    clientCreateDto.loginClient(),
                    SecurityConfiguration.passwordEncoder().encode(clientCreateDto.passwordClient()), // Encrypt the password
                    clientCreateDto.clientAccount().toString());

            clientRepository.save(client);

        }catch (Exception e){
            throw new RuntimeException("Error creating client", e);
        }
    }

    public TokenResponse authenticateClient(ClientLoginDto clientLoginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordToken =
                new UsernamePasswordAuthenticationToken(clientLoginDto.loginClient(), clientLoginDto.passwordClient());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordToken);

        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        Client client = (Client) authentication.getPrincipal();
        TokenResponse tokenResponse = new TokenResponse(tokenJwt.generateTokenJwt(client));
        return tokenResponse;
    }

}
