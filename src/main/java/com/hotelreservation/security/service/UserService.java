package com.hotelreservation.security.service;


import com.hotelreservation.Model.User;
import org.springframework.http.ResponseEntity;


public interface UserService {

    public ResponseEntity<?> saveUser(User user) throws UserNotFoundException;

    public User findUserProfileByJwt(String jwt) throws UserNotFoundException;

    ResponseEntity<?> loginUser(String username, String password);
}
