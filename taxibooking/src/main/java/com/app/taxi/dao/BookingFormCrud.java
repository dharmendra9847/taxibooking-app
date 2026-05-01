package com.app.taxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.taxi.model.BookingForm;

@Repository
public interface BookingFormCrud extends JpaRepository<BookingForm, Integer>{
	@Override
	public <S extends BookingForm> S save(S entity);
}
