package ca.debateon.records.guardian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ca.debateon.records.student.Student;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "guardian", schema = "public")
public class Guardian implements Serializable {

    @Id
    @Column(name = "guardian_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guardian_id;

    @Column(name = "guardian_name")
    private String guardian_name;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "cell_phone")
    private String cell_phone;

    @Column(name = "email")
    private String email;

    @Column(name = "home_phone")
    private String home_phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "postal_code")
    private String postal_code;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "guardian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

}