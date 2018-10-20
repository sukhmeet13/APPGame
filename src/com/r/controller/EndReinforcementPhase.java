package com.r.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.r.utility.StaticVariables;

/**
 * This class belongs to a listener which is used to end the Reinforcement
 * phase on getting the corresponding event from front end.
 * 
 * 
 * @author Raghav verma
 * @version 1.0
 */

public class EndReinforcementPhase implements ActionListener {
	static JFrame frame;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer.
	 * 
	 * @param actionEvent
	 *            Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		frame = new JFrame("End Reinforcement Phase");
		frame.setSize(300, 150);
		JPanel panel = new JPanel();
		JLabel Message = new JLabel("Do You Want To End Reinforcement Phase ? ");
		System.out.println(StaticVariables.gb.turnOrganizer.GetCurrentPhase());
		JButton buttonOK = new JButton("OK");
		buttonOK.addActionListener(new ActionListener() {

			/**
			 * Causes a new window to Pop-up. This window then asks the user to indicate the
			 * Continent name, the Control value. The control value must be set as Integer.
			 * 
			 * @param actionEvent
			 *            Not used.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				StaticVariables.gb.EndReinforcementPhase();
				System.out.println(StaticVariables.gb.turnOrganizer.GetCurrentPhase());
				JOptionPane.showMessageDialog(null, " Reinforcement Phase Ended  Successfully");
				frame.dispose();
			}
		});
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(Message);
		panel.add(buttonOK);
		panel.add(buttonCancel);
		frame.add(panel);
		frame.setVisible(true);
	}
}
