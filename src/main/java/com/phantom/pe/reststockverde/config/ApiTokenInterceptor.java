package com.phantom.pe.reststockverde.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiTokenInterceptor implements HandlerInterceptor {

    @Value("${api.security.token}")
    private String tokenFijo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Permitir solicitudes preflight de CORS
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        String expectedHeader = "Bearer " + tokenFijo;

        if (authHeader != null && authHeader.equals(expectedHeader)) {
            return true; // Token válido, permite la ejecución del Controller
        }

        // Token inválido o no enviado
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Acceso denegado. Token invalido o no proporcionado.\"}");
        return false; // Bloquea el flujo
    }
}