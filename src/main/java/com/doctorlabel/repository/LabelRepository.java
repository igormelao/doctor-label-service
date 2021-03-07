package com.doctorlabel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorlabel.model.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

}
