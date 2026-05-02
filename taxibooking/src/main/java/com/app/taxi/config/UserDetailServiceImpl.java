package com.app.taxi.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.taxi.dao.AdminRepository;
import com.app.taxi.model.Admin;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
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

	@PostConstruct
	public void init() {
		long count = adminRepository.count();
		if (count == 0) {
			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setEmail("Admin@123.com"); 
			admin.setPassword(passwordEncoder.encode("admin123"));
			
			adminRepository.save(admin);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Admin> byUsername = adminRepository.findByUsername(username);
		
		// Find admin or throw exception immediately if not found
	    Admin admin = byUsername
	            .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));

	    // Return a Spring Security User object
	    return User.builder()
	            .username(admin.getUsername())
	            .password(admin.getPassword())
	            .roles("ADMIN") // Assign the role here
	            .build();
	}

}
