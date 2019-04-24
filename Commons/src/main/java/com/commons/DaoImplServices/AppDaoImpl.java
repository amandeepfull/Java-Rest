package com.commons.DaoImplServices;

import com.commons.Dao.AppDao;
import com.commons.Enum.AppType;
import com.commons.entity.App;
import com.commons.objectify.OfyService;
import com.commons.utils.HashUtil;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;

import java.util.UUID;

public class AppDaoImpl extends OfyService implements AppDao {


    private static class AppRegistrationServiceInitializer{
        private static final AppDaoImpl appRegService = new AppDaoImpl();
    }

    public static AppDaoImpl getInstance(){
        return AppRegistrationServiceInitializer.appRegService;
    }

    @Override
    public App saveApp(App app) {

        validateApp(app);
        Preconditions.checkArgument(app == null, "Invalid app request body to save");

        app.setId(UUID.randomUUID().toString());

        if(app.getType() != AppType.MOBILE)
        app.setSecret(generateSecret(app.getId()));

        return save(app) != null ? app : null;
    }

    public App getById(String appId){
        Preconditions.checkArgument(ObjUtil.isBlank(appId), "Invalid appId");
        return get(App.class, appId);
    }

    private String generateSecret(String appId) {
        return HashUtil.md5Hash(appId+ "-client-secret-");
    }

    private void validateApp(App app) {

        Preconditions.checkArgument(app == null, "Invalid app to save");
        Preconditions.checkArgument(ObjUtil.isBlank(app.getHost()), "host cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isBlank(app.getName()), "app name cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isBlank(app.getRedirectUri()), "redirect uri cannot be null/empty");
        Preconditions.checkArgument(app.getType() == null, "Invalid app type, it can be, "+AppType.MOBILE+"/"+AppType.WEB+"/"+AppType.WEB_MOB);

    }
}
