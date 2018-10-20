package com.r.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.r.utility.ExchangeCards;
import com.r.utility.MapParser;
import com.r.utility.TurnPhases;

/**
 * This class represents the game board as a Singleton .
 * it maintains the list of
 * players as well as the map.
 * It  provides the all objects and methods needed for
 * presentation the game.
 * 
 * @author Raghav verma
 * @version 1.0.0.0
 */

public class GameBoard {
	public List<Player> players;
	public Map map;
	public static GameBoard instance;
	public List<Integer> roundRobin;
	public TurnOrganizer turnOrganizer;
	public List<Card> deck;

	public GameBoard() {
		if (this.instance == null) {
			players = new ArrayList<Player>();
			map = new Map("map");
			turnOrganizer = new TurnOrganizer();
			deck = new ArrayList<Card>();

		}

	}

	/**
	 * this method returns an instance the object if the instance is created
	 * already, will be returned otherwise a new instance will be created and will
	 * be returned
	 * 
	 * @return an instance of GameBoard
	 */
	public static GameBoard GetGameBoard() {
		if (instance == null) {
			return instance = new GameBoard();
		} else
			return instance;
	}

	/**
	 * this method is used to load the map.
	 * 
	 * @param prm_input
	 *            , which its type is string is the map file that will be imported
	 *            into the game
	 * @throws Exception
	 */
	public void LoadMap(String prm_input) throws Exception {
		// tbd
		this.map = MapParser.MapParser(prm_input);
		turnOrganizer.MapLoaded();
	}

	/**
	 * this method starts up the game.it takes input as the number of players, then creates
	 * players and then assign countries evenly to players, then builds the deck next.
	 * it sets the current player based on round-robin fashion next initializes the
	 * phase
	 * 
	 * @throws Exception
	 *             if the number of player is less than 2 or greater than 5
	 */
	public void StartupGame(int prm_playerNum) throws Exception {
		// tbd
		if (prm_playerNum < 2 || prm_playerNum > 5)
			throw new Exception("PlayerNumberNotValid");
		SetupPlayers(prm_playerNum);
		AssignCountriesRandom();
		BuildDeck(map.GetCountries().size());
		turnOrganizer.GameStarted();
		turnOrganizer.SetCurrentPlayerId(GetNextPlayerId());
		// tbd
		turnOrganizer.SetCurrentPhase(TurnPhases.Reinforcement);
		;
	}

	/**
	 * this method creates 6 instance of players
	 */
	public void SetupPlayers() {
		for (int i = 1; i < 7; i++) {
			players.add(new Player(i, "player" + i));
		}
		roundRobin = new ArrayList<Integer>(players.size());
		InitRoundRobin();
	}

	/**
	 * this method creates instance of players as many as the playerNum
	 * 
	 * @param playerNum
	 *            , which is integer, is the number of players that user spesifies
	 *            at the beginning of the game
	 */
	public void SetupPlayers(int prm_playerNum) {
		for (int i = 1; i <= prm_playerNum; i++) {
			players.add(new Player(i, "player" + i));
		}
		roundRobin = new ArrayList<Integer>(players.size());
		InitRoundRobin();
	}

	/**
	 * this method will assign the companies to the players evenly
	 */
	public void AssignCountriesRandom() {
		Country countryRandom;
		while (map.GetCountriesNotAssigned().size() > 0) {
			for (Player p : players) {
				if (map.GetCountriesNotAssigned().size() > 0) {
					map.GetNotAssignedCountryRandom().SetPlayerId(p.GetId());
				} else
					break;
			}
		}
	}

	/**
	 * this method initialaizes the roundrobin objects it adds all players to the
	 * instance of roundrobin
	 */
	public void InitRoundRobin() {
		for (Player p : this.players) {
			roundRobin.add(p.GetId());
		}

	}

	/**
	 * this method returns the next player to play
	 * 
	 * @return the id of the next player who should play
	 */
	public int GetNextPlayerId() {
		int nextPlayerId = -1;
		if (roundRobin.size() < 1) {
			InitRoundRobin();
		}
		// tbd
		nextPlayerId = roundRobin.get(0);
		roundRobin.remove(0);
		// tbd
		this.turnOrganizer.SetCurrentPlayerId(nextPlayerId);
		// tbd
		CalculateArmies(GetPlayerById(turnOrganizer.GetCurrentPlayerId()));
		return nextPlayerId;
	}

	/**
	 * this method calls the map file validator from MapParser
	 * 
	 * @param input,
	 *            which is string, is the name of the map file to be validated
	 * @return , which is string, is valid if the map file meets the validation
	 *         criteria otherwise returns invalid
	 * @throws Exception
	 */
	public boolean MapValidator(String input) throws Exception {
		boolean result = false;
		try {
			result = MapParser.MapValidator(input);
		} catch (Exception ex) {
			throw ex;
		}
		return result;
	}

	/**
	 * this method calculate the number of armies that the player can have
	 * 
	 * @param ,
	 *            which its type is playerId, is the id of the player
	 * 
	 */
	public void CalculateArmies(Player prm_player) {
		int armiesNum = map.GetCountriesByPlayerId(prm_player.GetId()).size();
		armiesNum /= 3;
		prm_player.SetArmiesToplayer(armiesNum <= 3 ? 3 : armiesNum);

	}

	/**
	 * this method will save the current map into a file in the format of Conquest
	 * rules
	 * 
	 * @param input,
	 *            which its type is string, is the file in which the map will be
	 *            saved
	 * @return , which its type is integer, is 1 if the save is successful and is 0
	 *         if it is failed
	 * @throws Exception
	 *             if continents are less than 1 or countries are less than 5 the
	 *             exception will be thrown
	 */
	public int SaveMapToFile(String input) throws Exception {
		int result = 0;
		// tbd
		if (this.map.GetContinents().size() < 1)
			throw new Exception("MapHasNoContinent");
		if (this.map.GetCountries().size() < 5)
			throw new Exception("LessThanFiveCountriesValidation");
		MapParser.WriteMapToFile(map, input);
		return 1;
	}

	/**
	 * this method reset the map and get it ready to build it
	 * 
	 */
	public void CreateMap() {
		this.map = new Map("map1");
	}

	/**
	 * this function returns the player from its id
	 * 
	 * @param playerId
	 *            is the id of the player
	 * @return the player
	 */
	public Player GetPlayerById(int prm_playerId) {
		for (Player p : players) {
			if (p.GetId() == prm_playerId)
				return p;
		}
		return null;
	}

	/**
	 * this method places armies on a country
	 * 
	 * @param prm_countryId
	 *            is the id of the country on which armies are placed
	 * @param prm_armies
	 *            the number of armies to be placed
	 * @return 1 if it is successful otherwise 0
	 * @throws Exception
	 *             if the current phase not Reinforcement
	 */
	public int PlaceArmiesOnCountry(int prm_countryId, int prm_armies) throws Exception {
		int result = 0;
		// tbd
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Reinforcement)
			throw new Exception("PhaseNotValid");
		int countryArmies = map.GetCountryById(prm_countryId).GetArmies();
		int playerArmies = GetPlayerById(map.GetCountryById(prm_countryId).GetPlayerId()).GetArmies();
		if (playerArmies >= prm_armies) {
			map.GetCountryById(prm_countryId).SetArmies(countryArmies + prm_armies);
			GetPlayerById(map.GetCountryById(prm_countryId).GetPlayerId()).SetArmies(playerArmies - prm_armies);
			result = 1;
		} else
			throw new Exception("NotEnoughArmies");
		return result;
	}

	/**
	 * this method places armies on a country
	 * 
	 * @param prm_countryId
	 *            is the id of the country on which armies are placed
	 * @param prm_armies
	 *            the number of armies to be placed
	 * @return 1 if it is succesful otherwise 0
	 * @throws Exception
	 *             if the current phase not Fortification
	 */
	public int MoveArmiesToCountryFromCountry(int prm_countryIdS, int prm_countryIdD, int prm_armies) throws Exception {
		int result = 0;
		// tbd
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Fortification)
			throw new Exception("PhaseNotValid");
		int countrySArmies = map.GetCountryById(prm_countryIdS).GetArmies();
		int countryDArmies = map.GetCountryById(prm_countryIdD).GetArmies();
		if (countrySArmies >= prm_armies
				&& map.GetCountryById(prm_countryIdS).playerId == map.GetCountryById(prm_countryIdD).playerId) {
			map.GetCountryById(prm_countryIdS).SetArmies(countrySArmies - prm_armies);
			map.GetCountryById(prm_countryIdS).SetArmies(countryDArmies + prm_armies);
			result = 1;
		} else
			throw new Exception("NotEnoughArmies");
		return result;
	}

	// tbd
	/**
	 * this method ends the reinforcement phase this set the turn organizer to
	 * fortification phase
	 */
	public void EndReinforcementPhase() {
		turnOrganizer.SetCurrentPhase(TurnPhases.Fortification);
	}

	/**
	 * this method reset the number of armies for all countries of the current
	 * player to zero and get them ready for reinforcement phase
	 * 
	 * @param prm_playerId
	 *            is the id of the current player
	 */
	public void ResetCountriesOrmiesByPlayerId(int prm_playerId) {
		for (Country c : map.GetCountriesByPlayerId(prm_playerId)) {
			c.SetArmies(0);
		}

	}

	/**
	 * this method ends the reinforcement phase this set the turn-organizer to
	 * reinforcement phase also it causes the turn to change also it recalculate the
	 * armies for the current player
	 * 
	 * @throws Exception
	 *             if there is now card left
	 */
	public void EndFortificationPhase() throws Exception {
		// this is only for build one to show cards flow and it is supposed that a
		// successful attack is done
		if (turnOrganizer.IsAttackSuccessful()) {
			DrawACard(turnOrganizer.GetCurrentPlayerId());
		}
		turnOrganizer.SetCurrentPhase(TurnPhases.Reinforcement);
		GetNextPlayerId();
		ResetCountriesOrmiesByPlayerId(turnOrganizer.GetCurrentPlayerId());
		// tbd
		// CalculateArmies(GetPlayerById(turnOrganizer.GetCurrentPlayerId()));
	}

	public void BuildDeck(int countryNum) {
		int cardsNum = (countryNum / 3 + 1) * 3;
		Card card;
		for (int i = 0; i < cardsNum / 3; i++) {
			deck.add(new Card(ExchangeCards.Artillery));
		}
		for (int i = cardsNum / 3; i < cardsNum * 2 / 3; i++) {
			deck.add(new Card(ExchangeCards.Cavalry));
		}
		for (int i = cardsNum * 2 / 3; i < cardsNum; i++) {
			deck.add(new Card(ExchangeCards.Infantry));
		}
	}

	/**
	 * this method shuffles the card
	 * 
	 */
	public void ShuffleDeck() {
		Collections.shuffle(deck);
	}

	/**
	 * this method returns unassigned cards
	 * 
	 */
	public List<Card> GetUnassignedCards() {
		List<Card> cards = new ArrayList<Card>();
		for (Card c : deck) {
			if (c.playerId == -1)
				cards.add(c);
		}
		return cards;
	}

	/**
	 * this method draws a card from deck to the player with specific id
	 * 
	 * @param prm_playerId
	 * @throws Exception
	 *             if there is no card to draw
	 */
	public int DrawACard(int prm_playerId) throws Exception {
		int result = 0;
		if (GetUnassignedCards().get(0) != null) {
			GetUnassignedCards().get(0).playerId = prm_playerId;
			return result = 1;
		} else
			throw new Exception("DeckHasNoCard");
	}

	/**
	 * this method returns the cards of a given player in his hand
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 */
	public List<Card> GetCardsByPlayerId(int prm_playerId) {
		List<Card> cards = new ArrayList<Card>();
		for (Card c : deck) {
			if (c.playerId == prm_playerId)
				cards.add(c);
		}
		return cards;
	}

	/**
	 * this method checks if the player has three same type card
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 */
	public boolean HasThreeSameCardsByPlayerId(int prm_playerId) {
		int cardCount = 0;
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Artillery)
				cardCount += 1;
		}
		if (cardCount >= 3)
			return true;
		cardCount = 0;
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Cavalry)
				cardCount += 1;
		}
		if (cardCount >= 3)
			return true;
		cardCount = 0;
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Infantry)
				cardCount += 1;
		}
		if (cardCount >= 3)
			return true;
		return false;
	}

	/**
	 * this method checks if the player has three different type card
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 */
	public boolean HasThreeDifferentCardsByPlayerId(int prm_playerId) {
		boolean hasArtillery = false;
		boolean hasCavalry = false;
		boolean hasInfantry = false;
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Artillery)
				hasArtillery = true;
		}
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Cavalry)
				hasCavalry = true;
		}
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Infantry)
				hasInfantry = true;
		}
		if (hasArtillery && hasCavalry && hasInfantry)
			return true;
		else
			return false;
	}

	/**
	 * this method returns a card with specific type from hand cards of a given
	 * player
	 * 
	 * @param prm_playerId
	 *            which is the id of the player
	 * @param prm_cardType
	 *            which is the type of the card
	 * @return is a card
	 */
	public Card GetACardByTypeByPlayerId(int prm_playerId, ExchangeCards prm_cardType) {
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.GetType() == prm_cardType)
				return c;
		}
		return null;
	}

	/**
	 * this method exchange cards for armies for a given player if the given player
	 * has three same cards the method decks those cards and gives the player 5
	 * armies
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return 1 if the exchange is done successfully, otherwise 0
	 */
	public int ExchangeThreeSameCardsByPlayerId(int prm_playerId) {
		int result = 0;
		int cardCount = 0;
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Artillery)
				cardCount += 1;
		}
		if (cardCount >= 3) {
			for (int i = 0; i < 3; i++) {
				GetACardByTypeByPlayerId(prm_playerId, ExchangeCards.Artillery).SetPlayerId(-1);
			}
			GetPlayerById(prm_playerId).AddArmiesFromCards(5);
			return 1;
		}
		cardCount = 0;
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Cavalry)
				cardCount += 1;
		}
		if (cardCount >= 3) {
			for (int i = 0; i < 3; i++) {
				GetACardByTypeByPlayerId(prm_playerId, ExchangeCards.Cavalry).SetPlayerId(-1);
			}
			GetPlayerById(prm_playerId).AddArmiesFromCards(5);
			return 1;
		}
		cardCount = 0;
		for (Card c : GetCardsByPlayerId(prm_playerId)) {
			if (c.type == ExchangeCards.Infantry)
				cardCount += 1;
		}
		if (cardCount >= 3) {
			for (int i = 0; i < 3; i++) {
				GetACardByTypeByPlayerId(prm_playerId, ExchangeCards.Infantry).SetPlayerId(-1);
			}
			GetPlayerById(prm_playerId).AddArmiesFromCards(5);
			return 1;
		}
		return 0;
	}

	/**
	 * this method exchange cards for armies for a given player if the given player
	 * has three different cards the method decks those cards and gives the player 5
	 * armies
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return 1 if the exchange is done successfully, otherwise 0
	 * @throws Exception
	 *             if the player has no three different cards
	 */
	public int ExchangeThreeDifferentCardsByPlayerId(int prm_playerId) throws Exception {
		try {
			GetACardByTypeByPlayerId(prm_playerId, ExchangeCards.Artillery).SetPlayerId(-1);
			GetACardByTypeByPlayerId(prm_playerId, ExchangeCards.Cavalry).SetPlayerId(-1);
			GetACardByTypeByPlayerId(prm_playerId, ExchangeCards.Infantry).SetPlayerId(-1);
			GetPlayerById(prm_playerId).AddArmiesFromCards(5);
			return 1;
		} catch (Exception ex) {
			throw new Exception("HasNoThreeDifferentCards");
		}
	}

	/**
	 * this method exchange cards for armies for a given player if the given player
	 * has three same cards or three different cards the method decks those cards
	 * and gives the player 5 armies
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @throws Exception
	 *             if there is no three different or same cards
	 */
	public int ExchangeCards(int prm_playerId) throws Exception {
		// System.out.println("Inside Cards Exchange");
		if (turnOrganizer.GetCurrentPhase() == TurnPhases.Reinforcement) {
			if (HasThreeSameCardsByPlayerId(prm_playerId)) {
				ExchangeThreeSameCardsByPlayerId(prm_playerId);
				return 1;
			}
			if (HasThreeDifferentCardsByPlayerId(prm_playerId)) {
				ExchangeThreeDifferentCardsByPlayerId(prm_playerId);
				return 1;
			}
		} else
			throw new Exception("CurrentPhaseNotReinforcement");
		return 0;

	}
}
