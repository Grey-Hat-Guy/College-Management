package com.project.college.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "signup")
public class Signup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String passwd;
	@Transient
	private String confirmPwd;
	private String uniqueIdentifier;

	public Signup() {

	}

	public Signup(int id, String email, String passwd, String confirmPwd) {
		super();
		this.id = id;
		this.email = email;
		this.passwd = passwd;
		this.confirmPwd = confirmPwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	@Override
	public String toString() {
		return "Signup [id=" + id + ", email=" + email + ", passwd=" + passwd + ", confirmPwd=" + confirmPwd + "]";
	}

}
