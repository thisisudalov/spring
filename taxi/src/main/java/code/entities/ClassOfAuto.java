package code.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "taxi")
public class ClassOfAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfClass;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classOfAuto")
    private Set<Driver> drivers;

    public String getNameOfClass() {
        return nameOfClass;
    }

    public void setNameOfClass(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }

    protected ClassOfAuto() {}

    public ClassOfAuto(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }
}
