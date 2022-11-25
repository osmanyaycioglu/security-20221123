package com.training.security.security.document.rest;

import com.training.security.security.customer.data.dao.ICustomerDao;
import com.training.security.security.customer.services.models.Customer;
import com.training.security.security.document.rest.model.DocumentRest;
import com.training.security.security.document.rest.model.IDocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/document/management")
//@RequestMapping("/customer/document")
public class DocumentManagementController {

    @Autowired
    private IDocumentDao documentDao;

    @Autowired
    private ICustomerDao customerDao;

    @PostMapping("/add")
    public String add(@RequestBody DocumentRest documentRestParam,
                      Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        System.out.println(principal.getName());
        System.out.println(authentication.getName());
        Customer byHashName = customerDao.findByHashName(authentication.getName()
                                                                       .hashCode());
        if (byHashName == null){
            return "Başarısız";
        }
        documentRestParam.setCustomerId(byHashName.getCustomerId());
        documentDao.save(documentRestParam);
        return "Id : " + documentRestParam.getDocumentId();
    }
}
