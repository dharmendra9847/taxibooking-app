package com.app.taxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.taxi.dao.BookingFormCrud;
import com.app.taxi.model.BookingForm;

@Service
public class BookingFormServiceImpl implements BookingFormService{
	
	private BookingFormCrud bookingFormCrud;
	
	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}

	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {
		return bookingFormCrud.save(bookingForm);
	}

}
