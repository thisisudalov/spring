package code.controllers;

import code.entities.*;
import code.repos.ClassOfAutoRepo;
import code.repos.DriverRepo;
import code.repos.PassengerRepo;
import code.repos.UserRepo;
import org.hibernate.AssertionFailure;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.opentest4j.TestAbortedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @MockBean
    private PassengerRepo passengerRepo;
    @Test
    public void registerUser() {

        User user = new User();
        UserRepo userRepo = Mockito.mock(UserRepo.class);
        DriverRepo driverRepo = Mockito.mock(DriverRepo.class);
        ClassOfAutoRepo classOfAutoRepo = Mockito.mock(ClassOfAutoRepo.class);

        Long classOfAutoId = 1L;
        Role r = new Role(2L, "DRIVER");
        user.setRole(r);
        String name = "name";
        Long stage = 5l;
        if (!user.getRole().getName().equals("DRIVER")) {
            throw new TestAbortedException("TEST FAILED");
        } else if (user.getRole().getName().equals("DRIVER")) {
            try {
                Optional<ClassOfAuto> optionalClassOfAuto = classOfAutoRepo.findById(classOfAutoId);
                if (optionalClassOfAuto.isPresent()) {
                    ClassOfAuto classOfAuto = optionalClassOfAuto.get();
                    userRepo.save(user);
                    Mockito.when(driverRepo.save(new Driver(user.getId(), name, stage, classOfAuto))).thenReturn(null);
                    Mockito.when(userRepo.save(user)).thenReturn(null);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}