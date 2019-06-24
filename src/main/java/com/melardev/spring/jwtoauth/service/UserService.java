package com.melardev.spring.jwtoauth.service;


import com.melardev.spring.jwtoauth.entities.AppUser;
import com.melardev.spring.jwtoauth.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        // AppUser exists, we have to return an Implementation of UserDetails, let's use the default
        return new AppUser(user.get().getUsername(), user.get().getPassword());
    }


    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<AppUser> findAll() {
        List<AppUser> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public AppUser createUser(String username, String password) {
        AppUser user = new AppUser(username, passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public long count() {
        return userRepository.count();
    }
}
