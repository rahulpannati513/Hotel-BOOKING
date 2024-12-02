package com.hotelreservation.Controllers;

import com.hotelreservation.Model.User;
import com.hotelreservation.security.service.AuthResponse;
import com.hotelreservation.security.service.UserServiceImplementation;
import com.hotelreservation.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/users")
    @CrossOrigin(origins = "http://localhost:3000")
    public class UserController {

        @Autowired
        private UserServiceImplementation userService;

        @Autowired
        private JwtService jwtService;

        // Sign Up a new user
        @CrossOrigin(origins = "http://localhost:3000")
        @PostMapping("/signup")
        public ResponseEntity<?> signUp(@RequestBody User user) {
            // Delegate to service to save user and generate token
            return userService.saveUser(user);
        }

        // Sign In user and return JWT token
        @CrossOrigin(origins = "http://localhost:3000")
        @PostMapping("/signin")
        public ResponseEntity<?> signIn(@RequestBody User user) {
            // Delegate to service to validate user credentials and generate token
            return userService.loginUser(user.getUsername(), user.getPassword());
        }

        // Get user profile based on JWT token
        @CrossOrigin(origins = "http://localhost:3000")
        @GetMapping("/profile")
        public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String token) {
            try {
                // Extract the JWT token (strip "Bearer " prefix if present)
                String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
                User user = userService.findUserProfileByJwt(jwt);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        // Additional helper methods to secure other actions can be added here
    }
