package de.growi.springframework.shell.extension;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import org.jline.terminal.Attributes;
import org.jline.terminal.Cursor;
import org.jline.terminal.MouseEvent;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.NonBlockingReader;

import de.growi.springframework.shell.extension.MultiTerminalShell.REPLThread;

public class Switchboard implements Terminal{
	
	Map<Long, Terminal> terminals = new ConcurrentHashMap<>();
	
	public Switchboard() throws IOException {
		terminals.put(Thread.currentThread().getId(), TerminalBuilder.builder().build() );
	}
	
	public void addTerminalThread(REPLThread thread) {
		terminals.put(thread.getId(), thread.getInputProvider().getTerminal());
	}
	
	private Terminal getTerminal() {
		System.out.println("acessing terminal for thread: " + Thread.currentThread().getId());
		return terminals.get(Thread.currentThread().getId());
	}

	@Override
	public void close() throws IOException {
		getTerminal().close();
	}

	@Override
	public String getName() {
		return getTerminal().getName();
	}

	@Override
	public SignalHandler handle(Signal signal, SignalHandler handler) {
		return getTerminal().handle(signal, handler);
	}

	@Override
	public void raise(Signal signal) {
		getTerminal().raise(signal);
	}

	@Override
	public NonBlockingReader reader() {
		return getTerminal().reader();
	}

	@Override
	public PrintWriter writer() {
		return getTerminal().writer();
	}

	@Override
	public InputStream input() {
		return getTerminal().input();
	}

	@Override
	public OutputStream output() {
		return getTerminal().output();
	}

	@Override
	public Attributes enterRawMode() {
		return getTerminal().enterRawMode();
	}

	@Override
	public boolean echo() {
		return getTerminal().echo();
	}

	@Override
	public boolean echo(boolean echo) {
		return getTerminal().echo(echo);
	}

	@Override
	public Attributes getAttributes() {
		return getTerminal().getAttributes();
	}

	@Override
	public void setAttributes(Attributes attr) {
		getTerminal().setAttributes(attr);		
	}

	@Override
	public Size getSize() {
		return getTerminal().getSize();
	}

	@Override
	public void setSize(Size size) {
		getTerminal().setSize(size);		
	}

	@Override
	public void flush() {
		getTerminal().flush();		
	}

	@Override
	public String getType() {
		return getTerminal().getType();
	}

	@Override
	public boolean puts(Capability capability, Object... params) {
		return getTerminal().puts(capability, params);
	}

	@Override
	public boolean getBooleanCapability(Capability capability) {
		return getTerminal().getBooleanCapability(capability);
	}

	@Override
	public Integer getNumericCapability(Capability capability) {
		return getTerminal().getNumericCapability(capability);
	}

	@Override
	public String getStringCapability(Capability capability) {
		return getTerminal().getStringCapability(capability);
	}

	@Override
	public Cursor getCursorPosition(IntConsumer discarded) {
		return getTerminal().getCursorPosition(discarded);
	}

	@Override
	public boolean hasMouseSupport() {
		return getTerminal().hasMouseSupport();
	}

	@Override
	public boolean trackMouse(MouseTracking tracking) {
		return getTerminal().trackMouse(tracking);
	}

	@Override
	public MouseEvent readMouseEvent() {
		return getTerminal().readMouseEvent();
	}

	@Override
	public MouseEvent readMouseEvent(IntSupplier reader) {
		return getTerminal().readMouseEvent(reader);
	}
}
