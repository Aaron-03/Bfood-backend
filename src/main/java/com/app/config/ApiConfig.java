package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.jwt.JwtEntryPoint;
import com.app.jwt.JwtTokenFilter;

import com.app.service.SellerService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiConfig extends WebSecurityConfigurerAdapter {

//	@Bean
//	public SellerService sellerservice() {
//		return new SellerService;
//	}
	
	
	// ================ all reference to login
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encriptar());
	}

	@Bean
	public BCryptPasswordEncoder encriptar() {
		return new BCryptPasswordEncoder();

	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
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
				.and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint) //
				.and()                                                       //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilterBefore(jwtTokenFilter(),  //
		 UsernamePasswordAuthenticationFilter.class);//
	}
}
