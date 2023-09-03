package com.project.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.college.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

	LeaveRequest findByUserId(int id);

	void deleteByUserId(int id);

	List<LeaveRequest> findByStatus(String string);

}
