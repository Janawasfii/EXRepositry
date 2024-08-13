package com.example.exrepository.Controller;

import com.example.exrepository.Model.User;
import com.example.exrepository.Repository.UserRepository;
import com.example.exrepository.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("Successfully added user");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body("Successfully updated user");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("Successfully deleted user");
    }

    @GetMapping("/finds/{username}/{password}")
    public ResponseEntity findUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        User user = userService.findByUsernameAndPassword(username,password);
        if(user==null){
            return ResponseEntity.status(400).body("Username or password not found");
        }
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get-user/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        if(user == null){
            return ResponseEntity.status(400).body("User not found");
        }
        return ResponseEntity.status(200).body(user);

     }
     @GetMapping("/find/{role}")
    public ResponseEntity findUserByRole(@PathVariable String role){
        List<User> users = userService.findUserByRole(role);
        if(users.isEmpty()){
            return ResponseEntity.status(400).body("User not found");
        }
        return ResponseEntity.status(200).body(users);
     }
    @GetMapping("/get-age/{role}")
    public ResponseEntity findUserByAgeOrAbove(@PathVariable int age){
        List<User> users = userService.findUserByAgeOrAbove(age);
        if(users.isEmpty()){
            return ResponseEntity.status(400).body("User not found");
        }
        return ResponseEntity.status(200).body(users);
    }






}
