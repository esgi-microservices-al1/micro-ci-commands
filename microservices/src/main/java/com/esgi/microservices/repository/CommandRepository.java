package com.esgi.microservices.repository;

import com.esgi.microservices.models.Commands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Commands, Long> {
}
