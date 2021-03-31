package it.jac.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")

@Entity
@Table
@Getter
@Setter
public class Tag extends AuditModel {

	@Column(name = "code")
	private String code;

}
