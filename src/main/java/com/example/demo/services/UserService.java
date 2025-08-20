package com.example.demo.services;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.UpdateUserDTO;
import com.example.demo.entity.User;
import com.example.demo.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer createUser (CreateUserDTO dto) {

        // Conversão da classe DTO para a entity.
        User user = new User();
        user.setFirstName(dto.first_name());
        user.setLastName(dto.last_name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        var userSaved = userRepository.save(user);
        return userSaved.getId();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(Integer.parseInt(id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserById (String id,
                                UpdateUserDTO updateUserDTO) {

        Integer ID = Integer.parseInt(id);
        // Retorno um optional
        var UserEntity = userRepository.findById(ID);

        // Vejo se esse optional é presente, retorna true se for.
        if (UserEntity.isPresent()) {
            // Se for, caso os campos não forem nulos, eu faço a alteração :
            var user = UserEntity.get();

            if (updateUserDTO.first_name() != null) {
                user.setFirstName(updateUserDTO.first_name());
            }

            if (updateUserDTO.last_name() != null) {
                user.setLastName(updateUserDTO.last_name());
            }

            // Se o campo for nulo, não muda nada.
            if (updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }

            // O próprio JPA já identifica que a entidade já existe no banco de dados e ele muda a alteração de INSERT para UPDATE
            userRepository.save(user);
        }
    }

    public void deleteUserById(String id) {
        // Transforma de String para ID
        Integer ID = Integer.parseInt(id);

        // Checa se ele existe
        var UserExists = userRepository.existsById(ID);

        if (UserExists) {
            userRepository.deleteById(ID);
        }
    }
}
