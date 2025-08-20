package com.example.college.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "notes")

public class Note {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer note_id;
@Column(nullable = false)
private String subjectCode;
private String subject_name;
@ManyToOne
@JoinColumn(name = "uploader_staff_id")

private LocalDateTime upload_date = LocalDateTime.now();
@Column(nullable = false)
private String file_path; // relative path under uploads/
@Column(columnDefinition = "TEXT")
private String description;
public Integer getNote_id() { return note_id; }
public void setNote_id(Integer note_id) { this.note_id = note_id; }
public String getSubject_code() { return subjectCode; }
public void setSubject_code(String subject_code) { this.subjectCode =
subject_code; }
public String getSubject_name() { return subject_name; }
public void setSubject_name(String subject_name) { this.subject_name =
subject_name; }

public LocalDateTime getUpload_date() { return upload_date; }
public void setUpload_date(LocalDateTime upload_date) { this.upload_date =
upload_date; }
public String getFile_path() { return file_path; }
public void setFile_path(String file_path) { this.file_path = file_path; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description =
description; }
}

