package com.nts.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nts.model.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String empId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String gender;

	private String email;

	@JsonIgnore
	private String password;
	
	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dob;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date doj;

	private String reportingManager;

	private String mobileNumber;
	
	private Set<String> permissions;
	private Set<String> roles;
	private Collection<? extends GrantedAuthority> authorities;

	public EmployeeDetailsImpl(String empId, String firstName, String middleName, String lastName, String gender,
			String email, String password, String status, Date dob, Date doj, String reportingManager,
			String mobileNumber, Set<String> roles, Set<String> permissions, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.status = status;
		this.dob = dob;
		this.doj = doj;
		this.reportingManager = reportingManager;
		this.mobileNumber = mobileNumber;
		this.roles = roles;
		this.permissions = permissions;
		this.authorities = authorities;
	}
	
	public static EmployeeDetailsImpl build(Employee employee, RolesAndPermisssions rolesAndPermisssions) {
		if(null == rolesAndPermisssions) {
			return new EmployeeDetailsImpl(employee.getEmpId(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), 
					employee.getGender(), employee.getEmail(), employee.getPassword(), 
					employee.getStatus(), employee.getDob(), employee.getDoj(), employee.getReportingManager(),
					employee.getMobileNumber(), null, null, null);
		}
		List<GrantedAuthority> authorities = rolesAndPermisssions.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return new EmployeeDetailsImpl(employee.getEmpId(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), 
				employee.getGender(), employee.getEmail(), employee.getPassword(), 
				employee.getStatus(), employee.getDob(), employee.getDoj(), employee.getReportingManager(),
				employee.getMobileNumber(), null, null, null);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmployeeDetailsImpl employee = (EmployeeDetailsImpl) o;
		return Objects.equals(email, employee.email);
	}

}