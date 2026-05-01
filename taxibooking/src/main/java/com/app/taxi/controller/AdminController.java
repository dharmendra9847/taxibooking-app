package com.app.taxi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.taxi.model.Admin;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerAdmin(@Valid @RequestBody Admin admin) {
	    // Logic here
	    return ResponseEntity.ok("Admin is valid");
	}
}
