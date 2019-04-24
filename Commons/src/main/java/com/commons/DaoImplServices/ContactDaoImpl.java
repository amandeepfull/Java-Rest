package com.commons.DaoImplServices;

import com.commons.Dao.ContactDao;
import com.commons.entity.Contact;
import com.commons.objectify.OfyService;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;

import java.util.List;
import java.util.UUID;

public class ContactDaoImpl extends OfyService implements ContactDao {


    public Contact createContact(Contact contact) {

        contact.setId(UUID.randomUUID().toString());
        return saveContact(contact);

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
