package br.gov.sp.fatec.livraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.gov.sp.fatec.livraria.service.impl.UserDetailsImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsImpl userDetailsImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
					.antMatchers("/admin/**").hasAnyRole("ADMIN")
					.antMatchers("/carrinho/**").hasAnyRole("CLIENTE")
					.antMatchers("/perfil/**").hasAnyRole("CLIENTE")
				.and()
					.formLogin().loginPage("/liv/entrar").permitAll().defaultSuccessUrl("/home", true)
				.and()
					.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/*.html", "/v2/api-docs", "/webjars/", "/configuration/", "/swagger-resources/*", "cdnjs.cloudflare.com/*");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsImpl);
		return daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
