package com.training.security.security.document.rest.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentDao extends JpaRepository<DocumentRest,Long> {

}
