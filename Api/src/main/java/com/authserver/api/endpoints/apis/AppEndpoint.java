package com.authserver.api.endpoints.apis;


import com.commons.exception.EntityException;
import com.commons.exception.UnauthorizedException;
import com.commons.DaoImplServices.AppDaoImpl;
import com.commons.Enum.EntityErrorCode;
import com.commons.entity.App;
import com.commons.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/app")
public class AppEndpoint extends BaseApiEndpoint{

    @PostMapping("/")
    public ResponseEntity<ApiResponse> registerApp(@RequestBody App app) throws EntityException {

        ApiResponse response = new ApiResponse();

        app = AppDaoImpl.getInstance().create(app);

        if (app == null)
            throw new EntityException(EntityErrorCode.CREATE_FAILED, "failed to create app");

        response.add("app", app);
        response.setOk(true);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{appId}")
    public ResponseEntity<ApiResponse> updateApp(@RequestBody App app, @PathVariable("appId") String appId) throws EntityException {

        ApiResponse response = new ApiResponse();

        app = AppDaoImpl.getInstance().updateApp(appId, app);

        if (app == null)
            throw new EntityException(EntityErrorCode.UPDATE_FAILED, "failed to update app Info");


        response.add("app", app);
        response.setOk(true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserApp(@PathVariable("userId") String userId) throws UnauthorizedException {

        ApiResponse response = new ApiResponse();

        if(response == null)
            throw new UnauthorizedException("you are not ldjlfjsdjf");

        List<App> apps = new ArrayList<>();

        apps = AppDaoImpl.getInstance().getUserApps(userId);

        response.setOk(true);
        response.add("apps", apps);
        return ResponseEntity.ok(response);

    }
}
