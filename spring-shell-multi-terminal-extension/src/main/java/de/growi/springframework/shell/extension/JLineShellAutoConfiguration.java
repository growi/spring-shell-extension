package de.growi.springframework.shell.extension;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.shell.ResultHandler;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class JLineShellAutoConfiguration extends org.springframework.shell.jline.JLineShellAutoConfiguration {

	@Autowired
	ResultHandler resultHandler;
	
	@Bean(destroyMethod = "close")
	public Terminal terminal() {
		try {
			return new Switchboard();
		}
		catch (IOException e) {
			throw new BeanCreationException("Could not create Terminal: " + e.getMessage());
		}
	}
}
