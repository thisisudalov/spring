package code.controllers;

import code.entities.Driver;
import code.entities.Passenger;
import code.entities.Ride;
import code.repos.DriverRepo;
import code.repos.PassengerRepo;
import code.repos.RideRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.opentest4j.TestAbortedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RideControllerTest {
    @Test
    public void createRide() {
        Long driverId = 0l;
        Long distanceMetres = 100L;
        DriverRepo driverRepo = Mockito.mock(DriverRepo.class);
        PassengerRepo passengerRepo = Mockito.mock(PassengerRepo.class);
        RideRepo rideRepo = Mockito.mock(RideRepo.class);
        BigDecimal cost = new BigDecimal(distanceMetres/100*3)
                .setScale(2, RoundingMode.CEILING); //30 rub km
        Driver driver;
        Optional<Driver> optionalDriver = driverRepo.findById(driverId);
        if (optionalDriver.isPresent() && optionalDriver.get().getFree()) {
            driver = optionalDriver.get();
        } else return;
        Passenger passenger = passengerRepo.findById(2);
        Mockito.when(rideRepo.save(new Ride(driver, passenger, cost))).thenReturn(null);
    }
}