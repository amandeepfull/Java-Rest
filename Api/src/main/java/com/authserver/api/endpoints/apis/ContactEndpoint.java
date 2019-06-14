package com.authserver.api.endpoints.apis;

import com.authserver.api.exception.NotFoundException;
import com.authserver.api.model.ApiResponse;

import com.commons.DaoImplServices.ContactDaoImpl;
import com.commons.entity.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/contact")
public class ContactEndpoint extends BaseApiEndpoint {

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createContact(@RequestBody Contact contact){

        ApiResponse response = new ApiResponse();

        contact = ContactDaoImpl.getInstance().createContact(contact);

        response.setOk(true);
        response.add("contact", contact);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/uname/{uname}")
    public ResponseEntity<ApiResponse> getContact (@PathVariable("uname") String uname){

        ApiResponse response = new ApiResponse();

        Contact contact = ContactDaoImpl.getInstance().getByEmail(uname);

        if(contact == null)
            throw new NotFoundException("contact not found");

        response.setOk(true);
        response.add("contact", contact);
        return ResponseEntity.ok(response);
    }
}
