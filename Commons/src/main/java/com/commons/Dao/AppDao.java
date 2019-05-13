package com.commons.Dao;

import com.commons.entity.App;

public interface AppDao {
    App getByClientId(String s);

    App create(App app);
}
