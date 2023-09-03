package com.project.college.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_details")
public class StudentRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@Column(unique = true)
	private String regno;

	private String fathername;
	private String mothername;
	private String address;
	private String mobile;
	private String percent_10;
	private String yearofpass_10;
	private String percent_12;
	private String yearofpass_12;
	private String degree;
	private String aadhar_num;
	private String uniqueIdentifier;

	public StudentRegistration() {

	}

	public StudentRegistration(String name, String regno, String fathername, String mothername, String address,
			String mobile, String percent_10, String yearofpass_10, String percent_12, String yearofpass_12,
			String degree, String aadhar_num) {
		this.name = name;
		this.regno = regno;
		this.fathername = fathername;
		this.mothername = mothername;
		this.address = address;
		this.mobile = mobile;
		this.percent_10 = percent_10;
		this.yearofpass_10 = yearofpass_10;
		this.percent_12 = percent_12;
		this.yearofpass_12 = yearofpass_12;
		this.degree = degree;
		this.aadhar_num = aadhar_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPercent_10() {
		return percent_10;
	}

	public void setPercent_10(String percent_10) {
		this.percent_10 = percent_10;
	}

	public String getYearofpass_10() {
		return yearofpass_10;
	}

	public void setYearofpass_10(String yearofpass_10) {
		this.yearofpass_10 = yearofpass_10;
	}

	public String getPercent_12() {
		return percent_12;
	}

	public void setPercent_12(String percent_12) {
		this.percent_12 = percent_12;
	}

	public String getYearofpass_12() {
		return yearofpass_12;
	}

	public void setYearofpass_12(String yearofpass_12) {
		this.yearofpass_12 = yearofpass_12;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getAadhar_num() {
		return aadhar_num;
	}

	public void setAadhar_num(String aadhar_num) {
		this.aadhar_num = aadhar_num;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	@Override
	public String toString() {
		return "StudentRegistration [id=" + id + ", name=" + name + ", regno=" + regno + ", fathername=" + fathername
				+ ", mothername=" + mothername + ", address=" + address + ", mobile=" + mobile + ", percent_10="
				+ percent_10 + ", yearofpass_10=" + yearofpass_10 + ", percent_12=" + percent_12 + ", yearofpass_12="
				+ yearofpass_12 + ", degree=" + degree + ", aadhar_num=" + aadhar_num + "]";
	}

}
