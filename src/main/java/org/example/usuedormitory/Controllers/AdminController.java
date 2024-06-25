package org.example.usuedormitory.Controllers;

import org.example.usuedormitory.Entities.Role;
import org.example.usuedormitory.Entities.User;
import org.example.usuedormitory.Payloads.Request.SignUpRequest;
import org.example.usuedormitory.Repositories.RoleRepository;
import org.example.usuedormitory.Security.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserDetailsServiceImpl userDetailsService, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/create")
    public String getSignUp() {
        return "admin-page";
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(), signUpRequest.getSurname(), signUpRequest.getMiddleName(),
                signUpRequest.getBirthday(), signUpRequest.getNumberRoom(), signUpRequest.getAddress());

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(signUpRequest.getRole());
        roles.add(role);

        user.setRoles(roles);

        userDetailsService.saveUser(user);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }
}
