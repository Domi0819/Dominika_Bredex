package com.example.demo.services;

import com.example.demo.entity.Position;
import com.example.demo.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository){
        this.positionRepository = positionRepository;
    }

    public ResponseEntity<?> savePosition(String name , String location){
        String url = "";
        Position position = new Position();
        position.setName(name);
        position.setLocation(location);

        Position p = this.positionRepository.save(position);
        url="http://localhost:8080/position/"+p.getId();
        return new ResponseEntity<>(
                url, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(long id){
        return new ResponseEntity<>(this.positionRepository.findById(id),HttpStatus.OK);
    }

    public ResponseEntity<?> getPositions(String keyworld , String location){
        List<String> urls = new ArrayList<>();
        List<Position> positions = this.positionRepository.findByKeywordAndLocation(keyworld,location);
        positions.forEach((Position p)-> {
            urls.add("http://localhost:8080/position/" + p.getId());
        });
        return new ResponseEntity<>(urls,HttpStatus.OK);
    }
    public ResponseEntity<?> getAll(){
        List<Position> all = this.positionRepository.findAll();
        return new ResponseEntity<>(all , HttpStatus.OK);
    }

}
