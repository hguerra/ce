package org.carneiro.ce.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author heitor
 * @since 01/02/17
 */

@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
