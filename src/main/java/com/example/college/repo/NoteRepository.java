package com.example.college.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.college.entity.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
List<Note> findBySubjectCodeIgnoreCase(String subjectCode);
}
 
