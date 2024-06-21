package com.example.demo.configuration;


import com.example.demo.entity.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;



class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ClientRepository repository) {

        return args -> {
            Client client = new Client();
            client.setEmail("asd@emil.com");
            client.setName("mösziő asd");


           // System.out.println("Preloading " + repository.save(client).toString());
        };
    }
}
