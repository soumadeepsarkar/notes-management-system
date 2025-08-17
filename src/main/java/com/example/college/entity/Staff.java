package com.example.college.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "staff")
public class Staff {
@Id

@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer staff_id;
@NotBlank
private String name;
private String designation;
private String department;
private String room_number;
@Email
private String email;
private String phone_number;
@Column(columnDefinition = "TEXT")
private String other_details;
public Integer getStaff_id() { return staff_id; }
public void setStaff_id(Integer staff_id) { this.staff_id = staff_id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getDesignation() { return designation; }
public void setDesignation(String designation) { this.designation =
designation; }
public String getDepartment() { return department; }
public void setDepartment(String department) { this.department =
department; }
public String getRoom_number() { return room_number; }
public void setRoom_number(String room_number) { this.room_number =
room_number; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getPhone_number() { return phone_number; }
public void setPhone_number(String phone_number) { this.phone_number =
phone_number; }
public String getOther_details() { return other_details; }
public void setOther_details(String other_details) { this.other_details =
other_details; }
}

