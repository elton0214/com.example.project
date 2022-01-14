package com.example.project.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.AUTO;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@Entity
//public class Patient {
// @Id //@GeneratedValue(strategy = AUTO) //can't use when id is String
//    private String patientname;
//    private String patient_email;
//    private String patient_mobile;
//    private Date registeredDate;
//    
//    public Patient(String patient_name, String patient_email, String patient_mobile, Date registeredDate) {
//    	super();
//    	this.patientname = patient_name;
//        this.patient_email = patient_email;
//        this.patient_mobile = patient_mobile;
//        this.registeredDate = registeredDate;
//    }
//    public Patient() {
//        super();
//    }
//}

@Entity //@Entity 表示是一個對應到 Database Table 的 Object
public class Patient {

	@Id @GeneratedValue(strategy = AUTO)
    private Long patientid;
    private String patientname;
    private String patient_email;
    private String patient_mobile;
    private Date registeredDate;
    
    public Patient(String patientname, String patient_email, String patient_mobile, Date registeredDate) {
        super();
        this.patientname = patientname;
        this.patient_email = patient_email;
        this.patient_mobile = patient_mobile;
        this.registeredDate = registeredDate;
    }
    
    public Patient() {
        super();
    }
    
    public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getPatient_email() {
		return patient_email;
	}
	public void setPatient_email(String patient_email) {
		this.patient_email = patient_email;
	}
	public String getPatient_mobile() {
		return patient_mobile;
	}
	public void setPatient_mobile(String patient_mobile) {
		this.patient_mobile = patient_mobile;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
    

}
