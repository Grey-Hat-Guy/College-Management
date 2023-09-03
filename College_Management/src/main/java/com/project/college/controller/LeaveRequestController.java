package com.project.college.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.college.entity.LeaveRequest;
import com.project.college.entity.Signup;
import com.project.college.repository.LeaveRequestRepository;

@Controller
public class LeaveRequestController {

	@Autowired
	private LeaveRequestRepository requestRepository;

	@GetMapping("/leave-request")
	public String showLeaveRequestForm(Model model, HttpSession session) {
		Signup user = (Signup) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		LeaveRequest leaveRequest = requestRepository.findByUserId(user.getId());

		if (leaveRequest == null) {
			// No leave request submitted, show the form
			model.addAttribute("leaverequest", new LeaveRequest());
			return "leaveRequestForm";
		} else {
			// Leave request exists, show the view page
			model.addAttribute("leaverequest", leaveRequest);
			return "viewLeaveRequest";
		}
	}

	@PostMapping("/submit-leave")
	public String submitLeaveRequest(@ModelAttribute("leaverequest") LeaveRequest leaveRequest, HttpSession session) {
		Signup user = (Signup) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		leaveRequest.setUserId(user.getId());
		leaveRequest.setStatus("Pending");
		requestRepository.save(leaveRequest);
		return "redirect:/dashboard";
	}

	@GetMapping("/view-leave-request")
	public String viewLeaveRequest(Model model, HttpSession session) {
		Signup user = (Signup) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		LeaveRequest leaveRequest = requestRepository.findByUserId(user.getId());

		if (leaveRequest == null) {
			// No leave request submitted, show the form
			model.addAttribute("leaverequest", new LeaveRequest());
			return "leaveRequestForm";
		} else {
			// Leave request exists, show the view page
			model.addAttribute("leaverequest", leaveRequest);
			return "viewLeaveRequest";
		}
	}

	@Transactional
	@PostMapping("/withdraw-leave")
	public String withdrawLeaveRequest(HttpSession session) {
		Signup user = (Signup) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		requestRepository.deleteByUserId(user.getId());
		return "redirect:/dashboard";
	}
}
