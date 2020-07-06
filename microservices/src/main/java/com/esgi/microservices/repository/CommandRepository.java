package com.esgi.microservices.repository;

import com.esgi.microservices.models.Commands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Commands, Long> {

    @Query("select c,p from Commands c , Project p WHERE c.project.project_id=p.project_id and c.process_id=:id")
    List<Commands> listCommandsOfProject(@Param("id") Long id);
}
