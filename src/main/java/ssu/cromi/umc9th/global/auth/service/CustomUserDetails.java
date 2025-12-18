package ssu.cromi.umc9th.global.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ssu.cromi.umc9th.domain.user.entity.User;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole().toString());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}