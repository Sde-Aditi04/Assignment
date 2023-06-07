package com.example.Authorization.service;


import com.example.Authorization.dto.UserDTO;
import com.example.Authorization.model.User;
import com.example.Authorization.repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        // Implementation to create a user
        User user = new User();
        user.setUsername(userDTO.getUsername());
        // Set other user properties
        // Save the user using the UserRepository
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        // Implementation to get a user by ID
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        // Implementation to update a user
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            // Update the user properties with the values from userDTO
            existingUser.setUsername(userDTO.getUsername());
            // Update other user properties
            // Save the updated user using the UserRepository
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        // Implementation to delete a user
        userRepository.deleteById(id);
    }
}


