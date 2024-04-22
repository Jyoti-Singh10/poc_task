package com.springjpa.poctask.controller;

import com.springjpa.poctask.dto.UserCreateRequest;
import com.springjpa.poctask.entities.Users;
import com.springjpa.poctask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping()
    @Validated
    public ResponseEntity<Users> createUser(@RequestBody @Valid UserCreateRequest userRequest){

        Users addUser = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addUser);
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @GetMapping()
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/{id}")
    @Validated
    public Users putUsers(@PathVariable int id, @RequestBody @Valid Users user){
        return userService.putUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted the given user : "+id);
    }
}
