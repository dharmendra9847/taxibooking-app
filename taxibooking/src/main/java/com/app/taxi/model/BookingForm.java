package com.app.taxi.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "booking_form")
public class BookingForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(length = 50)
    private String name;

    @NotBlank(message = "Pickup location is required")
    @Column(length = 100, name = "pickup_location")
    private String from;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    @Column(length = 50)
    private String email;

    @NotBlank(message = "Destination is required")
    @Column(length = 100, name = "dropoff_location")
    private String to;

    @NotNull(message = "Time is required")
    private LocalTime time;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate date;
    
    @NotBlank(message = "Please select a comfort level")
    @Column(length = 20)
    private String comfort;

    @Min(value = 1, message = "At least one adult is required")
    @Max(value = 4, message = "Maximum 4 adults allowed")
    private int adult;

    @Min(value = 0, message = "Children count cannot be negative")
    @Max(value = 3, message = "Maximum 3 adults allowed")
    private int children;
    
    @NotBlank(message = "Message is required")
    @Size(min = 10, max = 500, message = "Message must not exceed 500 characters")
    @Column(length = 500)
    private String message;
}
