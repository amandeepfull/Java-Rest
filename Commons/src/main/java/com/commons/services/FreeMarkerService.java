package com.commons.services;

import com.commons.utils.AppUtils;
import com.commons.utils.ObjUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;


@Slf4j
public class FreeMarkerService {

    private static final Configuration fmConfig;

    static {
        fmConfig = new Configuration(Configuration.VERSION_2_3_23);
        fmConfig.setServletContextForTemplateLoading(ResteasyProviderFactory.getContextData(ServletContext.class), "/");
        fmConfig.setDefaultEncoding("UTF-8");
        fmConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public static String pageTmplPath() {
        return "pages/html";
    }

    public static Template getHtmlTemplate(String fileName) throws IOException {

        return fmConfig.getTemplate(String.format("%s/%s", pageTmplPath(), fileName));
    }

    public static Template getTemplate(String path) throws Exception {

        return fmConfig.getTemplate(path);
    }

    public static String renderTmplAsString(String tmplName, Object params) throws Exception {

        Template tmpl = getTemplate(tmplName);

        StringWriter writer = new StringWriter();
        tmpl.process(params, writer);

        return writer.toString();
    }


    public static boolean writeHtmlResponse(HttpServletResponse servletResponse, int statusCode, String tmplFileName, Object tmplParams) {

        servletResponse.setStatus(statusCode);
        servletResponse.setContentType(javax.ws.rs.core.MediaType.TEXT_HTML + "; charset=UTF-8");

        servletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        servletResponse.setHeader("X-Content-Type-Options", "nosniff");
        servletResponse.setHeader("X-XSS-Protection", "1; mode=block");
        servletResponse.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setHeader("Strict-Transport-Security", "max-age=31536000");

        try {
            Template tmpl = FreeMarkerService.getTemplate(tmplFileName);
            System.out.println("template params : "+ObjUtil.getJson(tmplParams));
            tmpl.process(tmplParams, servletResponse.getWriter());
            return true;

        } catch (Exception e) {
            log.warn("Error Writing Html Response : " + e.getMessage(), e);
            return false;
        }
    }
}
