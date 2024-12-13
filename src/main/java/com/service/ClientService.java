package com.service;

import com.entity.Client;
import com.entity.CustomUserDetails;
import com.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;


    public Optional<Client> findById(Long id) {
        return clientRepository.findById(Client.class, id);
    }
    public void save(Client client) {
        clientRepository.save(client);
    }
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username);
        if (client == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(
                client.getId(),
                client.getEmail(),
                client.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                true, true, true, true
        );
    }


}
