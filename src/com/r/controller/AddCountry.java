package com.r.controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import com.r.model.Continent;
import com.r.model.Country;
import com.r.model.GameBoard;
import com.r.utility.MapParser;
import com.r.utility.StaticVariables;
import com.r.view.ApplicationWindow;

/**
 * This class performs the functioning of adding a Country according to the
 * events from the front end.
 * 
 * @author Raghav verma
 * @version 1.0
 */
public class AddCountry extends JFrame implements ActionListener {

	JPanel panel;
	JFrame frame;
	JLabel lblContinentName, lblCountryName, banner;
	JTextField txtCountryName, txtContinentName;
	JButton buttonOK, buttonCancel;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to select the
	 * name of Continent from a list, the X and Y cordinates, and the country name
	 * to be added.
	 * 
	 * @param actionEvent
	 *            Not used.
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		frame = new JFrame("Add Country Frame");
		frame.setSize(600, 150);
		Container content = getContentPane();
		content.removeAll();

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(20, 10, 10, 10));

		JPanel panelComboBox = new JPanel();
		panelComboBox.setLayout(new FlowLayout());

		lblContinentName = new JLabel("Select Continent: ");
		String[] continentString = new String[StaticVariables.gb.map.GetContinents().size()];
		int i = 0;
		for (Object o : StaticVariables.gb.map.GetContinents()) {
			continentString[i] = ((Continent) o).GetName();
			i++;
		}
		final JComboBox<String> comboBoxList = new JComboBox<>(continentString);

		JLabel CountryNametobeadded = new JLabel("Country Name:");
		final JTextField CountryField = new JTextField(10);
		JLabel enterXcordinates = new JLabel("X:");
		final JTextField xField = new JTextField(5);

		JLabel enterYcordinates = new JLabel("Y:");
		final JTextField yField = new JTextField(5);

		JButton buttonOK = new JButton("ADD COUNTRY ");
		/**
		 * Causes addition of a country to the continent selected by user on press of OK
		 * button.
		 * 
		 * @param actionEvent
		 *            Not used.
		 */
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = comboBoxList.getSelectedItem().toString();
				int id = StaticVariables.gb.map.GetContinentIdByName(name);

				if (StaticVariables.gb.map.DoesExistCountry(
						StaticVariables.gb.map.GetCountryIdByName(CountryField.getText())) == true) {
					JOptionPane.showMessageDialog(null, CountryField.getText() + " Already Exists");
				} else {
					int before = StaticVariables.gb.map.GetCountries().size();
					System.out.println("Before is :" + before);
					String output = StaticVariables.gb.map.AddCountry(CountryField.getText(), id,
							Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
					System.out.println("HERE1: " + output);
					int after = StaticVariables.gb.map.GetCountries().size();
					System.out.println(after + "After");

					try {
						StaticVariables.gb.SaveMapToFile("output.txt");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, CountryField.getText() + " Country Added to " + name);
					frame.dispose();
					try {
						StaticVariables.gb.SaveMapToFile("output.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}

		});

		/**
		 * Causes frame to be closed on press of cancel button.
		 * 
		 * @param actionEvent
		 *            Not used.
		 */
		JButton buttonCancel = new JButton("Cancel");

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		panelComboBox.add(lblContinentName);
		panelComboBox.add(comboBoxList);
		panelComboBox.add(comboBoxList);
		panelComboBox.add(CountryNametobeadded);
		panelComboBox.add(CountryField);
		panelComboBox.add(enterXcordinates);
		panelComboBox.add(xField);
		panelComboBox.add(enterYcordinates);
		panelComboBox.add(yField);

		panel.add(panelComboBox);
		panel.add(buttonOK);
		panel.add(buttonCancel);

		frame.add(panel);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
