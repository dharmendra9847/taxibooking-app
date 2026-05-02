package com.app.taxi.service;

import java.util.List;

import com.app.taxi.model.BookingForm;

public interface BookingFormService {
	
	public BookingForm saveBookingFormService(BookingForm bookingForm);
	public List<BookingForm> readAllBookingService();
	public void deleteBookingById(int id);
}
