package de.growi.springframework.shell.extension;

import org.jline.terminal.Terminal;
import org.springframework.shell.InputProvider;

public interface TerminalBackedInputProvider extends InputProvider {

	public Terminal getTerminal();
}
