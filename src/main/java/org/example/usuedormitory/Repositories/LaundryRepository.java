package org.example.usuedormitory.Repositories;

import org.example.usuedormitory.Entities.Laundry;
import org.example.usuedormitory.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaundryRepository extends JpaRepository<Laundry, Long> {
    List<Laundry> findByUser(User user);
    List<Laundry> findAll();
}
