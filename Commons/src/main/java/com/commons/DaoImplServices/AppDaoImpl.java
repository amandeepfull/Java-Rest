package com.commons.DaoImplServices;

import com.commons.Dao.AppDao;
import com.commons.Enum.AppType;
import com.commons.entity.App;
import com.commons.objectify.OfyService;
import com.commons.utils.HashUtil;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import com.commons.utils.RandomUtil;

import javax.ws.rs.NotFoundException;
import java.util.UUID;

public class AppDaoImpl extends OfyService implements AppDao {


    @Override
    public App getByClientId(String clientId) {
        Preconditions.checkArgument(ObjUtil.isBlank(clientId), "clienId cannot be null/empty");
        return OfyService.ofy().load().type(App.class).filter("clientId", clientId).first().now();
    }

    public App updateApp(String appId, App updateApp) {

        App app = getById(appId);

        if(app == null)
            throw new NotFoundException("app not found");

       app =  app.merge(updateApp);

       return save(app) != null ? app : null;
    }

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

        app.setClientId(UUID.randomUUID().toString());

        if(app.getType() != AppType.MOBILE)
        app.setClientSecret(UUID.randomUUID().toString());

        app.setId((app.getName().replace(" ","")+"_"+RandomUtil.generateRandomString(8)).toLowerCase());
        return save(app) != null ? app : null;
    }


    public App getById(String appId){
        Preconditions.checkArgument(ObjUtil.isBlank(appId), "Invalid appId");
        return get(App.class, appId);
    }

    private void validateApp(App app) {

        Preconditions.checkArgument(app == null, "Invalid app to save");
        Preconditions.checkArgument(ObjUtil.isBlank(app.getName()), "app name cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isNullOrEmpty(app.getRedirectUri()), "redirect uri cannot be null/empty");
        Preconditions.checkArgument(app.getType() == null, "Invalid app type, it can be, "+AppType.MOBILE+"/"+AppType.WEB+"/"+AppType.WEB_MOB);
       // Preconditions.checkArgument(ObjUtil.isNullOrEmpty(app.getScopes()), "scopes cannot be null/empty");
    }
}
