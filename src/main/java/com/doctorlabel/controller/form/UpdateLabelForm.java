package com.doctorlabel.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.doctorlabel.model.Label;
import com.doctorlabel.repository.LabelRepository;

public class UpdateLabelForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Label update(Label label, LabelRepository labelRepository) {
		label.setDescription(this.getDescription());

		return label;
	}
}
