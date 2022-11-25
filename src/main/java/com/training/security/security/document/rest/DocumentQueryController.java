package com.training.security.security.document.rest;

import com.training.security.security.document.rest.model.DocumentRest;
import com.training.security.security.document.rest.model.IDocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/document/query")
public class DocumentQueryController {

    @Autowired
    private IDocumentDao documentDao;

    @GetMapping("/get/by/id")
    public DocumentRest getById(@RequestParam("did") Long documentId){
        return documentDao.findById(documentId).orElse(null);
    }
}
