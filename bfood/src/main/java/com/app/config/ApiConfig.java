package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.app.service.SellerService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiConfig extends WebSecurityConfigurerAdapter {

	
	
	@Bean
	public SellerService sellerservice() {
		return new SellerService();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/myspace/**",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll().antMatchers("/bfood/**").permitAll().anyRequest().authenticated()
                .and();
                //.exceptionHandling().authenticationEntryPoint(jwtEntryPoint) //
                //.and()                                                       //
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         //http.addFilterBefore(jwtTokenFilter(),  //
         //UsernamePasswordAuthenticationFilter.class);//
    }
	
}
