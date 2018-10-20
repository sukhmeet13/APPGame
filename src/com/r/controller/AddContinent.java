package com.r.controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.r.utility.StaticVariables;

/** 
 * This class performs the functioning of adding a continent according to the
 * events from the front end.
 * 
 * @author Raghav verma
 * @version 1.0
 */

public class AddContinent implements ActionListener {
	static JFrame frame;

	@Override
	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer.
	 * @param actionEvent Not used.
	 */
	public void actionPerformed(ActionEvent e) {
		frame = new JFrame("Add Continent Frame");
		frame.setSize(350, 200);

		JLabel continentLabel = new JLabel("Continent Name:");

		final JTextField nameTField = new JTextField(15);

		JLabel continentcontrolLabel = new JLabel("Control Value:");

		final JTextField continentfield = new JTextField(10);

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JButton buttonOK = new JButton("OK");

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (StaticVariables.gb.map.DoesExistContinent(nameTField.getText()) == true) {
					JOptionPane.showMessageDialog(null, "Continent with name " + nameTField.getText() + " Exists!");
				} else {
					String output = StaticVariables.gb.map.AddContinent(nameTField.getText(),
							Integer.parseInt(continentfield.getText()));
					System.out.println(output);
					System.out.println(StaticVariables.gb.map.lands.size());
					JOptionPane.showMessageDialog(null, "Continent with name " + nameTField.getText()
							+ ",Control value : " + continentfield.getText() + " is successfully added!");

					try {
						StaticVariables.gb.SaveMapToFile("output.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					frame.dispose();
				}

			}
		});

		JButton buttonCancel = new JButton("Cancel");

		/**
		 * Causes frame to get closed on selecting Cancel button.
		 * 
		 * @param actionEvent
		 *            Not used.
		 */
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		panel.add(continentLabel);
		panel.add(nameTField);
		panel.add(continentcontrolLabel);
		panel.add(continentfield);
		panel.add(buttonOK);
		panel.add(buttonCancel);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
