package com.app.taxi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "contact_form")
public class ContactForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name can only contain alphabets and spaces")
	@Column(length = 30)
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
	@Column(length = 50)
	private String email;

	@NotNull(message = "Phone number is required")
	@Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits")
	@Column(length = 10)
	private Long phone;

	@NotBlank(message = "Message cannot be empty")
	@Size(min = 10, max = 2000, message = "Message must be between 10 and 2000 characters")
	@Column(length = 300)
	private String message;
}
