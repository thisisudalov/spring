package code.controllers;

import code.entities.*;
import code.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    PassengerRepo passengerRepo;
    @Autowired
    ClassOfAutoRepo classOfAutoRepo;
    @Autowired
    RoleRepo roleRepo;

    @GetMapping
    private Page<User> getUsers(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable p) {
        return userRepo.findAll(p);
    }

    @PostMapping
    private String registerUser(@RequestBody User user,
                                @RequestParam String name,
                                @RequestParam(required = false) Long phoneNumber,
                                @RequestParam(required = false) Long classOfAutoId,
                                @RequestParam(required = false) Long stage,
                                @RequestParam Long roleId) {
        Role r = roleRepo.findById(roleId).get();
        user.setRole(r);
        if (user.getRole().getName().equals("CLIENT")) {
            try {
                userRepo.save(user);
                passengerRepo.save(new Passenger(user.getId(), name, phoneNumber));
            } catch (NullPointerException e) {
                e.printStackTrace();
                return "Not enough parameters passed to the method, Client needs name and phoneNumber";
            }
        } else if (user.getRole().getName().equals("DRIVER")) {
            try {
                Optional<ClassOfAuto> optionalClassOfAuto = classOfAutoRepo.findById(classOfAutoId);
                if (optionalClassOfAuto.isPresent()) {
                    ClassOfAuto classOfAuto = optionalClassOfAuto.get();
                    userRepo.save(user);
                    driverRepo.save(new Driver(user.getId(), name, stage, classOfAuto));
                } else return "No such class of auto id";
            } catch (NullPointerException e) {
                e.printStackTrace();
                return "Not enough parameters passed to the method, Driver needs name and classOfAuto";
            }
        }
        return "User added";
    }
}
