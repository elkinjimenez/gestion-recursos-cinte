package com.claro.gestionrecursosweb.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import com.claro.gestionrecursosweb.seguridad.domain.PasswordEncoder;
import com.claro.gestionrecursosweb.seguridad.domain.SeguridadService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${claro.dominio.opcionesfrontconsultargrupo.nombre}")
    protected String dominio_opcionesfrontconsultargrupo;

    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder();
    }

    @Autowired
    private SeguridadService userDetailsService;
    @Autowired
    private CustomAuthenticationProvider authProvider;

    // Necesario para evitar que la seguridad se aplique a los resources
    // Como los css, imagenes y javascripts
    String[] resources = new String[]{"/Estilos/**", "/Complementos/**", "/Scripts/**", "/img/**", "/js/**", "/layer/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(resources).permitAll()
                .antMatchers("/Hallazgo/Crear/**").permitAll()
                .antMatchers("/", "/api/v1/cargue/presupuesto").permitAll()
                .antMatchers("/Empleado/*").permitAll()
                //.antMatchers("/*").access("hasRole('USUARIO') or hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/Seguridad/Ingresar")
                .loginProcessingUrl("/Seguridad/Ingresar").permitAll()
                .usernameParameter("usuario").passwordParameter("clave")
                .failureUrl("/Seguridad/Ingresar?error=true")
                .and()
                .logout()
                .logoutUrl("/Seguridad/CerrarSesion")
                .logoutSuccessUrl("/")
                .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/Seguridad/Ingresar");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/Hallazgo/Crear/**")
                .antMatchers("/Hallazgo/Filtro/**")
                .antMatchers("/Estimaciones/Crear/**")
                .antMatchers("/Seguridad/OlvidoPassword/**")
                .antMatchers("/Seguridad/sendMail/**")
                .antMatchers("/Estimaciones/Filtro/**");
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}