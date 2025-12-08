package com.yo.GestPro.service.client;

import com.yo.GestPro.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientAuthenticationService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String loginClient) throws UsernameNotFoundException {
        var client = clientRepository.findByLoginClient(loginClient).orElse(null);

        if (client == null){
            throw new UsernameNotFoundException("Client not found");
        }
        return client;
    }

}
