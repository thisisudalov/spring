package code.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(schema = "taxi")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @MapsId("id")
    private Driver driver;
    @ManyToOne
    @MapsId("id")
    private Passenger passenger;
    private LocalDateTime startTime;
    private String duration;
    private BigDecimal cost;
    private Boolean isOver;

    public Boolean getOver() {
        return isOver;
    }

    public void setOver(Boolean over) {
        isOver = over;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public LocalDateTime getDateTime() {
        return startTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.startTime = dateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    protected Ride() {}

    public Ride(Driver driver, Passenger passenger, BigDecimal cost) {
        this.driver = driver;
        this.passenger = passenger;
        this.startTime = LocalDateTime.now();
        this.cost = cost;
        isOver = false;
    }

    public boolean endRide() {
        if (this.isOver) {
            return false;
        } else this.isOver = true;
        this.duration = Duration.between(this.startTime, LocalDateTime.now()).toString();
        return true;
    }
}
