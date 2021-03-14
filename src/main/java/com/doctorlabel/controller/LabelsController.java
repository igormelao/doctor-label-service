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

/**
* 
* <p>This class is a Rest Controller for resources of Labels</p>
* 
* <p>There is one dependency related with this Rest Controller and it is Injectabled by SpringBoot by tag @Autowired <p>
* 
* <p>List third Objects:<p>
* <ol>
* 	<li>LabelRepository labelRepository</li>
* </ol>
* 
* <p>The list available for this Rest LabelsController are described below:<p>
* 
*  <ul> 
*  	<li>
*  		Request Mapping: "/labels" 
*  		<ul>
*  			<li>METHOD: GET</li>
*  			<li>return a List LabelDto( @see {@link com.doctorlabel.controller.dto.LabelDto})</li>
*  		</ul>
* 	</li>
* 	<li>
*  		Request Mapping: "/labels/{id}" 
*  		<ul>
*  			<li>METHOD: GET</li>
*  			<li>PATH VARIABLE: String id</li> 
*  			<li>return a ResponseEntity LabelDto(@see {@link com.doctorlabel.controller.dto.LabelDto})</li>
*  		</ul>
*	</li>
*  </ul>
*  
*  <p> You can see more details of all resource @see <a href=" http://localhost:8080/swagger-ui.html"> http://localhost:8080/swagger-ui.html</a>
* 
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
* 
*/
@RestController
@RequestMapping("/labels")
public class LabelsController {

	/**
	 * <p>This is a repository that access database layer for Labels</p>
	 * @since 0.0.1-SNAPSHOT
	 */
	@Autowired
	private LabelRepository labelRepository;

	/**
	 * <p>This is a resource to return all Labels</p>
	 * @return List of LabelDto @see(com.doctorlabel.controller.dto.LabelDto)
	 * @see <a href="http://localhost:8080/swagger-ui.html#/labels-controller/listAllUsingGET">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping
	public List<LabelDto> listAll() {
		List<Label> labels = labelRepository.findAll();
		return LabelDto.convert(labels);
	}

	/**
	 * <p>This is a resource to create a new Label</p>
	 * @param 
	 * @return ResponseEntity< LabelDto > @see(com.doctorlabel.controller.dto.LabelDto)
	 * @see <a href="http://localhost:8080/swagger-ui.html#/labels-controller/createUsingPOST">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<LabelDto> create(@RequestBody @Validated LabelForm form, UriComponentsBuilder uriBuilder) {
		Label label = form.convert();
		labelRepository.save(label);

		URI uri = uriBuilder.path("/labels/{id}").buildAndExpand(label.getId()).toUri();
		return ResponseEntity.created(uri).body(new LabelDto(label));
	}

	/**
	 * <p>This is a resource to find Label by your unique identification ID</p>
	 * @param
	 * @return ResponseEntity< LabelDto > @see(com.doctorlabel.controller.dto.LabelDto)
	 * <p>return  ResponseEntity.notFound() in case that the resource it's not found</p>
	 * @see <a href="http://localhost:8080/swagger-ui.html#/labels-controller/findByUsingGET">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping("/{id}")
	public ResponseEntity<LabelDto> findBy(@PathVariable String id) {
		Optional<Label> label = labelRepository.findById(id);
		if (label.isPresent()) {
			return ResponseEntity.ok(new LabelDto(label.get()));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * <p>This is a resource to update description of Label</p>
	 * @param
	 * @return ResponseEntity< LabelDto > @see(com.doctorlabel.controller.dto.LabelDto)
	 * <p>return  ResponseEntity.notFound() in case that the resource it's not found</p>
	 * @see <a href="http://localhost:8080/swagger-ui.html#/labels-controller/updateUsingPUT">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
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

	/**
	 * <p>This is a resource to delete  Label</p>
	 * @param
	 * @return ResponseEntity< LabelDto > @see(com.doctorlabel.controller.dto.LabelDto)
	 * <p>return  ResponseEntity.notFound() in case that the resource it's not found</p>
	 * @see <a href="http://localhost:8080/swagger-ui.html#/labels-controller/deleteUsingDELETE">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
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
