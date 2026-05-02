package com.app.taxi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.taxi.dao.AdminRepository;
import com.app.taxi.model.Admin;

@Service
public class AdminUpdateProfileServiceImpl implements AdminUpdateProfileService {
	
	private AdminRepository adminRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setAdminRepository(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String checkCredentials(String username, String oldpassword) {
		
		Optional<Admin> byUsername = adminRepository.findByUsername(username);
		if (byUsername.isPresent()) {
			Admin admin = byUsername.get();
			boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
			if (matches) {
				return "SUCCESS";
			} else {
				return "Oop's! Something wents wrong!";
			}
		} else {
			return "Invalid credentials";
		}
	}

	@Override
	public String updateAdminCredentials(String email, String newpassword, String username) {
		
		int updateAdminCredential = adminRepository.updateAdminCredential(email, passwordEncoder.encode(newpassword), username);
		if (updateAdminCredential == 1) {
			return "Success! Your profile details and security settings are now up to date.";
		} else {
			return "Update failed. We couldn't save your changes. Please verify your details and try again.";
		}
	}

}
