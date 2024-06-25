package org.example.usuedormitory.Entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "laundries")
public class Laundry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    private Integer idWashingMachine;
    private LocalTime time;

    public Laundry() {
    }

    public Laundry(User user, Integer idWashingMachine, LocalTime time) {
        this.user = user;
        this.idWashingMachine = idWashingMachine;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdWashingMachine() {
        return idWashingMachine;
    }

    public void setIdWashingMachine(Integer idWashingMachine) {
        this.idWashingMachine = idWashingMachine;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
