package com.project.college.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.college.entity.AdminLogin;
import com.project.college.entity.AdminSignup;
import com.project.college.repository.AdminSignupRepository;

@Controller
public class AdminLoginController {

	@Autowired
	private AdminSignupRepository adminSignupRepository;

	@GetMapping("/adminlogin")
	public String showAdminLogin(Model model) {
		model.addAttribute("adminLogin", new AdminLogin());
		return "adminlogin";
	}

	@PostMapping("/adminlogin")
	public String processLogin(@ModelAttribute("adminLogin") AdminLogin adminLogin, BindingResult result) {
		AdminSignup admin = adminSignupRepository.findByEmail(adminLogin.getEmail());

		if (admin == null) {
			result.rejectValue("email", "error.login", "Email not found");
			return "adminlogin";
		}

		if (!BCrypt.checkpw(adminLogin.getPassword(), admin.getPassword())) {
			result.rejectValue("password", "error.login", "Incorrect Password");
			return "adminlogin";
		}

		return "redirect:/admindashboard";
	}
}
