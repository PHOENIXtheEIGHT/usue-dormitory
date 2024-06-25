package org.example.usuedormitory.Repositories;

import org.example.usuedormitory.Entities.Pay;
import org.example.usuedormitory.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {
    List<Pay> findAllByUser(User user);
}