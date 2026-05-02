package com.app.taxi.service;

public interface AdminUpdateProfileService {
	
	public String checkCredentials(String username, String oldpassword);
	public String updateAdminCredentials(String email, String newpassword, String username);
}
