package equipe4.atlanticshippingmasters;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;






@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/login", "/js/**", "/css/**","/img/**","/", "/signup","/public/**").permitAll()
        .antMatchers("/admin/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").defaultSuccessUrl("/adminDashboard").permitAll()
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        return http.build();
    }
    
    @Autowired
   
    void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService);
       
    }

	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
    
}

