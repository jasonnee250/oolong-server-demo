package org.oolong.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Data
public class SecurityUser implements UserDetails {
    
    private Long id;
    
    private String username;
    
    private String password;
    
    private Boolean isLocked;
    
    private LocalDateTime expired;
    
    private LocalDateTime passwordExpired;
    
    private Boolean isEnabled;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(0);
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return this.expired == null || this.expired.isAfter(LocalDateTime.now());
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return this.passwordExpired == null || this.passwordExpired.isAfter(LocalDateTime.now());
    }
    
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
