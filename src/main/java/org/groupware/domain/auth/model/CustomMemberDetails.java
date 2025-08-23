package org.groupware.domain.auth.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.domain.member.model.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomMemberDetails implements UserDetails {

    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getInfo().getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .toList();
    }

    @Override
    public String getPassword() {
        return member.getInfo().getPassword();
    }

    @Override
    public String getUsername() {
        return member.getInfo().getMemberName();
    }
}