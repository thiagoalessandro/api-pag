package br.com.tcb.api.config;

import javax.activation.DataSource;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.logging.Level;

@Log
@Configuration
@Profile("prod")
public class ProdConfig {
	 	
	@Bean
    public DataSource getDataSource() {
		DataSource ds = null;
        try {
			log.info("DataSource: prod");
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return ds;
    }
	
}
