package com.example.college.controller;
import com.example.college.entity.Note;
import com.example.college.repo.NoteRepository;
import com.example.college.service.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
//import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteRepository noteRepository;
    private final FileStorageService storageService;

    public NoteController(NoteRepository noteRepository, FileStorageService storageService) {
        this.noteRepository = noteRepository;
        this.storageService = storageService;
    }

    @GetMapping
    public List<Note> all(@RequestParam(value = "subject_code", required = false) String code) {
        if (code != null && !code.isBlank()) {
            return noteRepository.findBySubjectCodeIgnoreCase(code);
        }
        return noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> one(@PathVariable Integer id) {
        return noteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> upload(
            @RequestParam String subject_code,
            @RequestParam(required = false) String subject_name,
            @RequestParam(required = false) Integer uploader_staff_id,
            @RequestParam(required = false) String description,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (file.isEmpty()) return ResponseEntity.badRequest().body("File is required");
        String storedName = storageService.store(file);

        Note note = new Note();
        note.setSubject_code(subject_code);
        note.setSubject_name(subject_name);
        note.setDescription(description);
        note.setFile_path(storedName);

        //if (uploader_staff_id != null) {
           /// Optional<Staff> up = staffRepository.findById(uploader_staff_id);
            //up.ifPresent(note::setUploader);
        //}

        Note saved = noteRepository.save(note);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!noteRepository.existsById(id)) return ResponseEntity.notFound().build();
        noteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
