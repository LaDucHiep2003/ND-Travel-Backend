package com.javaweb.service;

import com.javaweb.model.request.AuthenticationRequest;
import com.javaweb.model.request.IntrospectRequest;
import com.javaweb.model.response.AuthenticationResponse;
import com.javaweb.model.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}
