package equipe4.atlanticshippingmasters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class MvcConfig {

	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

}
