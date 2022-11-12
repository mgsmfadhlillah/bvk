package com.bvk.springjwt.config.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
  @Autowired
  private JwtUtils jwtUtils;

  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    final Map<String, Object> body = new HashMap<>();
    final ObjectMapper mapper = new ObjectMapper();
    String jwt = jwtUtils.parseJwt(request);

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    body.put("error", "Unauthorized");
    body.put("path", request.getServletPath());
    if(jwt != null) {
      String msg = jwtUtils.getValidateToken(jwt);
      body.put("message", msg);
      logger.error("JWT msg : {}", msg);
    }else{
      body.put("message", authException.getMessage());
    }
    logger.error("Unauthorized error: {}", authException.getMessage());

    mapper.writeValue(response.getOutputStream(), body);
  }

}
