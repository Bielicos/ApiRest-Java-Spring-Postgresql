package com.example.demo.controllers;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.UpdateUserDTO;
import com.example.demo.entity.User;
import com.example.demo.repositorys.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO) {
        Integer userId = userService.createUser(createUserDTO);
        URI location = URI.create("/v1/users/" + userId);

        return ResponseEntity.created(location).body("ID do usuário : " + userId.toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        // Recebe o ID do usuário em forma de String, o Service transforma isso em Integer, identifica o usuário e retorna o usuário. Logo, aqui no controller é criado uma variável com o valor do usuário.
        var user = userService.getUserById(id);
        if (user.isPresent()) {
            // Se o usuário existir :
            return  ResponseEntity.ok(user.get());
            // Vai dar status 200
        } else  {
            // Caso ele não exista :
            return ResponseEntity.notFound().build();
            // Vai dar status 404
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var  users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById (@PathVariable("id") String id,
                                                @RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUserById(id, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    // É uma boa prática não retornar nada no payload
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
