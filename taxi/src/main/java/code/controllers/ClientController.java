package code.controllers;

import code.entities.Driver;
import code.entities.Ride;
import code.repos.DriverRepo;
import code.repos.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/client")
public class ClientController {
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    PassengerRepo passengerRepo;

    @GetMapping(path = "/driverslist")
    private List<Driver> getDrivers() {
        return driverRepo.findByIsFreeTrue();
    }

    @GetMapping(path = "/rides")
    private Set<Ride> getRides(@RequestParam Long id) {
        return passengerRepo.findById(id).get().getRides();
    }
}
