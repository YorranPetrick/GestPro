package com.yo.GestPro.infra.filter;

import com.yo.GestPro.infra.security.TokenJwt;
import com.yo.GestPro.repository.ClientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class RequestFilter extends OncePerRequestFilter {


    private final TokenJwt tokenJwtDecode;
    private final ClientRepository clientRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJwt = recoverToken(request);

        try {
            if (tokenJwt != null){
                String subject = tokenJwtDecode.getSubject(tokenJwt);
                var client = clientRepository.findByLoginClient(subject).orElse(null);

                if (client != null){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }catch (Exception e){
             new RuntimeException("Error authenticating user", e);
        }

        filterChain.doFilter(request, response);
    }

    public static String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null){
            return null;
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}
