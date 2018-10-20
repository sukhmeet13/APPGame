package com.r.controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.r.model.Continent;
import com.r.model.Map;
import com.r.utility.StaticVariables;


/**
 * This class belongs to a listener which Removes a continent from the map file based
 * on the events captured from the user interface.
 * 
 * @author Raghav verma
 * @version 1.0
 */
public class RemoveContinent extends JFrame implements ActionListener {
	static JFrame jFrame;
	JLabel jlblContinenToBeRemoved;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		jFrame = new JFrame("Remove Continent Frame");
		jFrame.setSize(375, 225);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder(new EmptyBorder(25, 15, 15, 15));
		jlblContinenToBeRemoved = new JLabel("Please! Select the Continent: ");
		JPanel jpanelCB = new JPanel();
		jpanelCB.setLayout(new FlowLayout());
		String[] stringContinent = new String[StaticVariables.gb.map.GetContinents().size()];
		int i = 0;
		for (Object o : StaticVariables.gb.map.GetContinents()) {
			stringContinent[i] = ((Continent) o).GetName();
			i++;
		}
		final JComboBox<String> jcbCBList = new JComboBox<>(stringContinent);
		JButton jbtnOK = new JButton("OK");
		//action performed on button
		jbtnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK pressed:");
				String continentName = jcbCBList.getSelectedItem().toString();
				Continent cnt = new Continent(continentName, 25);
				System.out.println(cnt.GetName());
				System.out.println("Before" + StaticVariables.gb.map.GetContinents().size());
				StaticVariables.gb.map
						.RemoveContinent(StaticVariables.gb.map.GetContinentByName(continentName));
				try {
					//writing to output to file
					StaticVariables.gb.SaveMapToFile("output.txt");
				}
				//catching exception
				catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, cnt.GetName() + " Continent was deleted successfully!");
				System.out.println("After " + StaticVariables.gb.map.GetContinents().size());
				jFrame.dispose();
			}
		});
		
		JButton jbtnCancel = new JButton("Cancel");
		//action performed on button
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		
		//adding components to panel and adding panel to frame
		jpanelCB.add(jcbCBList);
		jPanel.add(jpanelCB);
		jPanel.add(jbtnOK);
		jPanel.add(jbtnCancel);
		
		jpanelCB.add(jcbCBList);
		jPanel.add(jlblContinenToBeRemoved);
		jPanel.add(jpanelCB);
		jPanel.add(jbtnOK);
		jPanel.add(jbtnCancel);
		jFrame.add(jPanel);
		jFrame.setVisible(true);
		jFrame.add(jPanel);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}