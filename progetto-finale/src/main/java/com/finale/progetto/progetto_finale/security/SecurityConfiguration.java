package com.finale.progetto.progetto_finale.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. ABILITAZIONE CORS (FONDAMENTALE PER REACT)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. Configurazione CSRF
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/login", "/books/**"))
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()))

                // 3. Autorizzazioni
                // 3. Autorizzazioni
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/login").permitAll()

                        // PERMETTI A TUTTI DI LEGGERE LA NUOVA API LIBRARY
                        .requestMatchers("/api/library", "/api/library/**").permitAll()

                        // SOLO ADMIN PUÒ MODIFICARE (Aggiornato per il nuovo percorso)
                        // Nota: Se i tuoi endpoint di modifica sono sempre sotto /books/edit, lascia
                        // pure quelli.
                        // Se li hai spostati sotto /api/library, usa la riga commented qui sotto.
                        .requestMatchers("/books/edit", "/books/edit/**").hasAuthority("ADMIN")
                        // .requestMatchers("/api/library/edit",
                        // "/api/library/edit/**").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/library/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/library/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/library/**").hasAuthority("ADMIN")

                        // Tutto il resto richiede autenticazione
                        .anyRequest().authenticated())

                // 4. Configurazione del Login
                .formLogin(form -> form
                        .loginProcessingUrl("/api/login")
                        .successHandler((request, response, authentication) -> {
                            // --- MODIFICA CHIAVE ---
                            // Crea esplicitamente la sessione HTTP e salva l'autenticazione.
                            // Questo garantisce che il cookie JSESSIONID venga generato e inviato al
                            // browser.
                            request.getSession(true);

                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ADMIN"));

                            response.setContentType("application/json");
                            // Evita che il browser caching la risposta
                            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

                            if (isAdmin) {
                                response.getWriter().write("{\"role\": \"ADMIN\"}");
                            } else {
                                response.getWriter().write("{\"role\": \"GUEST\"}");
                            }
                        })
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(401);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Credenziali errate\"}");
                        })
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    // 5. Metodo per configurare il CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true); // Fondamentale per i cookie
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}