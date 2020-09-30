package code.entities;

import com.sun.istack.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(schema = "taxi")
public class Passenger {
    @Id
    private long id;
    @NotNull
    private String name;
    @NotNull
    @Size(min = 11, max = 11)
    private long phoneNumber;
    @OneToMany(mappedBy = "passenger")
    private Set<Ride> rides;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }

    protected Passenger() {}

    public Passenger(long id, String name, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
