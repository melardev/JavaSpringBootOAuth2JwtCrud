package com.melardev.spring.jwtoauth.dao;


import com.melardev.spring.jwtoauth.entities.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
