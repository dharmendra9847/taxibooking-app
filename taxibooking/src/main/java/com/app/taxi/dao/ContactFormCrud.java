package com.app.taxi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.taxi.model.ContactForm;

@Repository
public interface ContactFormCrud extends JpaRepository<ContactForm, Integer> {
	
	@Override
	public <S extends ContactForm> S save(S entity);
	
	@Override
	public List<ContactForm> findAll();
}
