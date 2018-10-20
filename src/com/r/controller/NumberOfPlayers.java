package com.r.controller;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.r.utility.StaticVariables;

/**
 * This class is used for selecting Number of players which will play the game, input
 * is taken from the user.
 * 
 * @author Raghav verma
 * @version 1.0
 */

public class NumberOfPlayers implements ActionListener {

	JPanel panel;
	JFrame jFrame;
	JLabel selectplayerlabel;
	JTextField txtCountryName, txtContinentName;
	JButton buttonOK, buttonCancel;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		jFrame = new JFrame("Set Number Of players ");
		jFrame.setSize(410, 170);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder(new EmptyBorder(25, 15, 15, 15));
		JPanel jPanelCB = new JPanel();
		jPanelCB.setLayout(new FlowLayout());
		JLabel jlbSelectPlayer = new JLabel("Please! Select Number of  Players:");
		int intMaxnmbOfplayers = 5;
		final JComboBox jcbCBList = new JComboBox();
		for (int i = 0; i <= intMaxnmbOfplayers; i++) {
			jcbCBList.addItem(i);
		}
		
		//ok buton action listener perforemd
		JButton jbtnOK = new JButton("OK ");
		jbtnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int intNmbOfPlayerSelected = Integer.parseInt(jcbCBList.getSelectedItem().toString());
				if (intNmbOfPlayerSelected < 2) {
					JOptionPane.showMessageDialog(null, "Attention! Minimum Number of Players Must be 2  ");
					jFrame.dispose();
				} else {
					try {
						StaticVariables.gb.StartupGame(intNmbOfPlayerSelected);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Number of Players Selected  " + intNmbOfPlayerSelected);
				}
			}
		});
		
		//cancel button action listener
		JButton jbtnCancel = new JButton("Cancel");
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		
		
		//adding components to panel and adding panel to frame
		jPanelCB.add(jlbSelectPlayer);
		jPanelCB.add(jcbCBList);
		jPanel.add(jPanelCB);
		jPanel.add(jbtnOK);
		jPanel.add(jbtnCancel);
		jFrame.add(jPanel);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
