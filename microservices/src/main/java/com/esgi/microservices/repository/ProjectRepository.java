package com.esgi.microservices.repository;

import com.esgi.microservices.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
<<<<<<< HEAD
    Optional<Project> findByProjectName(String name);

//    Project findByTypeIsLike(String type);
//
//    Boolean existsByType(String type);

=======
    Optional<Project> findByName(String type);
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
}
