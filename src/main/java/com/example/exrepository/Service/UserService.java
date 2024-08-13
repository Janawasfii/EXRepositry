package com.example.exrepository.Service;

import com.example.exrepository.APIResponse.APIException;
import com.example.exrepository.Model.User;
import com.example.exrepository.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
 public List<User> getUser(){
     return userRepository.findAll();
 }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void updateUser(Integer id ,User user){
        User u = userRepository.findUserById(id);;
        if(u == null){
            throw new APIException("User not found");
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        user.setRole(user.getRole());
        u.setAge(user.getAge());
        userRepository.save(u);
    }
    public void deleteUser(Integer id){
     User u = userRepository.findUserById(id);
     if(u == null){
         throw new APIException("User not found");
     }
     userRepository.delete(u);
    }
    public User findByUsernameAndPassword(String username, String password){
        User u = userRepository.findByUsernameAndPassword(username, password);
        if(u == null){
            throw new APIException("User not found");
        }
        return u;
    }


    public User findUserByEmail(String email){
     User u = userRepository.findUserByEmail(email);
     if(u == null){
         throw new APIException("User not found");
     }
     return u;
    }

    public List<User> findUserByRole(String role){
     List<User> u = userRepository.findUserByRole(role);
     if(u.isEmpty()){
         throw new APIException("User not found");
     }
     return u;
    }
    public List<User> findUserByAgeOrAbove(int age){
     List<User> u = userRepository.findUserByAgeOrAbove(age);
     if(u.isEmpty()){
         throw new APIException("User not found");
     }
     return u;
    }


}
