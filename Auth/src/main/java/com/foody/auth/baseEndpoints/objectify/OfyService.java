package com.foody.auth.baseEndpoints.objectify;


import com.googlecode.objectify.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public abstract class OfyService {

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

    public <T> T get(Class<T> clazz, String key) {
        return isNullOrEmpty(key) ? null : ofy().load().type(clazz).id(key).now();
    }

    public <T> T get(Class<T> clazz, long key) {
        return key <= 0 ? null : ofy().load().type(clazz).id(key).now();
    }

    public <T> T get(Class<T> clazz, String key, boolean cache) {
        return isNullOrEmpty(key) ? null : ofy().cache(cache).load().type(clazz).id(key).now();
    }

    public <T> T get(Ref<T> ref) {
        return ref == null ? null : get(ref.getKey());
    }

    public <T> Key<T> getKey(Class<T> clazz, String id) {
        return Key.create(clazz, id);
    }

    public <T> T get(Key<T> key) {
        return key == null ? null : ofy().load().key(key).now();
    }

    public <T, S> List<T> get(Class<T> clazz, Iterable<S> ids) {
        if (ids == null)
            return new ArrayList<>();
        Map<S, T> result = ofy().load().type(clazz).ids(ids);
        if (result == null)
            return new ArrayList<>();
        return new ArrayList<T>(result.values());
    }

    public <T> T getByFilter(Class<T> clazz, String filterBy, Object value) {

        if (value == null || isNullOrEmpty(filterBy))
            throw new IllegalArgumentException("Invalid Argument value/filterBy");

        return ofy().load().type(clazz).filter(filterBy, value).first().now();
    }

    public <T> List<T> getEntitiesByFilter(Class<T> clazz, String filterBy, Object value) {
        if (value == null || isNullOrEmpty(filterBy))
            throw new IllegalArgumentException("Invalid Argument value / filterBy");
        return ofy().load().type(clazz).filter(filterBy, value).list();
    }

    public <T> Key<T> getKeyByFilter(Class<T> clazz, String filterBy, Object value) {
        return ofy().load().type(clazz).filter(filterBy, value).keys().first().now();
    }



    public <T> Key<T> save(T entity) {
        if (entity == null)
            return null;
        return ofy().save().entity(entity).now();
    }

    public <T> Map<Key<T>, T> save(Collection<T> entities) {
        return isNullOrEmpty(entities) ? null : ofy().save().entities(entities).now();
    }

    public boolean delete(Collection<?> entities) {

        if (isNullOrEmpty(entities))
            return false;

        ofy().delete().entities(entities);
        //TODO delete entity etag cache data as well
        return true;
    }



    static boolean isNullOrEmpty(String value) {
        return (value == null || value.length() <= 0);
    }

    static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    static boolean isNullOrEmpty(Collection<?> obj) {
        return (obj == null || obj.isEmpty());
    }

    static boolean isNullOrEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }


}
