package it.jac.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.jac.blog.enums.Status;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table
@Getter
@Setter
public class Comment extends AuditModel {

	@Column(name = "text", length = 1000)
	private String text;

	@ManyToOne
	private User author;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status;

}
