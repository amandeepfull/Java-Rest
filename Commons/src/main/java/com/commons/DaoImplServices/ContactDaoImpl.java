package com.commons.DaoImplServices;


import com.commons.Dao.ContactDao;
import com.commons.entity.Contact;
import com.commons.http.HttpMethod;
import com.commons.http.HttpRequest;
import com.commons.http.HttpResponse;
import com.commons.http.UrlFetcher;
import com.commons.objectify.OfyService;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;

import java.util.Map;
import java.util.UUID;

public class ContactDaoImpl extends OfyService implements ContactDao {


    public Contact createContact(Contact contact) {

        validate(contact);
        contact.setId(UUID.randomUUID().toString());
        return saveContact(contact);

    }

    private void validate(Contact contact) {

        Preconditions.checkArgument(contact == null, "Invalid contact to save");
        Preconditions.checkArgument(ObjUtil.isBlank(contact.getEmail()), "Email cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isBlank(contact.getPassword()), "Invalid password cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isBlank(contact.getFirstName()), "First Name cannot be null/empty");

    }

    public Contact getById(String userId) {
        Preconditions.checkArgument(ObjUtil.isBlank(userId), "Invalid userId");
        return get(Contact.class, userId);
    }



    private static class ContactDaoImplInitializer {
        private static final ContactDaoImpl contactDaoImpl = new ContactDaoImpl();
    }

    public static ContactDaoImpl getInstance() {
        return ContactDaoImplInitializer.contactDaoImpl;

    }

    @Override
    public Contact getByEmail(String email) {

        Preconditions.checkArgument(ObjUtil.isBlank(email), "Invalid email");

        return OfyService.ofy().load().type(Contact.class).filter("email", email).first().now();

    }

    @Override
    public Contact saveContact(Contact contact) {

        Preconditions.checkArgument(contact == null, "Invalid contact");

        return save(contact) != null ? contact : null;
    }
}
