package com.example.demo.services;

import com.example.demo.entity.Client;
import com.example.demo.exceptions.ClientException;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final ClientRepository clientRepository;

    @Autowired
    public AuthenticationService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
        this.getAllRegistered();
    }

    public ResponseEntity<?> Register(String name , String email){
        String key = "Valami";
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        Optional<Client> c = this.clientRepository.findByEmail(email);

        if(c.isPresent()){
            throw new ClientException("Email is already registered.");


        }else{
            System.out.println("Successfully registered.");
            this.clientRepository.save(client);
            return new ResponseEntity<>(
                    key, HttpStatus.OK);
        }

    }

    public void getAllRegistered(){
        List<Client> clients = (List<Client>) this.clientRepository.findAll();

        clients.forEach((Client client) -> {
            System.out.println(client.toString());
        });
    }


}
