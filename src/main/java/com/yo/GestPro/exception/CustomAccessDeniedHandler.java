package com.yo.GestPro.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yo.GestPro.models.error.ErrorField;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        List<ErrorField> errorField = List.of(new ErrorField(
                request.getMethod(),
                "You do not have permission to access this resource."
        ));

        response.getWriter().write(mapper.writeValueAsString(errorField));
    }
}
