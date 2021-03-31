package it.jac.blog.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.jac.blog.enums.Role;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")

@Entity
@Table
@Getter
@Setter
public class User extends AuditModel {
	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	@Column
	private String password;

	@Column
	private String fullName;

	@Column
	private String address;

	@Column
	private String phone;

	@OneToOne(cascade = CascadeType.ALL)
	private Image photo;

	@Column
	@Enumerated(EnumType.STRING)
	private Role role;

}
