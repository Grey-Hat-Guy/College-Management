package com.project.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.college.entity.LeaveRequest;
import com.project.college.repository.LeaveRequestRepository;

@Controller
public class AdminLeaveController {

	@Autowired
	private LeaveRequestRepository repository;

	@GetMapping("/admin-view-requests")
	public String adminViewRequest(Model model) {
		List<LeaveRequest> leaveRequests = repository.findByStatus("Pending");
		model.addAttribute("leaveRequests", leaveRequests);
		return "adminViewRequest";
	}

	@PostMapping("/admin-accept-request")
	public String adminAcceptRequest(@RequestParam("requestId") int requestId) {
		LeaveRequest leaveRequest = repository.findById(requestId).orElse(null);
		if (leaveRequest != null) {
			leaveRequest.setStatus("Accepted");
			repository.save(leaveRequest);
		}

		List<LeaveRequest> remainingRequests = repository.findByStatus("Pending");
		if (remainingRequests.isEmpty()) {
			return "redirect:/admindashboard";
		} else {
			return "redirect:/admin-view-requests";
		}

	}

	@PostMapping("/admin-reject-request")
	public String adminRejectRequest(@RequestParam("requestId") int requestId) {
		LeaveRequest leaveRequest = repository.findById(requestId).orElse(null);
		if (leaveRequest != null) {
			leaveRequest.setStatus("Rejected");
			repository.save(leaveRequest);
		}
		List<LeaveRequest> remainingRequests = repository.findByStatus("Pending");
		if (remainingRequests.isEmpty()) {
			return "redirect:/admindashboard";
		} else {
			return "redirect:/admin-view-requests";
		}
	}
}
