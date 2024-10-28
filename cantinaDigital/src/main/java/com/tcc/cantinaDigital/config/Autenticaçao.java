package com.tcc.cantinaDigital.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Autenticaçao implements AuthenticationSuccessHandler{
	
	@Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                                        Authentication authentication) throws IOException {
	        String username = authentication.getName();
	        
	        if ("adm".equals(username)) {
	            response.sendRedirect("/menuAdm");
	        } else {
	            response.sendRedirect("/menuPedidos");
	        }
	    }
}