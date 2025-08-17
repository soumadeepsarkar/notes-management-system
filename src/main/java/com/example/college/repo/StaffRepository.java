package com.example.college.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.college.entity.Staff;

import java.util.List;
public interface StaffRepository extends JpaRepository<Staff, Integer> {
List<Staff> findByDepartmentIgnoreCase(String department);
}


