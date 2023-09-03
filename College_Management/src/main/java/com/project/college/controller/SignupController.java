package com.project.college.controller;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.college.entity.Signup;
import com.project.college.repository.SignupRepository;

@Controller
public class SignupController {

	@Autowired
	private SignupRepository signupRepository;

	@GetMapping("/")
	public String signupPage(Model model) {
		model.addAttribute("signup", new Signup());
		return "signupPage";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("signup") @Valid Signup signup, BindingResult result) {

		if (signupRepository.existsByEmail(signup.getEmail())) {
			result.rejectValue("email", "error.signup", "Email already taken");
		}

		if (signup.getPasswd().length() < 4) {
			result.rejectValue("passwd", "field.minSize", "Password must be at least 4 characters long");
		}

		if (!signup.getPasswd().equals(signup.getConfirmPwd())) {
			result.rejectValue("confirmPwd", "field.match", "Password do not match");
		}

		if (result.hasErrors()) {
			return "signupPage";
		}
		String hashedPassword = BCrypt.hashpw(signup.getPasswd(), BCrypt.gensalt(12));
		signup.setPasswd(hashedPassword);
		signupRepository.save(signup);
		return "redirect:/login";
	}
}
