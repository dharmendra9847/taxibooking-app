package com.app.taxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.taxi.service.ContactFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ContactFormService contactFormService;
	
	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}
	
	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}
	
	@GetMapping("readallcontacts")
	public String readAllContacts(Model model){
		
		model.addAttribute("allcontacts", contactFormService.readAllContactsService());
		return "admin/readallcontacts";
	}
	
	@PostMapping("update-profile")
	public String updateAdminProfile() {
		return "admin/update-profile";
	}
	
	@GetMapping("/deleteContact/{id}")
	public String deleteContact(@PathVariable() int id, RedirectAttributes redirectAttributes) {
	    try {
	        contactFormService.deleteContactById(id); 
	        redirectAttributes.addFlashAttribute("success", "Success! The contact record (ID: " + id + ") has been permanently removed from the system.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "System Error: We encountered an issue while attempting to delete the record. Please refresh and try again or contact support if the issue persists.");
	    }
	    return "redirect:/admin/readallcontacts";
	}
}
