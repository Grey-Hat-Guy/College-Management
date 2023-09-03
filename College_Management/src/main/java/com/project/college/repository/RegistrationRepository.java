package com.project.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.college.entity.StudentRegistration;

public interface RegistrationRepository extends JpaRepository<StudentRegistration, Long> {

	boolean existsByRegno(String regno);

	StudentRegistration findByRegno(String regno);

	StudentRegistration findByUniqueIdentifier(String uniqueIdentifier);

	@Query("SELECT s from StudentRegistration s WHERE " + "LOWER(s.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.regno) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.fathername) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.mothername) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.address) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.mobile) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.percent_10) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.yearofpass_10) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.percent_12) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.yearofpass_12) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.degree) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(s.aadhar_num) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	List<StudentRegistration> findBySearchTerm(@Param("searchTerm") String searchTerm);

	@Query(value = "SELECT sr FROM StudentRegistration sr")
	List<StudentRegistration> fetchAllData();
}
