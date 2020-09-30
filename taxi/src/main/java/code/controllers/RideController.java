package code.controllers;

import code.entities.Driver;
import code.entities.Passenger;
import code.entities.Ride;
import code.repos.DriverRepo;
import code.repos.PassengerRepo;
import code.repos.RideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;


@RestController
@RequestMapping(path = "/ride")
public class RideController {

    @Autowired
    RideRepo rideRepo;
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    PassengerRepo passengerRepo;

    @PostMapping
    private String createRide(@RequestParam Long distanceMetres, @RequestParam Long driverId) {
        BigDecimal cost = new BigDecimal(distanceMetres/100*3)
                .setScale(2, RoundingMode.CEILING); //30 rub km
        Driver driver;
        Optional<Driver> optionalDriver = driverRepo.findById(driverId);
        if (optionalDriver.isPresent() && optionalDriver.get().getFree()) {
            driver = optionalDriver.get();
        } else return "Водитель уже недоступен, выберите другого";
        Passenger passenger = passengerRepo.findById(2);
        System.out.println(driver.getId());
        System.out.println(passenger.getId());
        rideRepo.save(new Ride(driver, passenger, cost));
        return "Поездка начата";
    }

    @PutMapping
    private String endRide(Long rideId) {
        Optional<Ride> optionalRide = rideRepo.findById(rideId);
        if (optionalRide.isPresent() && !optionalRide.get().getOver()) {
            if (optionalRide.get().endRide()) {
                rideRepo.deleteById(rideId);
                rideRepo.save(optionalRide.get());
                return "Поездка завершена";
            } else return "Поездка уже была завершена";
        } else return "Такой поездки не найдено";
    }
}
