package com.r.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.r.utility.StaticVariables;

/**
 * This class shows current player according to the events captured from the front end.
 * 
 * @author Raghav verma
 * @version 1.0
 */
public class ShowPlayer implements ActionListener {

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(StaticVariables.gb.turnOrganizer.GetCurrentPlayerId());
		System.out.println(StaticVariables.gb.turnOrganizer.GetCurrentPhase());
	}
}
