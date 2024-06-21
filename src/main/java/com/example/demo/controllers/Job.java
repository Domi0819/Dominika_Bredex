package com.example.demo.controllers;

import com.example.demo.authentication.JwtUtil;
import com.example.demo.entity.Client;
import com.example.demo.entity.Position;
import com.example.demo.exceptions.ClientException;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
public class Job {
    private final AuthenticationService authenticationService;
    private final PositionService positionService;
    private final ExceptionHandlingController exceptionHandlingController;

    @Autowired
    Job(AuthenticationService authenticationService, PositionService positionService, ExceptionHandlingController exceptionHandlingController){
        this.authenticationService = authenticationService;
        this.positionService = positionService;
        this.exceptionHandlingController = exceptionHandlingController;
    }

    @PostMapping("/client")
    ResponseEntity<?> register(@RequestBody @Valid  Client client) {
        if(this.authenticationService.Register(client.getName() , client.getEmail()).getStatusCode() != HttpStatus.CONFLICT){
            String token = JwtUtil.generateToken(client.getUsername());
            return new ResponseEntity<>(token , HttpStatus.OK);
        }
        else{
            throw new ClientException("Email already exists");
        }
    }

    @PostMapping("/position")
    @Secured("")
    ResponseEntity<?> PositionRegister(@RequestBody @Valid Position position){
         return this.positionService.savePosition(position.getName() , position.getLocation());
    }

    @Secured("")
    @GetMapping("/position/{id}")
    @ResponseBody
    ResponseEntity<?> getPositionById(@PathVariable String id) {
        return this.positionService.getById(Long.parseLong(id));
    }

    @Secured("")
    @GetMapping("/position/search")
    ResponseEntity<?> getPosition(@RequestParam String keyword , @RequestParam String location){
        return this.positionService.getPositions(keyword,location);
    }

    @GetMapping("/getall")
    ResponseEntity<?> getAll(){
        return this.positionService.getAll();
    }

}
