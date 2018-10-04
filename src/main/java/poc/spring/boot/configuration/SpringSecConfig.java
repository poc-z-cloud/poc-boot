package poc.spring.boot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    @Qualifier("daoAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

//    @Bean
//    public PasswordEncoder passwordEncoder(PasswordEncryptor passwordEncryptor){
//        PasswordEncoder passwordEncoder = new org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder();
//        passwordEncoder.setPasswordEncryptor(passwordEncryptor);
//        return passwordEncoder;
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//      in Spring security 5, default encryptor setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        daoAuthenticationProvider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()); 
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        .authorizeRequests().antMatchers("/","/webjars/**","/pbdashboard/**","/css/**","/images/**","/mvc/products","mvc/product/show/*","/h2-console/**","/test/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().defaultSuccessUrl("/console/", false).loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
        .and()
        .logout().permitAll();
        
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }


}