package com.training.security.security.document.rest;

import com.training.security.security.document.rest.model.DocumentRest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/document/management")
//@RequestMapping("/customer/document")
public class DocumentManagementController {

    @PostMapping("/add")
    public String add(@RequestBody DocumentRest documentRestParam){
        return "OK";
    }
}
