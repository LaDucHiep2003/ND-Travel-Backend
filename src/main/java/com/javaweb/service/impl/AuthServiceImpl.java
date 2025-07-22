package com.javaweb.service.impl;


import com.javaweb.model.response.AuthResponse;
import com.javaweb.security.JwtTokenProvider;
import com.javaweb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public AuthResponse login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal());

            return new AuthResponse(200, "Login success", token);

        } catch (BadCredentialsException e) {
            return new AuthResponse(401, "Sai tài khoản hoặc mật khẩu", null);
        } catch (UsernameNotFoundException e) {
            return new AuthResponse(401, "Tài khoản không tồn tại", null);
        } catch (Exception e) {
            return new AuthResponse(500, "Đăng nhập thất bại", null);
        }
    }
}
