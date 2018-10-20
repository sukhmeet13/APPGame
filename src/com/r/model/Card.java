package com.r.model;

import com.r.utility.*;

/**
 * this class represents the card details such as id, tyoe of card and player id.
 * 
 * @author Raghav verma
 * @version 1.0.0.0
 */
public class Card {
	private static int counter = 0;
	protected int id;
	protected ExchangeCards type;
	protected int playerId;

	/**
	 * this is the constructor it takes the name and creates the card object
	 * 
	 * @param prm_name
	 */
	public Card(ExchangeCards prm_name) {
		counter++;
		this.id = this.counter;
		this.type = prm_name;
		this.playerId = -1;

	}

	/**
	 * this method returns the name of the card
	 * 
	 * @return the type of the object
	 */
	public ExchangeCards GetType() {
		return this.type;
	}

	/**
	 * this method sets playerId
	 * 
	 */
	public void SetPlayerId(int prm_playerId) {
		this.playerId = prm_playerId;
	}
}
