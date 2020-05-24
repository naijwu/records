package ca.debateon.records.student;

import ca.debateon.records.guardian.Guardian;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ca.debateon.records.studentcontact.StudentContact;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable; // from 'implements Serializable', anything that needs to go through a network
import java.util.Date;

// Object of student from database
@Entity // Tells Spring framework the class is an object that needs to be mapped to db
@Data // Lombok specific; makes it create getters and setters
@Table(name="student", schema="public") // Map this entity to the specific table | name: name of table | schema: namespace; conceptual folder
public class Student implements Serializable {

    @Id // declares primary key of column
    @Column(name="membership_id") // maps this variable to column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generates value for the primary key | strategy: how it gets its value from db (removing @GenerationType = enter data programmatically)
    private long membership_id;

    @Column(name="guardian_id", insertable = false, updatable = false)
    private long guardian_id;

    // map the other columns:
    @Column(name = "preferred_name")
    private String preferred_name;

    @Column(name = "legal_name")
    private String legal_name;

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "membership_type")
    private String membership_type;

    @Column(name = "grade")
    private int grade;

    @Column(name = "date_of_registration")
    private Date date_of_registration;

    @Column(name = "school")
    private String school;

    @CreationTimestamp // from hibernate, will set this whenever the object gets created
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE) // prevents lombok to create setter for this column (want this to be taken care of by backend)
    private Date last_updated;

    // Create relationship of this table with another table
    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false) // Cascade = when loading/deleting, linked databases are also affected (can be more specific; 'Delete Cascade')
    @JsonIgnore // don't load all the tables related to this one
    private StudentContact studentContact;

    // relationship with guardian
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guardian_id", nullable = false) // can join column
    @JsonIgnore // experiment w this
    private Guardian guardian;

}
