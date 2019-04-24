package com.commons.Dao;

import com.commons.entity.Contact;

public interface ContactDao {
    Contact getByEmail(String email);

    Contact saveContact(Contact contact);
}
