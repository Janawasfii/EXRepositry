package com.example.exrepository.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min=5)
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "Username must not be empty")
    @Size(min=5)
    @Column(columnDefinition = "varchar(20) not null unique ")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @Email
    @NotEmpty(message="Email must not be empty")
    @Column(columnDefinition = "varchar(30) unique not null")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp="^(USER|ADMIN)$",message =" You have only 2 options (USER or ADMIN) only")
    //@Column(columnDefinition = "varchar(15) check (role= 'USER' or role= 'ADMIN') ")
    private String role;

    @NotNull(message = "Age must not be null")
    @Positive(message = "Age must be a positive number")
    @Column(columnDefinition = "int not null")
    private int age;
}
