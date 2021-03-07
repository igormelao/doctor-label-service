package com.doctorlabel.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.doctorlabel.model.Label;
import com.doctorlabel.repository.LabelRepository;

public class LabelForm {
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String code;

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String description;

	private LocalDateTime dateCreate = LocalDateTime.now();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Label convert(LabelRepository labelRepository) {
		return new Label(code, description, dateCreate);
	}
}