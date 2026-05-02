package com.app.taxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.taxi.model.Admin;
import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value = "update admin set email=:email, password=:newpassword where username=:username", nativeQuery = true)
	public int updateAdminCredential(
			@Param("email") String email,
			@Param("newpassword") String newpassword,
			@Param("username") String username
			);
}
