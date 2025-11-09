package com.yo.GestPro.service.client;

import com.yo.GestPro.models.client.Client;
import com.yo.GestPro.models.client.ClientCreateDto;
import com.yo.GestPro.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(ClientCreateDto clientCreateDto) {
        try{
            Client client = new Client(
                    clientCreateDto.loginClient(),
                    clientCreateDto.passwordClient(),
                    clientCreateDto.clientAccount());

            clientRepository.save(client);

        }catch (Exception e){
            throw new RuntimeException("Error creating client", e);
        }
    }
}
