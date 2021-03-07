package com.doctorlabel.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.doctorlabel.model.Label;

public class LabelForm {
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String id;

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String description;

	private LocalDateTime dateCreate = LocalDateTime.now();


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Label convert() {
		return new Label(id, description, dateCreate);
	}
}