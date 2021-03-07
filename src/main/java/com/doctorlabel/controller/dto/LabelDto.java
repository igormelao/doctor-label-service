package com.doctorlabel.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.doctorlabel.model.Label;

public class LabelDto {
	private String id;
	private String description;
	private LocalDateTime dateCreate;

	public LabelDto() {
	}

	public LabelDto(Label label) {
		this.id = label.getId();
		this.description = label.getDescription();
		this.dateCreate = label.getDateCreate();
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public static List<LabelDto> convert(List<Label> labels) {
		return labels.stream().map(LabelDto::new).collect(Collectors.toList());
	}

}
