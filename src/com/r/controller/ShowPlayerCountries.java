package com.r.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JComboBox;

import com.r.model.Country;
import com.r.utility.StaticVariables;

/**
 * this class is used to show the countries which are owned by the player.
 * 
 * @author Raghav verma
 * @version 1.0
 */
public class ShowPlayerCountries implements ActionListener {
	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer.
	 * 
	 * @param actionEvent
	 *            Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("The number of countries :" + StaticVariables.gb.map.GetCountries().size());
		System.out.println("The current player Id :" + StaticVariables.gb.turnOrganizer.GetCurrentPlayerId());
		System.out.println("The list of countries per player is : ");
		System.out.println(
				(StaticVariables.gb.map.GetCountriesByPlayerId(StaticVariables.gb.players.size()))
						.size());
	}
}
