package com.seleccion.nuvu.clientes.security.config;

import com.seleccion.nuvu.clientes.domain.services.DetallesUsuarioService;
import com.seleccion.nuvu.clientes.security.filters.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DetallesUsuarioService detallesUsuarioService;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detallesUsuarioService);
    }

    // @Bean
    // public PasswordEncoder noPasswordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

    // @Bean
    // PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().disable().csrf().disable()
				.authorizeRequests().antMatchers("/api/v1/auth/**").permitAll().
                        anyRequest().authenticated()
                        .and().sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
