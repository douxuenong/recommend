package cn.shawn.recommend.config;

import cn.shawn.recommend.service.UserService;
import cn.shawn.recommend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring suecurity设置
 * @author shawn
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService){
        this.userService = userService;
    }

    /**
     * 匹配 "/register" 路径，不需要权限即可访问
     * 匹配所有路径，都需要 "USER" 权限 (在登录成功时默认赋予USER权限）
     * 登录地址为 "/login"，登录成功返回响应状态码
     * 退出登录的地址为 "/logout"，退出成功返回响应状态码
     * 禁用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/register").permitAll()
                 .antMatchers("/swagger-ui.html").permitAll()
                 .antMatchers("/**").hasAuthority("USER")
                //测试时允许全部
                //.antMatchers("/**").permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .clearAuthentication(true).permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .csrf().disable()
                .addFilterAt(customJSONLoginFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    /**
     * @return 密码加密
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * 自定义认证过滤器
     */
    private CustomJSONLoginFilter customJSONLoginFilter() {
        CustomJSONLoginFilter customJSONLoginFilter = new CustomJSONLoginFilter("/login",userService,bCryptPasswordEncoder());
        customJSONLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        customJSONLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        return customJSONLoginFilter;
    }
}
