package com.example.biblioteka2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getOne(long userId){
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
    }
    public User create(User user){
        return userRepository.save(user);
    }
    public void delete(long userId){
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("Book not found");
        }
        userRepository.deleteById(userId);
    }

}
