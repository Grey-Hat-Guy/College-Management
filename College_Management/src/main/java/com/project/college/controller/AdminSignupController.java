package com.project.college.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.college.entity.AdminSignup;
import com.project.college.repository.AdminSignupRepository;

@Controller
public class AdminSignupController {

	@Autowired
	private AdminSignupRepository adminSignupRepository;

	@GetMapping("/adminsignup")
	public String showAdminSignup(Model model) {
		model.addAttribute("signup", new AdminSignup());
		return "adminsignup";
	}

	@PostMapping("/adminsignup")
	public String performSignup(@ModelAttribute("signup") AdminSignup adminSignup, BindingResult result) {

		if (adminSignupRepository.existsByEmail(adminSignup.getEmail())) {
			result.rejectValue("email", "error.signup", "Email already taken");
		}

		if (adminSignup.getPassword().length() < 4) {
			result.rejectValue("password", "field.minSize", "Password must be atleast 4 characters long");
		}

		if (!adminSignup.getPassword().equals(adminSignup.getConfirm_password())) {
			result.rejectValue("confirm_password", "field.match", "Password do not match");
		}

		if (result.hasErrors()) {
			return "adminsignup";
		}
		String hashPassword = BCrypt.hashpw(adminSignup.getPassword(), BCrypt.gensalt(12));
		adminSignup.setPassword(hashPassword);
		adminSignupRepository.save(adminSignup);
		return "redirect:/adminlogin";
	}
}
