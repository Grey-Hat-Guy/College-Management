package com.project.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.college.entity.AdminSignup;

public interface AdminSignupRepository extends JpaRepository<AdminSignup, Integer> {

	boolean existsByEmail(String email);

	AdminSignup findByEmail(String email);

}
