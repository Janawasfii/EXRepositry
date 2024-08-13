package com.example.exrepository.Repository;

import com.example.exrepository.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByEmail(String email);


    @Query("select u from User u where u.role=?1")
    List<User> findUserByRole(String role);

    @Query("select u from User u where u.age>=?1")
    List<User> findUserByAgeOrAbove(int age);


    User findByUsernameAndPassword(String username, String password);

}
