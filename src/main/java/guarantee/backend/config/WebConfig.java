package guarantee.backend.config;

import guarantee.backend.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Role roleSaler() {
        return new Role(2L, "ROLE_SALER");
    }

    @Bean
    public Role roleAdmin() {
        return new Role(1L, "ROLE_ADMIN");
    }

    @Bean
    public Role roleTech() {
        return new Role(3L, "ROLE_TECHNICIANS");
    }
}
