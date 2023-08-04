package com.wuf.flowable.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.entity
 * @Date 2023/8/4 10:43
 */
@ApiModel(value="com-wuf-flowable-entity-User")
@Data
public class User implements Serializable, UserDetails {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private String username;

    @ApiModelProperty(value="")
    private String password;

    private List<Role> roles;
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles != null && roles.size() > 0) {
            return roles.stream().map(r -> new
                    SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    @Override
    public String getPassword() {
        return password;
    }



    private static final long serialVersionUID = 1L;
}