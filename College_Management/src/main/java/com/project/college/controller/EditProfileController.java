package com.project.college.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.college.entity.Signup;
import com.project.college.entity.StudentRegistration;
import com.project.college.repository.RegistrationRepository;

@Controller
public class EditProfileController {

	@Autowired
	private RegistrationRepository registrationRepository;

	@GetMapping("/editprofile")
	public String showEditProfile(Model model, HttpSession session) {
		Signup user = (Signup) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		StudentRegistration studentRegistration = registrationRepository
				.findByUniqueIdentifier(user.getUniqueIdentifier());
		model.addAttribute("studentreg", studentRegistration);

		return "edit_profile";
	}

	@PostMapping("/editprofile")
	public String updateProfile(@ModelAttribute("studentreg") StudentRegistration updatedStudentRegistration,
			HttpSession session) {
		Signup user = (Signup) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		StudentRegistration existingStudentRegistration = registrationRepository
				.findByUniqueIdentifier(user.getUniqueIdentifier());

		existingStudentRegistration.setName(updatedStudentRegistration.getName());
		existingStudentRegistration.setRegno(updatedStudentRegistration.getRegno());
		existingStudentRegistration.setFathername(updatedStudentRegistration.getFathername());
		existingStudentRegistration.setMothername(updatedStudentRegistration.getMothername());
		existingStudentRegistration.setDegree(updatedStudentRegistration.getDegree());
		existingStudentRegistration.setAddress(updatedStudentRegistration.getAddress());
		existingStudentRegistration.setMobile(updatedStudentRegistration.getMobile());
		existingStudentRegistration.setPercent_10(updatedStudentRegistration.getPercent_10());
		existingStudentRegistration.setYearofpass_10(updatedStudentRegistration.getYearofpass_10());
		existingStudentRegistration.setPercent_12(updatedStudentRegistration.getPercent_12());
		existingStudentRegistration.setYearofpass_12(updatedStudentRegistration.getYearofpass_12());
		existingStudentRegistration.setAadhar_num(updatedStudentRegistration.getAadhar_num());

		registrationRepository.save(existingStudentRegistration);

		return "redirect:/dashboard";
	}
}
