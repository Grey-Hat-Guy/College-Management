package com.project.college.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.college.entity.Signup;
import com.project.college.entity.StudentRegistration;
import com.project.college.repository.RegistrationRepository;
import com.project.college.repository.SignupRepository;

@Controller
public class RegistrationController {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private SignupRepository signupRepository;

	@GetMapping("/registration")
	public String showRegForm(Model model) {
		model.addAttribute("studentreg", new StudentRegistration());
		return "studentRegistration";
	}

	@PostMapping("/registration")
	public String performRegister(@ModelAttribute("studentreg") StudentRegistration studentRegistration,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "studentRegistration";
		}
		Signup user = (Signup) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		if (registrationRepository.existsByRegno(studentRegistration.getRegno())) {
			result.rejectValue("regno", "error.studentreg", "Register Number already exists");
			return "studentRegistration";
		}
		String uniqueIdentifier = UUID.randomUUID().toString();
		user.setUniqueIdentifier(uniqueIdentifier);
		signupRepository.save(user);

		studentRegistration.setUniqueIdentifier(uniqueIdentifier);
		registrationRepository.save(studentRegistration);

		return "dashboard";
	}
}
