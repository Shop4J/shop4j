package shop4j.config.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import shop4j.services.login.LoginService;
import shop4j.services.login.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 15:49
 * @Description:商城安全配置
 */
@Configuration
@EnableWebSecurity
public class ShopSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private CustomAuthenticationDetailsSource customAuthenticationDetailsSource;

    @Autowired
    private LoginService loginService;
    /**
     * 不复写如果写再下面将会导致存在父类得provider导致重复调用2次验证
     * @param auth
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
                .authenticationEntryPoint(new CustomLoginUrlAuthenticationEntryPoint("/login"))
                .and()
            .authorizeRequests()
                .antMatchers("/user/**").hasRole("USER")
                .and()
            .formLogin()
                .authenticationDetailsSource(customAuthenticationDetailsSource)
                .failureHandler(new LoginFailHandler())
                .successForwardUrl("/login/success")
                .and()
            .rememberMe()
                /**
                 * 分布式登陆可以在这里控制登陆保持 但是sessionId存储得信息需要给分布式数据库
                 */
                .key("remember")
                .tokenValiditySeconds(30*24*60*60)
                .alwaysRemember(false)
                .rememberMeParameter("remember")
                /**
                 * 处理离线重装
                 */
                .userDetailsService(loginService)
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .deleteCookies("JSESSIONID")
                .deleteCookies("remember-me")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(new ForwardLogoutSuccessHandler("/login"))
                .and()
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public SpringTemplateEngine springTemplateEngine(){
//        return new SpringSecurityDialect();
//    }
}
