package com.uet.gts.auth.service.impl;

import com.uet.gts.auth.model.dto.UserDetailDTO;
import com.uet.gts.auth.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        var userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User: %s not found", username));
        }

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(
                        userOpt.get().getUserAuthority().getUserRole().getAuthority()
                )
        );

        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUser(userOpt.get());
        userDetailDTO.setAuthorities(authorities);

        return userDetailDTO;
    }
}
