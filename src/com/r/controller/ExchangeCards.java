package com.r.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.r.utility.StaticVariables;

public class ExchangeCards extends JFrame implements ActionListener {

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int output = StaticVariables.gb
					.ExchangeCards(StaticVariables.gb.turnOrganizer.GetCurrentPlayerId());
			if (output == 1)
				JOptionPane.showMessageDialog(null, "Cards Excahnged");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
