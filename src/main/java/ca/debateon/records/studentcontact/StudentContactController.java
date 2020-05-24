package ca.debateon.records.studentcontact;

import ca.debateon.records.student.StudentRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin()
@RestController
public class StudentContactController {

    @Resource
    StudentContactRepository studentContactRepository;

    @Resource
    StudentRepository studentRepository;

    @GetMapping("/students/{membership_id}/contact")
    public StudentContact findById(@PathVariable long membership_id) {
        return studentContactRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Contact not found with ID: " + membership_id));
    }

    @PostMapping("/students/{membership_id}/contact")
    public StudentContact createStudentContact(@Valid @RequestBody StudentContact studentContact) {
        return studentContactRepository.save(studentContact);
    }

    @PutMapping("/students/{membership_id}/contact")
    public StudentContact updateStudentContact(@PathVariable long membership_id, @Valid @RequestBody StudentContact studentContact) {
        StudentContact contactFromDB = studentContactRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Contact not found with ID: " + membership_id));

        contactFromDB.setCell_phone(studentContact.getCell_phone());
        contactFromDB.setEmail(studentContact.getEmail());
        contactFromDB.setHome_phone(studentContact.getHome_phone());
        contactFromDB.setAddress(studentContact.getAddress());
        contactFromDB.setCity(studentContact.getCity());
        contactFromDB.setProvince(studentContact.getProvince());
        contactFromDB.setPostal_code(studentContact.getPostal_code());
        return studentContactRepository.save(contactFromDB);
    }

}
