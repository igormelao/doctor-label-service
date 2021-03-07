package com.doctorlabel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorlabel.model.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

	Optional<Label> findById(String id);
}
