package com.example.exobibliotheque.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// ------- !!!!!!!!!! Ne laisser que les lignes indiquées par les commentaires pour tous permettre

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("springuser").password(passwordEncoder().encode("spring123")).roles("USER")
			.and()
			.withUser("springadmin").password(passwordEncoder().encode("admin123")).roles("ADMIN","USER");

	}
	*/
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		// !!! Permet d'utiliser presque toutes les requetes HTTP
		
        http.authorizeRequests().antMatchers("/").permitAll();

		
		 http
		 /*
		  	.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin().and()
			*/
			/*
			 * Permet de contourner l'erreur 403 obtenu pour la requete POST
			 * à creuser
			 */
		 // !!! Permet d'utiliser POST / DELETE
		.csrf().disable();
			//.and()
			//.oauth2Login();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
