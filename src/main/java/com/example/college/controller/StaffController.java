package com.example.college.controller;
import com.example.college.entity.Staff;
import com.example.college.repo.StaffRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping
    public List<Staff> all(@RequestParam(value = "department", required = false) String dept) {
        if (dept != null && !dept.isBlank()) {
            return staffRepository.findByDepartmentIgnoreCase(dept);
        }
        return staffRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> one(@PathVariable Integer id) {
        return staffRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Staff> create(@RequestBody @Valid Staff staff) {
        Staff saved = staffRepository.save(staff);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> update(@PathVariable Integer id, @RequestBody @Valid Staff body) {
        return staffRepository.findById(id)
                .map(existing -> {
                    existing.setName(body.getName());
                    existing.setDesignation(body.getDesignation());
                    existing.setDepartment(body.getDepartment());
                    existing.setRoom_number(body.getRoom_number());
                    existing.setEmail(body.getEmail());
                    existing.setPhone_number(body.getPhone_number());
                    existing.setOther_details(body.getOther_details());
                    return ResponseEntity.ok(staffRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!staffRepository.existsById(id)) return ResponseEntity.notFound().build();
        staffRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}