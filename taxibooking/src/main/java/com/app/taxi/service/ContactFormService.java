package com.app.taxi.service;

import java.util.List;

import com.app.taxi.model.ContactForm;

public interface ContactFormService {
	
	public ContactForm saveContactFormService(ContactForm contactForm);
	
	public List<ContactForm> readAllContactsService();

	public void deleteContactById(int id);
}
