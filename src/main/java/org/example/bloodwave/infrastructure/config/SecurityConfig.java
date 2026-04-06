package org.example.bloodwave.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.example.bloodwave.infrastructure.config.filters.JwtAuthenticationFilter;
import org.example.bloodwave.domain.repository.UtilisateurRepository;
import org.example.bloodwave.application.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UtilisateurRepository utilisateurRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Bean
    public JwtAuthenticationFilter jwtFilter() {
        return new JwtAuthenticationFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/webjars/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/collect-sang", "/api/collect-sang/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/demandes", "/api/demandes/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/inscriptions-collecte/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/hopital/stock/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/hopital/*/donations", "/api/hopital/donations/*").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/utilisateurs", "/api/utilisateurs/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/donneur/donneurs/filter/**", "/api/donneur/by-city").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/demandeurs/groupe-sanguin/**").permitAll()
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/donneur/**").hasAuthority("DONNEUR")
                        .requestMatchers(HttpMethod.GET, "/api/dons/history/**").hasAnyAuthority("DONNEUR", "HOPITAL", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/demandeurs").permitAll()
                        .requestMatchers("/api/demandeurs/**").hasAuthority("DEMANDEUR")
                        .requestMatchers(HttpMethod.POST, "/api/collect-sang/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.PUT, "/api/collect-sang/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/collect-sang/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.POST, "/api/stocks/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.PUT, "/api/stocks/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/stocks/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.POST, "/api/demandes/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.PUT, "/api/demandes/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/demandes/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.POST, "/api/hopital/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.PUT, "/api/hopital/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.PATCH, "/api/hopital/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/hopital/**").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.POST, "/api/dons").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.PUT, "/api/dons/*/approve", "/api/dons/*/cancel").hasAuthority("HOPITAL")
                        .requestMatchers(HttpMethod.GET, "/api/mouvements-stock/**").hasAnyAuthority("HOPITAL", "ADMIN")

                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of("http://localhost:*", "http://127.0.0.1:*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
