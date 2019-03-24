package com.lanmo.config;

import com.lanmo.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

//@Configuration
@EnableWebSecurity
//@EnableWebMvcSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserService userService;

    //配置user-detail服务
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存用户存储
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
//                .and().withUser("admin").password("password").roles("USER","ADMIN");


        /**
         * 用户信息存入从关系型数据库访问
         * 1、获取用户的用户名密码是否启用
         * 2、鉴权
         */
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                "select username, password from sys_user where username = ?"
        ).authoritiesByUsernameQuery("select username from sys_role where username=?0");

        /**
         * 基于ldap进行认证:轻量目录访问协议(Lightweight Directory Access Protocol)
         *
         * 1、用户和群组并指定搜素的范围
         * 2、可以基于密码匹配
         * 3、引用远程的LDAP服务器(contextSource) 访问远程服务器
         * 4、提供嵌入式服务器 .root("dc=lanmo,dc=com") .ldif()
         *    >> 指定加载文件.ldif("classpath:users.ldif")
         */
//        auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid)={0}")
//                .groupSearchBase("ou=groups").groupSearchFilter("member={0}")
//                .passwordCompare().passwordEncoder(new Pbkdf2PasswordEncoder())
//                .passwordAttribute("passcode")
//                .and().contextSource().url("ldap://www.bai.com:999/dc=lanmo,dc=com");


        /**
         * 基于自定义查询用户逻辑
         */
        auth.userDetailsService(userService);

    }

    //配置spring security的filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


    /**
     * 如何通过拦截器保护请求
     *
     * 定义如何保护路径的配置方法
     *
     * 使用Spring表达式进行安全保护[SpEL]
     *
     * 防止跨站请求伪造(cross-site request forgery CSRF)
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //表单的基本验证
            .formLogin().loginPage("/login")
            .and()
            .httpBasic()
            .realmName("aaa")
            //logout
            .and().logout().logoutSuccessUrl("/").logoutUrl("/signout")
            //remember me
            .and().rememberMe().tokenValiditySeconds(100000)
            .key("userKey")
            .and().authorizeRequests()
            .antMatchers("/auth/me").authenticated()
            .antMatchers(HttpMethod.POST,"/auth").authenticated()
            .antMatchers(HttpMethod.POST,"/auth").hasRole("role")
            .anyRequest().permitAll()
            //需要https
            .and().requiresChannel().antMatchers("/aaa").requiresSecure()
            //不需要http
            .antMatchers("/").requiresSecure()
            //禁用csrf
            .and().csrf().disable();
    }
}
