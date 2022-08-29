package com.example.controller;

import com.example.dto.JwtRequestDto;
import com.example.dto.JwtResponseDto;
import com.example.jwtutils.TokenManager;
import com.example.model.User;
import com.example.service.impl.AppUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private AppUserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestDto request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final User user = (User) userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(user);
        return ResponseEntity.ok(new JwtResponseDto(jwtToken));
    }
}
