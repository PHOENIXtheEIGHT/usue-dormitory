package org.example.usuedormitory.Repositories;

import org.example.usuedormitory.Entities.Application;
import org.example.usuedormitory.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByUser(User user);
}
