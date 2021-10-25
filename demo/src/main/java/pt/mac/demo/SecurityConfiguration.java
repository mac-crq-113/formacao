package pt.mac.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//TODO:
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated().and()
				.formLogin().permitAll();
		httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");
		httpSecurity.headers().frameOptions().sameOrigin();
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