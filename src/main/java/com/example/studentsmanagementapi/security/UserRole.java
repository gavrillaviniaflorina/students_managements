package com.example.studentsmanagementapi.security;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.studentsmanagementapi.security.UserPermission.*;

@AllArgsConstructor
public enum UserRole {

    USER(Sets.newHashSet(USER_READ,BOOK_READ,BOOK_WRITE,COURSE_READ)),
    ADMIN(Sets.newHashSet(USER_READ,USER_WRITE,BOOK_READ,BOOK_WRITE,COURSE_READ,COURSE_WRITE));

    private final Set<UserPermission> permissions;
    public Set<UserPermission> getPermissions(){return  permissions;}

    public Set<SimpleGrantedAuthority> getGrantedAuthrities(){
        Set<SimpleGrantedAuthority> collect=getPermissions()
                .stream()
                .map(e->new SimpleGrantedAuthority(e.getPermission()))
                .collect(Collectors.toSet());

        collect.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return collect;
    }
}
