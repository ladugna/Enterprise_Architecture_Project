package retail.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/items/**").permitAll()
                .antMatchers("/customer").permitAll()
                .antMatchers("/customers").permitAll()
                .antMatchers("/admins").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/cart/*").hasRole("CUSTOMER")
//                .antMatchers(HttpMethod.DELETE , "/customers/{id}").hasRole("ADMIN")
                .antMatchers("/order/*").permitAll()
                .antMatchers("/creditcards/**").permitAll()
                .anyRequest()
                .authenticated();
    }
}
