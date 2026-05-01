package com.app.taxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.taxi.model.Admin;
import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByUsername(String username);
}
