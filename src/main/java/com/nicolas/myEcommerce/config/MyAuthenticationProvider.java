package com.nicolas.myEcommerce.config;

import com.nicolas.myEcommerce.model.user.Authority;
import com.nicolas.myEcommerce.model.user.User;
import com.nicolas.myEcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Component
@EnableMethodSecurity
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user registered with this details!"));
        if (passwordEncoder.matches(pwd, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(user.getAuthorities()));
        }else {
            throw new BadCredentialsException("Invalid password");
        }
    }
    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        authorities.forEach(grantedAuthority -> grantedAuthorities.add(new SimpleGrantedAuthority(grantedAuthority.getName())));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}