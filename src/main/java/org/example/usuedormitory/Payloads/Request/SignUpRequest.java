package org.example.usuedormitory.Payloads.Request;

import java.util.Date;

public class SignUpRequest {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String middleName;
    private Date birthday;
    private Integer numberRoom;
    private String address;
    private String role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getNumberRoom() {
        return numberRoom;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }
}
