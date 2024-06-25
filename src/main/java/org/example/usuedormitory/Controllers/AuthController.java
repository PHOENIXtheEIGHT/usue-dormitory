package org.example.usuedormitory.Controllers;

import org.example.usuedormitory.Payloads.Request.SignInRequest;
import org.example.usuedormitory.Payloads.Response.JwtResponse;
import org.example.usuedormitory.Security.Services.UserDetailsImpl;
import org.example.usuedormitory.Security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/signIn")
    public String getSignIn() {
        return "auth";
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
        if (signInRequest.getUsername() == null || signInRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("Ошибка: Неверное имя пользователя или пароль");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        String role = String.valueOf(userDetails.getAuthorities());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(jwtCookie, userDetails.getId(),
                        userDetails.getUsername(), role));
    }
}
