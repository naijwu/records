package ca.debateon.records.studentcontact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ca.debateon.records.student.Student;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "student_contact", schema = "public")
public class StudentContact implements Serializable {

    @Id
    @Column(name = "student_contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long student_contact_id;

    @JoinColumn(name = "membership_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Student student;

    @Column(name = "membership_id", insertable = false, updatable = false)
    private long membership_id;

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
}