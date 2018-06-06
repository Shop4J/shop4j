package shop4j.vo.login;

import base.util.collections.CollectionUtil;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shop4j.models.user.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 11:57
 * @Description:用户信息VO
 */
@Data
public class UserDetailVO implements UserDetails{
    /**
     * 用户主键
     */
    private long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态 1有效 2无效
     */
    private int status;

    /**
     * 用户可用权限
     */
    private List<Role> roles;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Role> roles = getRoles();
        if(CollectionUtil.isNotEmpty(roles)) {
            for (Role role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status==1?true:false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==1?true:false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status==1?true:false;
    }

    @Override
    public boolean isEnabled() {
        return status==1?true:false;
    }
}
