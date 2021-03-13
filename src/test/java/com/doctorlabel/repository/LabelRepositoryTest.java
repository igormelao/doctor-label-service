package com.doctorlabel.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.doctorlabel.model.Label;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class LabelRepositoryTest {

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	public void shouldReturnAllLabels() {
		Label labelA09 = new Label("A09", "Infectious gastroenteritis and colitis, unspecified", LocalDateTime.now());
		em.persist(labelA09);

		Label labelA64 = new Label("A64", "Unspecified sexually transmitted disease", LocalDateTime.now());
		em.persist(labelA64);

		List<Label> labels = labelRepository.findAll();

		Assert.assertNotNull(labels);
		Assert.assertEquals(2, labels.size());
		
		Assert.assertEquals(labelA09.getId(), labels.get(0).getId());
		Assert.assertEquals(labelA09.getDescription(), labels.get(0).getDescription());
		
		Assert.assertEquals(labelA64.getId(), labels.get(1).getId());
		Assert.assertEquals(labelA64.getDescription(), labels.get(1).getDescription());
	}
	
	@Test
	public void shouldNotReturnLabels() {
		List<Label> labels = labelRepository.findAll();

		Assert.assertNotNull(labels);
		Assert.assertEquals(0,labels.size());
	}
	
	@Test
	public void shouldReturnLabelById() {
		Label labelA09 = new Label("A09", "Infectious gastroenteritis and colitis, unspecified", LocalDateTime.now());
		em.persist(labelA09);
		
		Label labelA64 = new Label("A64", "Unspecified sexually transmitted disease", LocalDateTime.now());
		em.persist(labelA64);
		
		Optional<Label> optionalLabel = labelRepository.findById("A09");
		
		Assert.assertNotNull(optionalLabel);
		Assert.assertTrue(optionalLabel.isPresent());
		
		Label labelFounded = optionalLabel.get();
		Assert.assertEquals(labelA09.getId(), labelFounded.getId());
		Assert.assertEquals(labelA09.getDescription(), labelFounded.getDescription());
	}
	
	@Test
	public void shouldCreateLabelWithSuccess() {
		Optional<Label> labelBeforeCreated = labelRepository.findById("A09");
		Assert.assertNotNull(labelBeforeCreated);
		Assert.assertTrue(labelBeforeCreated.isEmpty());
		Assert.assertFalse(labelBeforeCreated.isPresent());
		
		Label labelA09 = new Label("A09", "Infectious gastroenteritis and colitis, unspecified", LocalDateTime.now());
		
		labelRepository.save(labelA09);
		
		Optional<Label> optionalLabelAfterCreated = labelRepository.findById("A09");
		Assert.assertNotNull(optionalLabelAfterCreated);
		Assert.assertFalse(optionalLabelAfterCreated.isEmpty());
		Assert.assertTrue(optionalLabelAfterCreated.isPresent());
		
		Label labelAfterCreate = optionalLabelAfterCreated.get();
		Assert.assertNotNull(labelAfterCreate);
		Assert.assertEquals(labelA09.getId(), labelAfterCreate.getId());
		Assert.assertEquals(labelA09.getDescription(), labelAfterCreate.getDescription());
	}
	
	@Test
	public void shouldUpdateLabelWithSuccess() {
		Label labelA09 = new Label("A09", "Infectious gastroenteritis and colitis, unspecified", LocalDateTime.now());
		em.persist(labelA09);
		
		Optional<Label> optionalLabel = labelRepository.findById("A09");
		
		String updateDrescription = "Not Infectious gastroenteritis";
		Label label = optionalLabel.get();
		label.setDescription(updateDrescription);
		
		labelRepository.save(label);

		Optional<Label> optionalLabelAfterUpdate = labelRepository.findById("A09");
		Assert.assertNotNull(optionalLabelAfterUpdate);
		Assert.assertFalse(optionalLabelAfterUpdate.isEmpty());
		Assert.assertTrue(optionalLabelAfterUpdate.isPresent());
		
		Label labelUpdated = optionalLabelAfterUpdate.get();
		Assert.assertNotNull(labelUpdated);
		Assert.assertEquals(updateDrescription, labelUpdated.getDescription());
	}
}
