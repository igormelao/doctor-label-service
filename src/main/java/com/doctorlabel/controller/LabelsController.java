package com.doctorlabel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.doctorlabel.controller.dto.LabelDto;
import com.doctorlabel.controller.form.LabelForm;
import com.doctorlabel.model.Label;
import com.doctorlabel.repository.LabelRepository;

@RestController
@RequestMapping("/labels")
public class LabelsController {

	@Autowired
	private LabelRepository labelRepository;
	
	@GetMapping
	public List<LabelDto> listAll(){
		List<Label> labels = labelRepository.findAll();
		return LabelDto.convert(labels);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LabelDto> create(@RequestBody @Validated LabelForm form, UriComponentsBuilder uriBuilder){
		Label label = form.convert(labelRepository);
		labelRepository.save(label);
		
		URI uri = uriBuilder.path("/labels/{id}").buildAndExpand(label.getId()).toUri();
		return ResponseEntity.created(uri).body(new LabelDto(label));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LabelDto> findBy(@PathVariable String id) {
		Optional<Label> label = labelRepository.findById(id);
		if(label.isPresent()) {
			return ResponseEntity.ok(new LabelDto(label.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
}
