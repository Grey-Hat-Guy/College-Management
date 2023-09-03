package com.project.college.controller;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.college.entity.LoginForm;
import com.project.college.entity.Signup;
import com.project.college.entity.StudentRegistration;
import com.project.college.repository.RegistrationRepository;
import com.project.college.repository.SignupRepository;

@Controller
public class LoginController {

	@Autowired
	private SignupRepository repository;

	@Autowired
	private RegistrationRepository registrationRepository;

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("loginform", new LoginForm());
		return "loginPage";
	}

	@PostMapping("/login")
	public String performLogin(@ModelAttribute("loginform") LoginForm loginForm, HttpSession session,
			BindingResult result) {
		Signup user = repository.findByEmail(loginForm.getEmail());

		if (user == null) {
			result.rejectValue("email", "error.login", "Email not found");
			return "loginPage";
		}

		if (!BCrypt.checkpw(loginForm.getPassword(), user.getPasswd())) {
			result.rejectValue("password", "error.login", "Incorrect Password");
			return "loginPage";
		}

		session.setAttribute("user", user);

		StudentRegistration studentRegistration = registrationRepository
				.findByUniqueIdentifier(user.getUniqueIdentifier());
		if (studentRegistration == null) {
			return "redirect:/registration";
		}
		return "redirect:/dashboard";

	}
}
