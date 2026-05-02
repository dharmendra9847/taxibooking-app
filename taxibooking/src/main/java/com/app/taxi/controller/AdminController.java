package com.app.taxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.taxi.service.AdminUpdateProfileService;
import com.app.taxi.service.BookingFormService;
import com.app.taxi.service.ContactFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ContactFormService contactFormService;
	private AdminUpdateProfileService adminUpdateProfileService;
	private BookingFormService bookingFormService;
	
	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}
	
	@Autowired	
	public void setAdminUpdateProfileService(AdminUpdateProfileService adminUpdateProfileService) {
		this.adminUpdateProfileService = adminUpdateProfileService;
	}
	
	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
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
	
	@GetMapping("readallbookings")
	public String readAllBookings(Model model) {
	    model.addAttribute("allbookings", bookingFormService.readAllBookingService());
	    return "admin/readallbookings";
	}
	
	@GetMapping("update-profile")
	public String adminProfileView() {
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
	
	@PostMapping("update-profile")
	public String updateAdminProfile(
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newpassword") String newpassword,
			
			RedirectAttributes redirectAttributes
			) {
		
		String checkCredentials = adminUpdateProfileService.checkCredentials(username, oldpassword);
		if (checkCredentials.equals("SUCCESS")) {
			
			// UPDATE PASSWORD
			//String updateAdminCredentials = adminUpdateProfileService.updateAdminCredentials(email, newpassword, username);
			redirectAttributes.addFlashAttribute("updateSuccess", true);
			redirectAttributes.addFlashAttribute("message", "Your profile has been updated successfully.");
		} else {
			redirectAttributes.addFlashAttribute("updateSuccess", false);
			redirectAttributes.addFlashAttribute("message", "Incorrect credentials. Please try again.");
		}
		
		//System.out.println(checkCredentials);
		//redirectAttributes.addFlashAttribute("updateSuccess", true);
		return "redirect:/admin/dashboard";
	}
	
	@GetMapping("/deleteBooking/{id}")
	public String deleteBooking(@PathVariable() int id, RedirectAttributes redirectAttributes) {
	    try {
	    		bookingFormService.deleteBookingById(id); 
	        redirectAttributes.addFlashAttribute("success", "Success! The booking record (ID: " + id + ") has been permanently removed from the system.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "System Error: We encountered an issue while attempting to delete the record. Please refresh and try again or contact support if the issue persists.");
	    }
	    return "redirect:/admin/readallbookings";
	}
}
