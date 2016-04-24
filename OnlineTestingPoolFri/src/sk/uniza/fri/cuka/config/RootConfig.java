package sk.uniza.fri.cuka.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/config/service-context.xml" })
@ComponentScan(basePackages={"sk.uniza.fri.cuka.ldap", "sk.uniza.fri.cuka.model"})
public class RootConfig {

}
