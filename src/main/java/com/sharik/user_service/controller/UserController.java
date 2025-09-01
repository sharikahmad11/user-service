package com.sharik.user_service.controller;

import com.sharik.user_service.dto.UserDto;
import com.sharik.user_service.entity.User;
import com.sharik.user_service.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@NoArgsConstructor
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) throws Exception {
        return userService.get(id);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @PatchMapping("update/{id}")
    public User updateById(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateById(id, userDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody List<User> userList){
        return userService.saveUsers(userList);
    }
}
