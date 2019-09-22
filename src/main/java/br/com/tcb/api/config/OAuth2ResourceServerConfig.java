package br.com.tcb.api.config;

import br.com.tcb.api.config.utils.ConfigurationAppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ConfigurationAppUtil configurationAppUtil;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String securityContext = configurationAppUtil.getAppContextApi().concat("/**");
        http.cors()
                .and()
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(configurationAppUtil.getAppSecurityEnable() ? securityContext : "/ignore/**").authenticated();
    }

}
