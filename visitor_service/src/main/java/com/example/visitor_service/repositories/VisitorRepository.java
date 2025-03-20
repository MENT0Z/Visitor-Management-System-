package com.example.visitor_service.repositories;

import com.example.visitor_service.entities.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor,Integer> {
    Optional<Visitor> findByContactInfo(String contactInfo);

}
