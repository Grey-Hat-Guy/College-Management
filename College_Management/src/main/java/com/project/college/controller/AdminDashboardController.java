package com.project.college.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.college.entity.StudentRegistration;
import com.project.college.repository.RegistrationRepository;

@Controller
public class AdminDashboardController {

	@Autowired
	private RegistrationRepository registrationRepository;

	@GetMapping("/admindashboard")
	public String showAdminDashboard(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			Model model) {
		List<StudentRegistration> studentDetailList;

		if (searchTerm != null && !searchTerm.isEmpty()) {
			studentDetailList = registrationRepository.findBySearchTerm(searchTerm);
			model.addAttribute("studentDetailList", studentDetailList);
			return "fragments/studentTableDetail :: studentList";
		} else {
			studentDetailList = registrationRepository.findAll();
			model.addAttribute("studentDetailList", studentDetailList);
			return "adminDashBoard";
		}
	}

	@GetMapping("/download-details")
	public ResponseEntity<ByteArrayResource> downloadDetails(
			@RequestParam("selectedItems") List<String> selectedItems) {
		List<StudentRegistration> allData = registrationRepository.fetchAllData();

		List<String> detailHeaders = selectedItems;

		String csvContent = generateCSV(allData, detailHeaders);

		byte[] contentBytes = csvContent.getBytes();
		ByteArrayResource resource = new ByteArrayResource(contentBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student_details.csv");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/csv"))
				.body(resource);
	}

	private String generateCSV(List<StudentRegistration> allData, List<String> headers) {
		StringBuilder csvBuilder = new StringBuilder();
		csvBuilder.append(String.join(",", headers)).append("\n");

		for (StudentRegistration details : allData) {
			List<String> rowData = new ArrayList<>();
			for (String header : headers) {
				try {
					Field field = details.getClass().getDeclaredField(header.toLowerCase());
					field.setAccessible(true);
					Object value = field.get(details);
					rowData.add(value != null ? value.toString() : "");
				} catch (Exception e) {
					rowData.add("");
				}
			}
			csvBuilder.append(String.join(",", rowData)).append("\n");
		}

		return csvBuilder.toString();
	}
}