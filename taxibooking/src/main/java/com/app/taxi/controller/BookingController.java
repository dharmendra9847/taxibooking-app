package com.app.taxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.taxi.model.BookingForm;
import com.app.taxi.model.ContactForm;
import com.app.taxi.service.BookingFormService;
import com.app.taxi.service.ContactFormService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class BookingController {
	
	private ContactFormService contactFormService;
	private BookingFormService bookingFormService;
	
	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@GetMapping(path = {"/", "/home", "/index", "/welcome"})
	public String welcomeView(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		model.addAttribute("bookingForm", new BookingForm());
		return "index";
	}
	
	@GetMapping(path = {"/about"})
	public String about(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		return "about";
	}
	
	@GetMapping(path = {"/cars"})
	public String carsView(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		return "cars";
	}
	
	@GetMapping(path = {"/services"})
	public String servicesView(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		return "services";
	}
	
	@GetMapping(path = {"/contacts"})
	public String contactsView(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		model.addAttribute("contactForm", new ContactForm());
		return "contacts";
	}
	
	@PostMapping(path = {"/contactsform"})
	public String contactsForm(@Valid @ModelAttribute ContactForm contactForm, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			return "contacts";
		}
		
		ContactForm saveContactFormService = contactFormService.saveContactFormService(contactForm);
		if(saveContactFormService != null) {
		    redirectAttributes.addFlashAttribute("success", "Message sent! Our team is on it and will be in touch shortly.");
		} else {
		    redirectAttributes.addFlashAttribute("error", "Message failed to send. Please check your connection and try again.");
		}

		
		return "redirect:/contacts";
	}
	
	@PostMapping(path = {"/bookingform"})
	public String bookingForm(@Valid @ModelAttribute BookingForm bookingForm, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			return "index";
		} else if (bookingForm.getAdult() + bookingForm.getChildren() > 4) {
			model.addAttribute("message", "limit.exceeded" + ", " + "The total number of adult and children cannot exceed 4.");
			return "index";
		}
		
		// Service Call
		BookingForm saveBookingFormService = bookingFormService.saveBookingFormService(bookingForm);
		if(saveBookingFormService != null) {
		    redirectAttributes.addFlashAttribute("success", "Pack your bags! Your booking is confirmed and your adventure starts soon. ✈️");
		} else {
		    redirectAttributes.addFlashAttribute("error", "Oops! We hit a small bump in the road. Please try booking again in a moment.");
		}
		
		return "redirect:/index";
	}
	
	@GetMapping("/login")
	public String adminLoginView(HttpServletRequest request, Model model) {
		
//		ServletContext servletContext = request.getServletContext();
//		Object attribute = servletContext.getAttribute("logout");
//		if (attribute instanceof Boolean) {
//			model.addAttribute("logout", attribute);
//			servletContext.removeAttribute("logout");
//		}
		
		return "adminlogin";
	}
	
	@GetMapping("/logout")
	public String adminLogoutView() {
		return "adminlogout";
	}
}
