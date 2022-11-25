package com.training.security.security;

import com.training.security.security.jwt.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private JWTService            jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("user") String username,
                                       @RequestParam("pass") String password,
                                       @RequestHeader("X-Forwarded-For") String ip) {
        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails,
                                                            password,
                                                            userDetails.getAuthorities()));
            return ResponseEntity.status(200).body(jwtService.generateToken(userDetails, ip));
        } catch (Exception e) {
        }
        return ResponseEntity.status(401).body("Auth error");
    }
}
