package ca.debateon.records.guardian;

import ca.debateon.records.student.Student;
import ca.debateon.records.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
@Slf4j
public class GuardianController {

    @Resource
    GuardianRepository guardianRepository;

    @Resource
    StudentRepository studentRepository;

    @GetMapping("/guardians")
    public List<Guardian> retrieveAllGuardians() {
        return StreamSupport.stream(guardianRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Simulating slow IO using Thread
     *
     * @param guardian_id
     * @return
     */
    @GetMapping("/guardians/{guardian_id}")
    public Guardian findById(@PathVariable long guardian_id) {
        return guardianRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + guardian_id));
    }

    /**
     * NOT WORKING!!! Find Guardian by Student
     *
     * @param membership_id
     * @return
     */
    @GetMapping("/students/{membership_id}/guardians")
    public Guardian findGuardianByStudent(@PathVariable long membership_id) {
        Student student = studentRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + membership_id));

        return guardianRepository.findById(student.getGuardian_id())
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + student.getGuardian_id()));
    }


    @PostMapping("/guardians")
    public Guardian createGuardian(@Valid @RequestBody Guardian guardian) {
        return guardianRepository.save(guardian);
    }

    @PutMapping("/guardians/{guardian_id}")
    public Guardian updateGuardian(@PathVariable long guardian_id, @Valid @RequestBody Guardian guardian) {
        Guardian guardianFromDB = guardianRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + guardian_id));
        guardianFromDB.setGuardian_name(guardian.getGuardian_name());
        guardianFromDB.setRelationship(guardian.getRelationship());
        guardianFromDB.setCell_phone(guardian.getCell_phone());
        guardianFromDB.setEmail(guardian.getEmail());
        guardianFromDB.setHome_phone(guardian.getHome_phone());
        guardianFromDB.setAddress(guardian.getAddress());
        guardianFromDB.setCity(guardian.getCity());
        guardianFromDB.setProvince(guardian.getProvince());
        guardianFromDB.setPostal_code(guardian.getPostal_code());
        return guardianRepository.save(guardianFromDB);
    }

    @DeleteMapping("/guardians/{guardian_id}")
    public void delete(@PathVariable long guardian_id) {
        guardianRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + guardian_id));
        guardianRepository.deleteById(guardian_id);
    }

}