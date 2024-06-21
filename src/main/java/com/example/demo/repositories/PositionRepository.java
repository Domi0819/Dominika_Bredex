package com.example.demo.repositories;

import com.example.demo.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value = "SELECT * FROM Position p WHERE p.name LIKE %:keyword% OR p.location LIKE %:location%", nativeQuery = true)
    List<Position> findByKeywordAndLocation(@Param("keyword") String keyword, @Param("location") String location);

}

