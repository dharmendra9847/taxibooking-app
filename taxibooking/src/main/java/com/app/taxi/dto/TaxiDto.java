package com.app.taxi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaxiDto {

	private int id;
	private String name;
	private String email;
	private double salary;
}
