package br.com.tcb.api.config;

import javax.activation.DataSource;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.logging.Level;

@Log
@Configuration
@Profile("dev")
public class DevConfig {

	@Bean
    public DataSource getDataSource() {
		DataSource ds = null;
        try {
			log.info("DataSource: dev");
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return ds;
    }
	
}
