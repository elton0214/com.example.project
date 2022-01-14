package com.example.project.Model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(generator="system_uuid") //or just generate manually
	@GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "appointment")
    private String bookingid;
    public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Date getTentativeDate() {
		return tentativeDate;
	}

	public void setTentativeDate(Date tentativeDate) {
		this.tentativeDate = tentativeDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}

	private String disease;

    private Date tentativeDate;
    private String priority;

    private String patientname;
    private Date bookingTime;

    public Appointment( String disease, Date tentativeDate, String priority, String patientname) {
        super();

        this.disease = disease;
        this.tentativeDate = tentativeDate;
        this.priority = priority;
        this.patientname = patientname;

    }
    
    public Appointment() {
        super();
    }

}
