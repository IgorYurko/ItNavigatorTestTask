package org.itnavigator.test.task.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = "user")
@Table(name = "phone")
public class Phone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "type")
	@Enumerated(value = EnumType.STRING)
	private Type type;
	
	@Column(name = "comment")
	private String comment;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public enum Type {
		HOME, MOBILE, NO_INFORMATION
	}
}
