package com.sharik.user_service.service;

import com.sharik.user_service.dto.UserDto;
import com.sharik.user_service.entity.User;
import com.sharik.user_service.exception.UserNotFoundException;
import com.sharik.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User get(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        if(null!=user.getId()) {
            User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
        } else{
            throw new UserNotFoundException("User Not Found!!!");
        }
        User updatedUser = new User();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setRole(user.getRole());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(updatedUser);
    }

    public User updateById(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
        User newUSer = new User();

        if(null!=userDto.getFirstName())
            existingUser.setFirstName(existingUser.getFirstName());
        if(null!=userDto.getLastName())
            existingUser.setLastName(existingUser.getLastName());
        if(null!=userDto.getEmail())
            existingUser.setEmail(userDto.getEmail());
        if(null!=userDto.getPhoneNumber())
            existingUser.setPhoneNumber(userDto.getPhoneNumber());
        if(null!=userDto.getRole())
            existingUser.setRole(userDto.getRole());

        return userRepository.save(existingUser);
    }

    public String delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
        if(null!=user){
            userRepository.delete(user);
            return "User deleted successfully!!!";
        }
        return "User Not Found!!!";
    }

    public String saveUsers(List<User> userList) {
        Instant start = Instant.now();
        System.out.printf("User Data addition to DB started : " + start);
        userRepository.saveAll(userList);
        Instant end = Instant.now();
        System.out.printf("User Data addition to DB completed : " + end);
        System.out.printf("Total time taken: " + Duration.between(end,start));
        return "Users data added to Database";
    }
}
