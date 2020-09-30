package code.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "taxi")
public class Driver {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "classofauto_id", nullable = false)
    private ClassOfAuto classOfAuto;
    @OneToMany(mappedBy = "driver")
    private Set<Ride> rides;
    @NotNull
    private Long stage;
    private Boolean isFree;

    public Long getStage() {
        return stage;
    }

    public void setStage(Long stage) {
        this.stage = stage;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }

    protected Driver() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassOfAuto getClassOfAuto() {
        return classOfAuto;
    }

    public void setClassOfAuto(ClassOfAuto classOfAuto) {
        this.classOfAuto = classOfAuto;
    }

    public Driver(Long id, String name, Long stage, ClassOfAuto classOfAuto, Set<Ride> rides) {
        isFree = true;
        this.id = id;
        this.name = name;
        this.classOfAuto = classOfAuto;
        this.rides = rides;
        this.stage = stage;
    }

    public Driver(Long id, String name, Long stage, ClassOfAuto classOfAuto) {
        isFree = true;
        this.id = id;
        this.name = name;
        this.stage = stage;
        this.classOfAuto = classOfAuto;
    }
}
