package com.r.model;

import com.r.model.ILand;

/**
 * This class represents the base class for continent and country class.
 *  it maintains variables such as id and name.
 * 
 * @author Sukhmeet Kaur
 * @version 1.0.0.0
 */

public class Land implements ILand {
	private static int counter = 0;
	protected int id;
	protected String name;
	protected int playerId;

	/**
	 * this constructor generates the land object. it takes the name of the object
	 * and generates the id of the object as a unique incremental integer. this
	 * constructor will be used to construct continents and countries.
	 * 
	 * @param prm_name
	 *            , which is string, is the name of the new object
	 */
	public Land(String prm_name) {
		counter++;
		this.id = this.counter;
		this.name = prm_name;
		this.playerId = -1;
	}

	/**
	 * this method returns the name of the object which is used in the continent and
	 * country instances
	 * 
	 * @return , which is string, the name of the object
	 */
	public String GetName() {
		return this.name;
	}

	/**
	 * this function returns the id of the object which is used in the continent and
	 * country instances
	 * 
	 * @return ,which is integer, is the id of the object, which could be a
	 *         continent or a country
	 */
	public int GetId() {
		return this.id;
	}

}
