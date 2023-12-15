package com.socialMedia.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;


//This configuration class will have the WhiteListed APIs/Paths like Register User
//We will use this class to let the Spring Security know what sort of request to block and what sort of request to
//allow on the API (e.g : Registering a User should not be blocked)


@Configuration
@EnableWebSecurity  //To enable Web Security of Spring Here
public class AppConfig {
	
	//we will now create a method of SecurityFilterChain return type
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		//now here all the HTTP Requests will come, so we'd need to authorize them first
		
		http.sessionManagement(management -> management
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //stateless means, server will not create or store sessions for user
			.authorizeHttpRequests(Authorize -> Authorize
									.requestMatchers("/api/**").authenticated()
									.anyRequest().permitAll())	//this line says anyother reuqest not having /api will be premitted
			.addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class) 
			//this above filter will make sure that before any hit to /api/* path, request gets filtered/validated 
			//by the token sent into Header, using our jwtToken Method
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.configurationSource(CorsConfigurationSource()));
		
		return http.build();
		
	}
	
	private CorsConfigurationSource CorsConfigurationSource() {
		// we will write the configuration in this method 
		return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				
				CorsConfiguration cfg = new CorsConfiguration();
				
				cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
				
				return cfg;
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}

}
