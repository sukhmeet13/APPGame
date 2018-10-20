package com.r.model;

import com.r.model.Continent;
import com.r.model.Country;
import com.r.model.Land;

/**
 * This class is used to construct objects of continent and country classes
 * 
 * @author Raghav verma
 * @version 1.0.0.0
 */

public class Factory {

	public static Land GetLand(String type, String prm_name, int prm_continentId, int prm_x, int prm_y,
			int prm_control) {
		switch (type) {
		case "Country":
			return new Country(prm_name, prm_continentId, prm_x, prm_y);

		case "Continent":
			return new Continent(prm_name, prm_control);

		default:
			return null;

		}
	}

}
