package com.esgi.microservices.repository;

import com.esgi.microservices.models.Commands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandsRepository extends JpaRepository<Commands, Long> {

    @Query(value = "select * from commands_commands cm, commands c , project p where c.project_id=p.project_id and c.process_id=cm.commands_process_id and c.project_id=:id", nativeQuery = true)
    List<Commands> listCommandsOfProject(@Param("id") Long id);
}
