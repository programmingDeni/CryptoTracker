package com.crypto.tracker.security.UserFeature.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crypto.tracker.security.UserFeature.UserModel;
import com.crypto.tracker.security.UserFeature.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // User Erstellen
    public UserModel createUser(String email, String password) {
        // check email
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // user erstellen
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        UserModel savedUser = userRepository.save(user);

        // created bby setzen
        // savedUser.setCreatedBy(savedUser.getId());

        return savedUser;
    }

    // User nach email suchen
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // User nach id suchen
    public UserModel findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // check password
    public boolean checkPassword(UserModel user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

}
