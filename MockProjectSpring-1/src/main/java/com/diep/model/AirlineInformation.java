package com.diep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents information about an airline.
 * Account: DiepDP1
 * Birthday: 1998-02-27
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Airline_Information")
public class AirlineInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Airline_id")
	private long id;
	
	@Column(name = "Airline_Code", columnDefinition = "nvarchar(5)", unique = false)
	@Pattern(regexp = "^SG\\d{3}$", message = "Airline Code must be in the foramt of alpha numeric, must start with SG and 3 numbers")
	@NotNull(message="Airline Code must be entered")
	private String airlineCode;
	@Column(name = "Airline_Name", columnDefinition = "nvarchar(45)")
	@Pattern(regexp ="^[a-zA-Z0-9 ]+$" ,message = "Airline Name must be entered and contain no special characters")
	private String airlineName;
	@Column(name="Wholesaler_PCC", columnDefinition = "nvarchar(5)")
	@Pattern(regexp = "^WP\\d{3}$", message = "Wholesaler PCC must be in the format of alpha numeric, must start with WP and 3 numbers")
	private String wholesalerPCC;
	
	@Column(name = "Contact_Number", columnDefinition = "nvarchar(9)")
	@Pattern(regexp = "^\\d{9}$", message = "Contact Number must be a number and in the format of: +650123456")
	@NotNull(message="Contact Number must be entered")
	private String contactNumber;
	
	@Column(name="Email", columnDefinition = "nvarchar(50)")
	@Pattern(regexp="\\w+@fpt\\.com$", message = "Email must be in the format of abc@fpt.com")
	private String email;
}
