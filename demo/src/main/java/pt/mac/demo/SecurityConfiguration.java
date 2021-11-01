package pt.mac.demo;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/error", "/login","/token").permitAll().anyRequest()
				.authenticated().and().formLogin()
				//.successHandler(new CustomSuccesHandler())
				
				.and().cors();
				//.and().sessionManagement()
				//.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				
				//.exceptionHandling().disable().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).csrf()
				//.disable();
		
		
//		httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated().and()
//		.formLogin().permitAll();
//		httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");
//		httpSecurity.headers().frameOptions().sameOrigin();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://localhost", "https://localhost:9000", 
				 "https://pcmac"));
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
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
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