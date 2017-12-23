package de.growi.springframework.shell.extension;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Input;
import org.springframework.shell.ResultHandler;
import org.springframework.shell.Shell;

import de.growi.springframework.shell.extension.TerminalBackedInputProvider;

public class MultiTerminalShell extends Shell {
	
	@Autowired
	private Terminal switchboard;
	
	private ResultHandler resultHandler;

	public MultiTerminalShell(ResultHandler resultHandler) {
		super(resultHandler);
		this.resultHandler = resultHandler;
	}
	
	public void connectTerminal(TerminalBackedInputProvider inputProvider) {
		REPLThread thread = new REPLThread(inputProvider, resultHandler);
		((Switchboard)switchboard).addTerminalThread(thread);
		thread.start();
	}
	
	public void disconnectTerminal(Terminal terminal) {
		
	}
	
	public void run() {
		
	}
	
	class REPLThread extends Thread {
		
		private TerminalBackedInputProvider inputProvider;
		private ResultHandler resultHandler;
		
		public REPLThread(TerminalBackedInputProvider inputProvider, ResultHandler resultHandler) {
			this.inputProvider = inputProvider;
			this.resultHandler = resultHandler;
		}
		
		public TerminalBackedInputProvider getInputProvider() {
			return this.inputProvider;
		}
		
		@Override
		public void run() {
			
			while (true) {
				Input input;
				try {
					input = inputProvider.readInput();
				}
				catch (Exception e) {
					resultHandler.handleResult(e);
					continue;
				}
				if (input == null) {
					break;
				}
				Object result = evaluate(input);
				if (result != NO_INPUT) {
					resultHandler.handleResult(result);
				}
			}
		}
	}
}
