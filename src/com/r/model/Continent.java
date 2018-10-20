package com.r.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents continent which handles data variables like name, id,control and
 * playerId. this class has methods which can change the state of object.
 * 
 * @author Sukhmeet Kaur 
 * @version 1.0.0.0
 */

public class Continent extends Land {
	int control;
	int playerId;

	/**
	 * following is the constructor of Continent class, it inherits partially from
	 * the superclass  Land, to set the continentId and the name. the
	 * constructor also sets the control value. 
	 * 
	 * @param prm_name
	 *            this parameter will be the name of the continent which is created
	 * @param prm_control
	 *            this parameter represents the value of the control of the
	 *            continent
	 */
	public Continent(String prm_name, int prm_control) {
		super(prm_name);
		this.control = prm_control;
	}

	/**
	 * This method returns the continentID of the object
	 * 
	 * @return continentID as integer which is the Id of the object
	 */
	public int GetContinentId() {
		return this.id;
	}

	/**
	 * This method returns the value of the control of the object
	 * 
	 * @return control of the object as integer
	 */
	public int GetControl() {
		return control;
	}

}
