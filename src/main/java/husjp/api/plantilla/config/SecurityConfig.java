package husjp.api.plantilla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import husjp.api.plantilla.service.impl.UserDetailServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                        authorizeRequests.requestMatchers(AUTH_WHITLIST).permitAll();
                        //ejemplo para proteger un endpoint
                        authorizeRequests.requestMatchers(HttpMethod.GET, "ejemplo/prueba").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET,"AreaServicio").hasAnyRole("ADMIN","COORD");
                      // Procesos
                        authorizeRequests.requestMatchers(HttpMethod.GET,"procesos").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.POST,"procesos").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.PUT,"procesos/{idproceso}").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.GET,"procesos/{idarea}").hasAnyRole("ADMIN","COORD");
                        //SUBPROCESOS
                        authorizeRequests.requestMatchers(HttpMethod.GET,"subprocesos").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.POST,"subprocesos").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.PUT,"subprocesos/{id}").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.GET,"subprocesos/{id}").hasAnyRole("ADMIN","COORD");
                        //UsuariosProcesos;
                        authorizeRequests.requestMatchers(HttpMethod.GET,"usuarioprocesos").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.GET,"usuarioprocesos/{usuarioid}").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.GET,"usuarioprocesos/area/{idArea}").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.POST,"usuarioprocesos").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.PUT,"usuarioprocesos/{id}").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.PUT,"usuarioprocesos/estado/{id}").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.DELETE,"usuarioprocesos/{id}").hasAnyRole("ADMIN","COORD");
                        authorizeRequests.requestMatchers(HttpMethod.DELETE,"usuarioprocesos/transferir").hasAnyRole("ADMIN","COORD");
                        
                        authorizeRequests.anyRequest().authenticated();
                    }
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //123456 PASSWORD
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.withUsername("admin")
//                .password("$2a$12$VjeG91WALEUJd6ARRMzZWeEU0kAdf2flxgqLO2oR9a25Y/9i1GcNi")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_WHITLIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
}
