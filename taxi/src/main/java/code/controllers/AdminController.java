package code.controllers;


import code.entities.User;
import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @DeleteMapping
    private String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId) {
        if (userService.deleteUser(userId)) return "Deleted";
        else return "No such user";
    }

    @GetMapping
    private List<User> getUsers() {
        return userService.allUsers();
    }

    @GetMapping("{/id}")
    private User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
