package com.example.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.project.Model.ApplicationUser;
import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;
import com.example.project.service.PatientService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
//    private final PatientService patientService;
//    
//    public PatientController(PatientService patientService) {
//        this.patientService = patientService;
//    }
    
private final PatientService patientService;
private final PatientRepository patientRepository;
    
    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }
    
 // GET all employees
    @GetMapping("/list")
    List<Patient> all() {
      return patientService.getPatients();
    }

//   // POST a patient
//   @PostMapping("/register")
//   public String createPatient(@RequestBody Patient patient) {
//       return patientService.savePatient(patient);
//   }
   
   @PostMapping( value = "/register", headers = "Accept=application/json", produces = "application/json")
   public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
	   Patient user = patientRepository.save(patient);
       if (patient != null) {
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }

//     // GET a patient
//     @GetMapping("/view/{patientname}")
//     public @ResponseBody
//     Optional<Patient> getPatient(@PathVariable("patientname") String patientname) throws Exception {
//         return patientService.getPatient(patientname);
//     }
     
  // GET a patient
     @GetMapping( value = "/view/{patientname}", headers = "Accept=application/json", produces = "application/json")
     public @ResponseBody
     ResponseEntity<?> getPatient(@PathVariable("patientname") String patientname) throws Exception {

    	 Map<String, Object> map = new HashMap<String, Object>();
         map.put("patient_name", patientService.getPatient(patientname).get().getPatientname());
         map.put("patient_email", patientService.getPatient(patientname).get().getPatient_email());
         map.put("patient_mobile", patientService.getPatient(patientname).get().getPatient_mobile());
         map.put("registeredDate", patientService.getPatient(patientname).get().getRegisteredDate());
         ResponseEntity rspEntity = new ResponseEntity(map,HttpStatus.OK);
    	 
         return rspEntity;
     }

     // DELETE a patient
     @DeleteMapping("/delete/{id}")
     @ResponseStatus(HttpStatus.OK)
     public @ResponseBody
     void deletePatient(@PathVariable("id") String id) throws Exception {
         patientService.deletePatient(id);
     }
}
