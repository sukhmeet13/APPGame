package com.r.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to represent a player. it maintains data variables like
 *  id and name of the player and  also it
 * has different methods to change the status of the object.
 * 
 * @author Raghav verma
 * @version 1.0.0.0
 */

public class Player {
	private int id;
	private String name;
	private List<Land> lands;
	private int armies;
	private int armiesFromCards;

	/**
	 * this is the constructor of the class it takes id and name and will assign them
	 * to the player
	 * 
	 * @param prm_id
	 *            , which its type is integer, will br the id of the player
	 * @param prm_name,
	 *            which its type is string, will be the name of the player
	 */
	public Player(int prm_id, String prm_name) {
		this.id = prm_id;
		this.name = prm_name;
		lands = new ArrayList<Land>();
		armies = 0;
		armiesFromCards = 0;
	}

	/**
	 * this method returns the id of the player
	 * 
	 * @return, which its type is integer, is the id of the player
	 */
	public int GetId() {
		return this.id;
	}

	/**
	 * this method returns the name of the player
	 * 
	 * @return, which its type is string, is the name of the player
	 */
	public String GetName() {
		return this.name;
	}

	/**
	 * this method adds a country to the player
	 * 
	 * @param prm_land
	 * 			@return, which its type is integer, is 1 if it is successful
	 *            otherwise is 0
	 */
	public int AddLand(Land prm_land) {
		int result = 0;
		if (!DoesOwnLand(prm_land)) {
			this.lands.add(prm_land);
			result = 1;
		}
		return result;
	}

	/**
	 * this method removes a country from the list of countries that the player has
	 * 
	 * @param prm_land,
	 *            which its type is Land @return, which its type is integer, is 1 if
	 *            it is successful otherwise is 0
	 */
	public int RemoveLand(Land prm_land) {
		int result = 0;
		if (!DoesOwnLand(prm_land)) {
			this.lands.remove(prm_land);
			result = 1;
		}
		return result;
	}

	/**
	 * this method verifies if the land belongs to the player
	 * 
	 * @param prm_land,
	 *            which its type is Land @return, which its type is boolean, is true
	 *            if the land belongs to the player otherwise is 0
	 */
	public boolean DoesOwnLand(Land prm_land) {
		boolean result = false;
		for (Land c : this.lands) {
			if (prm_land.GetId() == c.GetId()) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method sets the number of armies of the player
	 * 
	 * @param prm_armies,
	 *            which its type is integer, will assign to the player
	 */
	public void SetArmiesToplayer(int prm_armies) {
		this.armies = prm_armies;
	}

	/**
	 * this method returns the number of armies that the player has
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return the number of armies of the player
	 */
	public int GetArmies() {
		return this.armies;
	}

	/**
	 * this method sets the number of armies
	 * 
	 * @param prm_armies
	 *            is the number of new armies
	 */
	public void SetArmies(int prm_armies) {
		this.armies = prm_armies;
	}

	/**
	 * this methos adds the armies exchanged with cards
	 * 
	 * @param prm_armies
	 *            which is the number of armies that will be added to the player
	 */
	public void AddArmiesFromCards(int prm_armies) {
		this.armiesFromCards += prm_armies;
		// tbd
		this.armies += prm_armies;
	}

	/**
	 * this method returns the number of armiesFromCards that the player has
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return the number of armies of the player
	 */
	public int GetArmiesFromCards() {
		return this.armiesFromCards;
	}

}
