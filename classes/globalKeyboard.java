//version 1.0, 11/2015, Patrick Suche

import com.cycling74.max.*;
import java.util.Random;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class globalKeyboard extends MaxObject implements NativeKeyListener {


	public globalKeyboard(Atom[] args) {
		declareAttribute("autostart", null, "autoStart");
		declareIO(1, 3);
	}
	

	public void nativeKeyPressed(NativeKeyEvent e) {
		outlet(0, NativeKeyEvent.getKeyText(e.getKeyCode()));

	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		outlet(1, NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		outlet(2, e.getKeyText(e.getKeyCode()));
	}

	private void autoStart(int i){
		if (i == 1) start();
	}


	//////////////

	public void start(){
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			post("There was a problem registering the native hook.");
			post(ex.getMessage());
		}

		GlobalScreen.addNativeKeyListener(this);
	}

	////////////////
	public void stop(){
		GlobalScreen.removeNativeKeyListener(this);
	}
}






