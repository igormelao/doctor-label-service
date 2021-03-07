package com.doctorlabel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.doctorlabel.controller.dto.LabelDto;
import com.doctorlabel.controller.form.LabelForm;
import com.doctorlabel.controller.form.UpdateLabelForm;
import com.doctorlabel.model.Label;
import com.doctorlabel.repository.LabelRepository;

@RestController
@RequestMapping("/labels")
public class LabelsController {

	@Autowired
	private LabelRepository labelRepository;

	@GetMapping
	public List<LabelDto> listAll() {
		List<Label> labels = labelRepository.findAll();
		return LabelDto.convert(labels);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<LabelDto> create(@RequestBody @Validated LabelForm form, UriComponentsBuilder uriBuilder) {
		Label label = form.convert();
		labelRepository.save(label);

		URI uri = uriBuilder.path("/labels/{id}").buildAndExpand(label.getId()).toUri();
		return ResponseEntity.created(uri).body(new LabelDto(label));
	}

	@GetMapping("/{id}")
	public ResponseEntity<LabelDto> findBy(@PathVariable String id) {
		Optional<Label> label = labelRepository.findById(id);
		if (label.isPresent()) {
			return ResponseEntity.ok(new LabelDto(label.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LabelDto> update(@PathVariable String id, @RequestBody @Valid UpdateLabelForm form) {
		Optional<Label> optionalLabel = labelRepository.findById(id);
		if (optionalLabel.isPresent()) {
			Label label = form.update(optionalLabel.get(), labelRepository);
			return ResponseEntity.ok(new LabelDto(label));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<LabelDto> delete(@PathVariable String id) {
		Optional<Label> optionalLabel = labelRepository.findById(id);
		if (optionalLabel.isPresent()) {
			labelRepository.delete(optionalLabel.get());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
