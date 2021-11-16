package pt.mac.demo;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/error", "/login", "/token", "/api/**", "/accounts").permitAll()
				.anyRequest().authenticated().and().formLogin().and().httpBasic().and().cors().and().csrf().disable();
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests(configurer -> configurer.antMatchers("/error", "/login","/token").permitAll().anyRequest()
//				.authenticated()).formLogin()
//				.successHandler(new CustomSuccesHandler()).and().cors().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//				.exceptionHandling().disable().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).csrf()
//				.disable();
//	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost", "http://localhost:9000"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		System.out.print("Password: ");
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println(new BCryptPasswordEncoder().encode(sc.nextLine()));
		}
	}
}

//-- ciclo de desenvolvimento
//-- modelo cliente/servidor - front end/backend
//-- metodologias: mvc vs ...
//-- java e ferramentas
//-- spring, jpa, hibernate
//-- principais
//-- tipos de classes/layers

//---

// outras linguagens, docker, ...
//
//
