package com.melardev.spring.jwtoauth.repositories;


import com.melardev.spring.jwtoauth.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

}
