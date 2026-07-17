package com.sgp.sgp.config;

// Importa el filtro personalizado encargado de validar el Token JWT
import com.sgp.sgp.security.JwtFilter;

// Importaciones de Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Importaciones de Spring Security
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
    @Configuration indica que esta clase contiene
    la configuración de seguridad de la aplicación.
*/
@Configuration
public class SecurityConfig {

    /*
     * Filtro personalizado encargado de interceptar
     * las peticiones HTTP y validar el Token JWT.
     */
    private final JwtFilter jwtFilter;

    /*
     * Inyección del JwtFilter mediante constructor.
     */
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /*
     * Bean encargado de configurar la cadena
     * de filtros de seguridad de Spring Security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                /*
                 * Desactiva CSRF.
                 * 
                 * Se utiliza esta configuración porque
                 * la aplicación trabaja como una API REST
                 * con autenticación mediante Token JWT.
                 */
                .csrf(csrf -> csrf.disable())

                /*
                 * Habilita la configuración CORS.
                 * 
                 * Permite la comunicación entre:
                 * 
                 * React + Vite
                 * http://localhost:5173
                 * 
                 * y
                 * 
                 * Spring Boot
                 * http://localhost:8080
                 */
                .cors(cors -> {
                })

                /*
                 * Configura la aplicación para trabajar
                 * sin sesiones en el servidor.
                 * 
                 * Cada petición debe enviar su propio
                 * Token JWT.
                 */
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))

                /*
                 * Configuración de autorización
                 * de las peticiones HTTP.
                 */
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/api/auth/**",
                                "/api/publica/**",
                                "/api/usuarios/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/webjars/**"
                        ).permitAll()

                        .anyRequest().permitAll()
                        )

                /*
                 * Desactiva la autenticación HTTP Basic.
                 * 
                 * La aplicación utiliza JWT.
                 */
                .httpBasic(httpBasic -> httpBasic.disable())

                /*
                 * Desactiva el formulario de Login
                 * predeterminado de Spring Security.
                 * 
                 * Posteriormente utilizaremos nuestro
                 * propio formulario de Login desarrollado
                 * con React.
                 */
                .formLogin(form -> form.disable())

                /*
                 * Agrega JwtFilter antes del filtro
                 * estándar de autenticación de
                 * Spring Security.
                 * 
                 * Esto permite validar el Token JWT
                 * antes de permitir el acceso a los
                 * endpoints protegidos.
                 */
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        /*
         * Construye y devuelve la configuración
         * de seguridad.
         */
        return http.build();
    }
}
