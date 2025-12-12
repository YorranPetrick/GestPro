package com.yo.GestPro.service.client;

import com.yo.GestPro.infra.security.SecurityConfiguration;
import com.yo.GestPro.infra.security.TokenJwt;
import com.yo.GestPro.models.client.Client;
import com.yo.GestPro.models.client.ClientCreateDto;
import com.yo.GestPro.models.client.ClientLoginDto;
import com.yo.GestPro.models.token.TokenResponse;
import com.yo.GestPro.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final TokenJwt tokenJwt;
    private final AuthenticationManager authenticationManager;


    public void createClient(ClientCreateDto clientCreateDto) {

            Client client = new Client(
                    clientCreateDto.loginClient(),
                    SecurityConfiguration.passwordEncoder().encode(clientCreateDto.passwordClient()), // Encrypt the password
                    clientCreateDto.clientAccount().toString());

            clientRepository.save(client);

    }

    public TokenResponse authenticateClient(ClientLoginDto clientLoginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordToken =
                new UsernamePasswordAuthenticationToken(clientLoginDto.loginClient(), clientLoginDto.passwordClient());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordToken);

        Client client = (Client) authentication.getPrincipal();
        return new TokenResponse(tokenJwt.generateTokenJwt(client), tokenJwt.expirationDate());
    }

    public void updateClient(ClientCreateDto clientCreateDto, String idClient) {

            Client client = clientRepository.findById(UUID.fromString(idClient))
                    .orElse(null);

           if (client != null) {
                client.setLoginClient(
                        clientCreateDto.loginClient() != null ?
                                clientCreateDto.loginClient() : client.getLoginClient());

                client.setPasswordClient(
                        clientCreateDto.passwordClient() != null ?
                        SecurityConfiguration
                                .passwordEncoder()
                                .encode(clientCreateDto.passwordClient()) : client.getPasswordClient());

                client.setClientAccount(
                        clientCreateDto.clientAccount() != null ?
                                clientCreateDto.clientAccount() : client.getClientAccount());

                clientRepository.save(client);
           }
    }

    public void deleteClient(String idClient) {
            clientRepository.deleteById(UUID.fromString(idClient));
        }
}
