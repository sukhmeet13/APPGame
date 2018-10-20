package com.r.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.r.utility.StaticVariables;

/**
 * This class belongs to a listener which Saves the static variables to a map file.
 * 
 * @author Raghav verma
 * @version 1.0
 */
public class SaveListener implements ActionListener {
	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			StaticVariables.gb.SaveMapToFile("output.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
