package com.example.demo.repositories;

import com.example.demo.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
  Optional<Client> findByEmail (String email);
}


