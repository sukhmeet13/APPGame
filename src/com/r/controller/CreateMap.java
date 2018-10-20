package com.r.controller;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.r.model.GameBoard;

/**
 * This class creates a map from GUI.
 * 
 * @author raghav verma
 * @version 1.0
 */

public class CreateMap implements ActionListener {
	JFrame frame;
	JPanel panel;

	JLabel lblFileName, lblCountryName, banner;
	JTextField txtCountryName, txtContinentName;
	JButton buttonOK, buttonCancel;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer.
	 * @param ActionEvent   Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		frame = new JFrame("Create Map");
		frame.setSize(200, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(20, 10, 10, 10));
		lblFileName = new JLabel("Do You Want to Create Map");
		JButton buttonOK = new JButton("CREATE MAP  ");
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameBoard.GetGameBoard().CreateMap();
				JOptionPane.showMessageDialog(null, "Please Add Countries and Continents ,then Save file! ");
				frame.dispose();
			}
		});
		JButton buttonCancel = new JButton("Cancel");

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		panel.add(lblFileName);
		panel.add(buttonOK);
		panel.add(buttonCancel);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
