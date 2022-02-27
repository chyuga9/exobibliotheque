package com.example.exobibliotheque.configuration;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// ------- !!!!!!!!!! Ne laisser que les lignes indiquées par les commentaires pour tous permettre

// ----------- Autre méthode pour l'authentification avec UserDetails : https://www.youtube.com/watch?v=TNt3GHuayXs&t=851s
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource datasource;


@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.jdbcAuthentication()
	.dataSource(datasource).passwordEncoder(getPasswordEncoder())
	// Personnalisation des requêtes 
	.usersByUsernameQuery("SELECT email, password, enabled "
			+ "FROM user "
			+"WHERE email = ?")
	.authoritiesByUsernameQuery("SELECT email, authority "
			+ "FROM authority "
			+"WHERE email = ?");

}



@Bean
public PasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder(12) ; //NoOpPasswordEncoder.getInstance()
}
	
@Override
public void configure(HttpSecurity http) throws Exception{
	
	// !!! Permet d'utiliser presque toutes les requetes HTTP, csrf().disable() permet de desactiver la securité CORS
	/*
    http.authorizeRequests().antMatchers("/").permitAll();
	http.csrf().disable();
	
	
	// ordre antMatchers() -> du plus spécifique au plus général
	//https://www.baeldung.com/spring-security-login
	
http
		.cors()
		.and().csrf().disable().httpBasic()
		
		.and().authorizeRequests()
		.antMatchers("/api/oeuvre").permitAll();
*/

	
/* */
	http
	.cors() // sans cette ligne, impossible de se connecter depuis angular
	.and().csrf().disable() // sans cette ligne, aucune requête POST ne passe... Il faut trouver une solution pcq je ne pense pas que ce soit bon de désactiver le csrf
		.httpBasic()
		.and()
		.authorizeRequests()
  			.antMatchers("/admin").hasRole("ADMIN")
  			.antMatchers("/user").hasAnyRole("ADMIN","USER")
  			.antMatchers("/api/login").permitAll()
  			.antMatchers("/api/logemail").permitAll()
  			.antMatchers("/api/oeuvres").permitAll() // permet à tout le monde de faire toutes les requetes...il faudrait créer un nouvel pour le post peut etre
  			.antMatchers("/api/oeuvre").permitAll()
  			.antMatchers("/api/createuser").permitAll()
  			//.antMatchers("/api/genres").hasAnyRole("ADMIN","USER")
  			.antMatchers("/api/genres").permitAll()
  			.antMatchers("/api/genres/*").permitAll()
  			//.antMatchers("/api/categories").hasAnyRole("ADMIN","USER")
  			.antMatchers("/api/categories").permitAll()
  			.antMatchers("/api/upload").permitAll()
  		//	.antMatchers("/api/**").permitAll()// accepte tous les utilisateurs
  			.anyRequest().authenticated() 
	.and()
  		.formLogin()
  		.loginPage("http://localhost:4200/signin")
  		.permitAll()
	.and()
  		.logout();
  		
  			//.permitAll();
}

//.and().csrf().disable().httpBasic();
//.and().formLogin();
		
		/*
		 * Permet de contourner l'erreur 403 obtenu pour la requete POST
		 * à creuser
		 */
	 // !!! Permet d'utiliser POST / DELETE
	// .csrf().disable();
		//.and()
		//.oauth2Login();

/*
@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
*/
/*
@Bean
public PasswordEncoder getPasswordEncoder() {
	return NoOpPasswordEncoder.getInstance();
}


 * 
 *  ----------  HASH PASSWORD
 *  
Option 1 - n'est pas considéré comme sécurisé pour un environnement de production, il est préférable hasher le password à l'extérieur :

User user = User.withDefaultPasswordEncoder()
  .username("user")
  .password("password")
  .roles("user")
  .build();
System.out.println(user.getPassword());
// {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG

Option 2 - n'est pas considéré comme sécurisé pour un environnement de production, il est préférable d'encoder / hasher le password à l'extérieur:

UserBuilder users = User.withDefaultPasswordEncoder();
User user = users
  .username("user")
  .password("password")
  .roles("USER")
  .build();
User admin = users
  .username("admin")
  .password("password")
  .roles("USER","ADMIN")
  .build();
  
  
  ----------  ENCODE PASSWORD


https://docs.spring.io/spring-security/site/docs/current/reference/html5

  Option 1 : 
  
  spring encodepassword password
{bcrypt}$2a$10$X5wFBtLrL/kHcmrOGGTrGufsBX8CJ0WpQpF3pgeuxBB/H73BK1DW6


Option 2 : 

//Create an encoder with strength 16
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
String result = encoder.encode("myPassword");
assertTrue(encoder.matches("myPassword", result));

Option 3 - The current implementation of the Argon2PasswordEncoder requires BouncyCastle:

// Create an encoder with all the defaults
Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
String result = encoder.encode("myPassword");
assertTrue(encoder.matches("myPassword", result));

Option 4 - when FIP certification is required :

// Create an encoder with all the defaults
Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
String result = encoder.encode("myPassword");
assertTrue(encoder.matches("myPassword", result));

Option 5 :

// Create an encoder with all the defaults
SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
String result = encoder.encode("myPassword");
assertTrue(encoder.matches("myPassword", result));


https://docs.spring.io/spring-security/site/docs/current/reference/html5/#headers

------- Response header
*/

//   aide spring pour le CORS --> https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
/*
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
        @Override
public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/api/**")
    //registry.addMapping("/**")
		.allowedOrigins("http://localhost:4200/")
		.allowedMethods("PUT", "DELETE", "POST")
			.allowedHeaders("header1", "header2", "header3")
		.exposedHeaders("header1", "header2")
		.allowCredentials(false).maxAge(3600);
    };
    };
}
*/
}