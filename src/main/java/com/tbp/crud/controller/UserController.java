package com.tbp.crud.controller;

import com.tbp.crud.dao.UserRepository;
import com.tbp.crud.entity.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

 @ResponseBody
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {

        return userRepository.saveUser(user);

    }
    @ResponseBody
    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {

        return userRepository.updateUser(user);

    }
    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id)  {

       User user=userRepository.getById(id);

       return user;

    }

    @ResponseBody
   @GetMapping("/users/{edition}")
    public List<User> getUserByE(@PathVariable("edition") int edition) {
        return userRepository.getByEdition(edition);
    }

    @ResponseBody
    @GetMapping("userByPrice/{price}")
    public List<User> getUserByPrice(@PathVariable("price") int price) {
        return userRepository.getPrice(price);
    }

    @ResponseBody
    @GetMapping("userByAuthor/{author}")
    public List<User> getUserByAuthor(@PathVariable("author") String author) {
        return userRepository.getAuthor(author);
    }

    @ResponseBody
    @GetMapping("/users")
    public List<User> getUser()  {
        return userRepository.allUsers();
   }


    @ResponseBody
    @DeleteMapping("/user/{id}")
    public String  deleteUser(@PathVariable("id") int id) {
        return userRepository.deleteById(id);
    }

}
