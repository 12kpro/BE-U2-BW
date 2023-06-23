package epicenergyservice.u2bw.auth;


import epicenergyservice.u2bw.exceptions.ExceptionHandlerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JWTAuthFilter jwtAuthFilter;
    @Autowired
    ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(c -> c.disable());
        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/import/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ruoli/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/indirizzo/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/fatture/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/comuni/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/province/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/statofatture/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/tipocliente/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/clienti/**").authenticated());

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
    @Bean
    PasswordEncoder pwEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
