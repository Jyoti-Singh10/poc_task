package com.springjpa.poctask.service;

import com.springjpa.poctask.dto.UserCreateRequest;
import com.springjpa.poctask.entities.Users;
import com.springjpa.poctask.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Users createUser(UserCreateRequest user) {
        Users newUser = new Users();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUser(int id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("Id not found."));
    }

    @Override
    @Transactional
    public Users putUser(int id, Users user) {
        if(user.getId()!=id) throw new RuntimeException("Id doesn't matched.");
        return userRepository.findById(id).map(existingId->userRepository.save(user))
                .orElseThrow(()->new RuntimeException("Id not found."));
     }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
