package com.project.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.college.entity.Signup;

public interface SignupRepository extends JpaRepository<Signup, Integer> {
	boolean existsByEmail(String email);

	Signup findByEmail(String email);
}
