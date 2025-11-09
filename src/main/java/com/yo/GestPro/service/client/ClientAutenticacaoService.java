package com.yo.GestPro.service.client;

import com.yo.GestPro.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class ClientAutenticacaoService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public ClientAutenticacaoService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginClient) throws UsernameNotFoundException {
        var client = clientRepository.findByLoginClient(loginClient).orElse(null);

        if (client == null){
            throw new UsernameNotFoundException("Client not found");
        }
        return client;
    }
}
