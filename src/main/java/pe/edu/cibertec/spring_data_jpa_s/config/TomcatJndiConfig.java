package pe.edu.cibertec.spring_data_jpa_s.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Configuration
public class TomcatJndiConfig {

    @Value("${DB_SAKILA_URL}")
    private String bdSakilaUrl;
    @Value("${DB_SAKILA_USER}")
    private String bdSakilaUser;
    @Value("${DB_SAKILA_PASS}")
    private String bdSakilaPass;
    @Value("${DB_SAKILA_DRIVER}")
    private String bdSakilaDriver;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(bdSakilaUrl);
        config.setUsername(bdSakilaUser);
        config.setPassword(bdSakilaPass);
        config.setDriverClassName(bdSakilaDriver);

        // configuracion de HikariCP
        config.setMaximumPoolSize(20); // numero maximo de conexiones en el pool
        config.setMinimumIdle(5); // numero minimo de conexiones que deben mantenerse inactivas en el pool
        config.setIdleTimeout(300000); // 5 minutos, tiempo en milisegundos despues del cual una conexion inactiva puede ser eliminada
        config.setConnectionTimeout(30000); // 30 segundos, tiempo en milisegundos que el pool espera para obtener una conexion antes de lanzar una excepcion

        return new HikariDataSource(config);
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatFactoryCustomizer(HikariDataSource hikariDataSource) {
        return factory -> factory.addContextCustomizers(context -> {
            try {
                Context initCtx = new InitialContext();
                initCtx.bind("java:comp/env/jdbc/MySakila", hikariDataSource);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        });
    }

}
