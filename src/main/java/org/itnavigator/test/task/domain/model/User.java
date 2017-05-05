package org.itnavigator.test.task.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "phone")
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "patronymic")
	private String patronymic;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Phone phone;
}
