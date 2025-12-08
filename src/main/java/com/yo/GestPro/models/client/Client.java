package com.yo.GestPro.models.client;

import com.yo.GestPro.models.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "client")
public class Client implements UserDetails {
    @Column(nullable = false, name = "id_client")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID idClient;
    @Column(nullable = false, unique = true, name = "login_client")
    private String loginClient;
    @Column(nullable = false, name = "password_client")
    private String passwordClient;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientAccount clientAccount;

    @OneToMany(mappedBy = "client")
    private List<Product> products;

    public Client(String loginClient, String passwordClient, String clientAccount) {
        this.loginClient = loginClient;
        this.passwordClient = passwordClient;
        this.clientAccount = ClientAccount.valueOf(clientAccount);
    }

    public Client(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.clientAccount.toString()));
    }

    @Override
    public String getPassword() {
        return this.passwordClient;
    }

    @Override
    public String getUsername() {
        return this.loginClient;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
