package com.example.CustomerSystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.CustomerSystem.service.ImportService;

@Controller
public class ImportController {
	
	@Autowired
	private ImportService importService;
	
	@GetMapping("/import")
	public String handleImport() {
		return "Import";
	}
	
	
	@PostMapping("/upload")
	 public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
		if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        // Xử lý tập tin ở đây
        String fileName = file.getOriginalFilename();
        importService.handleImport(file);
        return ResponseEntity.ok("File uploaded successfully. Data saved to data.txt." + fileName);
    }
}
