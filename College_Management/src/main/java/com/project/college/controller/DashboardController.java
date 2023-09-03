package com.project.college.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.college.entity.Signup;
import com.project.college.entity.StudentRegistration;
import com.project.college.repository.RegistrationRepository;

@Controller
public class DashboardController {

	@Autowired
	private RegistrationRepository registrationRepository;

	@GetMapping("/dashboard")
	public String showDashboard(Model model, HttpSession session) {
		Signup user = (Signup) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		StudentRegistration studentRegistration = registrationRepository
				.findByUniqueIdentifier(user.getUniqueIdentifier());
		model.addAttribute("studentreg", studentRegistration);

		return "dashboard";
	}
}
