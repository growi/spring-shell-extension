package de.growi.springframework.shell.extension;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.shell.ResultHandler;
import org.springframework.shell.SpringShellAutoConfiguration;

@Configuration
//@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class MultiTerminalShellAutoConfiguration {//extends SpringShellAutoConfiguration {

	@Bean
	public MultiTerminalShell shell(@Qualifier("main") ResultHandler resultHandler) {
		return new MultiTerminalShell(resultHandler);
	}
}
