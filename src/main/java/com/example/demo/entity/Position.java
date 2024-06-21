package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min=5 , max= 50)
    private String name;
    @Length(min=5 , max= 50)
    private String location;

    @Override
    public String toString() {
        return "Position name:" + name +", "+ location+", ID: "+ id;
    }

}
