package ca.debateon.records.studentcontact;

import ca.debateon.records.student.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

/*
 * findByxxx method where xxx must be a property of this entity. In this case, Payroll which defines @ManyToOne relationship.
 */
public interface StudentContactRepository extends PagingAndSortingRepository<StudentContact, Long> {
    StudentContact findByStudent(Student student);
}