package com.multi.oauth20server;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	// application.properties 의 Data Source 설정을 사용하기 위한 멤버필드
	private final DataSource dataSource;

	// 생성자를 이용해 Data Source 의존성 주입
	public SpringSecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// 사용자 password를 일방향 암호화하기 위한 Bean 설정
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService users() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		// 아래 formatter:off 어노테이션은 STS의 ctrl + shift + f 시 정렬 X
		// 그리고 아래 user 추가하는 로직은 테스트 위해 넣은 로직이지, 실제에선 아래 로직 필요없음
		// @formatter:off
		try {
			jdbcUserDetailsManager.loadUserByUsername("user1");
		} catch (UsernameNotFoundException ex) {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			UserDetails user =
					User.withUsername("user1")
						.passwordEncoder(encoder::encode).password("1234")
						.roles("USER").build();
					jdbcUserDetailsManager.createUser(user);
		}
		// @formatter:on

		return jdbcUserDetailsManager;
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	/**
	 * * 보안 설정 - AuthorizationServerConfiguration에서 기본 보안설정을 적용할 것이므로 여기서는 모든 요청에 대해
	 * 인증을 요구하는 것으로 설정해도 됨 - FormLogin을 Default로 설정
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
	        http
	            .authorizeHttpRequests(authorize ->
	              authorize.anyRequest().authenticated()
	            )
	            .formLogin(Customizer.withDefaults());
	        return http.build();
	        // @formatter:on
	}
	
}
