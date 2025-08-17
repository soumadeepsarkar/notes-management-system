package com.example.college.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

 @Service
   public class FileStorageService {
    private final Path uploadRoot = Paths.get("uploads");
     public FileStorageService() throws IOException {
if (!Files.exists(uploadRoot)) {
Files.createDirectories(uploadRoot);
}
}
public String store(MultipartFile file) throws IOException {
String original = file.getOriginalFilename();
String ext = "";
if (original != null && original.contains(".")) {
ext = original.substring(original.lastIndexOf('.'));

}
String filename = UUID.randomUUID() + ext;
Path dest = uploadRoot.resolve(filename);
Files.copy(file.getInputStream(), dest,
StandardCopyOption.REPLACE_EXISTING);
return filename; // relative name inside uploads/
}
}