package com.authserver.api.endpoints.apis;

import com.authserver.api.exception.mapper.ExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin
public class BaseApiEndpoint extends ExceptionMapper {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;
}
