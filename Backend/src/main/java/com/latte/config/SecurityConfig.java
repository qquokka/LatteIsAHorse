//package com.latte.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception{
//		return super.authenticationManagerBean();
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.httpBasic().disable()
//			.csrf().disable()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//				.authorizeRequests()
//					.antMatchers("/*/signin", "/*/signup").permitAll()
//					.antMatchers(HttpMethod.GET, "helloworld/**").permitAll();
////		   .and()
////           .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣는다
//			
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
//                "/swagger-ui.html", "/webjars/**", "/swagger/**");
//	}
//}
