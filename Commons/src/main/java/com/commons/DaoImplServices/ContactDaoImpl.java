package com.commons.DaoImplServices;

import com.commons.Dao.ContactDao;
import com.commons.constants.CommonConstants;
import com.commons.entity.Contact;
import com.commons.http.HttpMethod;
import com.commons.http.HttpRequest;
import com.commons.http.HttpResponse;
import com.commons.http.UrlFetcher;
import com.commons.objectify.OfyService;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
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

    public Contact getByIdFromRemote(String userId) {

        Preconditions.checkArgument(ObjUtil.isBlank(userId), "userId cannot be null/empty");

        try {
            HttpRequest request = new HttpRequest(CommonConstants.OAUTH_CATER_API_URL + "/api/v1/contact/" + userId, HttpMethod.GET);
            //request.addHeader("Authorization", "ApiKey=" + CommonConstants.AUTH_API_KEY);

            HttpResponse response = UrlFetcher.makeRequest(request);
            if (!response.wasSuccessful()) {
                log.error("error response : " + response.getResponseContent());
                return null;
            }

            Map<String, Object> apiResponse = ObjUtil.getMapFromJson(response.getResponseContent());

            return ObjUtil.safeConvertMap((Map<String, Object>) ((Map<String, Object>) apiResponse.get("data")).get("contact"), Contact.class);
        } catch (Exception e) {
            log.error("exception while fetching contact by Id from remote : ", e.getMessage(), e);
            return null;
        }
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
