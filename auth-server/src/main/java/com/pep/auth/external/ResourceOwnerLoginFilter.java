package com.pep.auth.external;

import com.pep.auth.helper.AuthenticationSuccessReturnTokenHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 资源拥有者认证拦截器
 *
 * @author gang.liu
 */
public class ResourceOwnerLoginFilter extends AbstractAuthenticationProcessingFilter {

    protected ResourceOwnerLoginFilter(AuthenticationManager authenticationManager) {
        super("/web/login");
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(new AuthenticationSuccessReturnTokenHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
