package com.r.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

import com.r.utility.StaticVariables;

/**
 * This class is a high level class which contains continents and countries. it manages
 * most of the tasks regarding these classes, it has several properties such as
 * name, lands which are the collections of both countries and continents as well
 * as connectivities which in this application is known as edge.
 * 
 * @author Raghav verma
 *
 */

public class Map {
	private String name;
	private String author;
	private String image;
	public List<Land> lands;
	public List<Edge> edges;

	/**
	 * this is the constructor of the class which takes the name of the map
	 * 
	 * @param prm_name
	 *            which is string and is the name of the map
	 */
	public Map(String prm_name) {
		this.name = prm_name;
		lands = new ArrayList<Land>();
		edges = new ArrayList<Edge>();
	}

	/**
	 * this method set author of the object
	 * 
	 * @param prm_author,
	 *            which its type is string, will be the name of the author of the
	 *            map
	 */
	public void SetAuthor(String prm_author) {
		this.author = prm_author;
	}

	/**
	 * this method set an image to the object
	 * 
	 * @param prm_image,
	 *            which its type is string, will be the name of the image related to
	 *            the object
	 */
	public void SetImage(String prm_image) {
		this.image = prm_image;
	}

	/**
	 * this method returns the author of the map
	 * 
	 * @return, which its type is string, is the author of the map
	 */
	public String GetAuthor() {
		return this.author;
	}

	/**
	 * this method returns the image name of the object
	 * 
	 * @return, which its type is string, is the name of the image
	 */
	public String GetImage() {
		return this.image;
	}

	/**
	 * this method return a country or continent by its name
	 * 
	 * @param prm_name,
	 *            which is the name of the country or continent
	 * @return a country or a continent
	 */
	public Land GetLandByName(String prm_name) {
		String tempName = "";
		for (Land land : lands) {
			if (prm_name.equals(land.GetName())) {
				return land;
			}
		}
		return null;

	}

	/**
	 * this method return a string
	 * 
	 * @param id,
	 *            which is the name of the country or continent
	 * @return a country or a continent
	 */
	public String[] getCountryListinStringForCombobox(int id) {
		List<Country> countryList = StaticVariables.gb.map.GetCountriesByContinentId(id);
		String[] countryString = new String[countryList.size()];

		for (int j = 0; j < countryList.size(); j++) {
			countryString[j] = countryList.get(j).GetName();
		}
		return countryString;
	}

	/**
	 * this method remove a continent
	 * 
	 * @param prm_continent
	 *            is the continent to be removed
	 * @return a string which is successful if it is removed and if it does not
	 *         exist, notExist will be returned
	 */
	public String RemoveContinent(Continent prm_continent) {
		if (DoesExistContinent(prm_continent.GetId())) {
			for (Country c : this.GetCountries()) {
				if (c.GetContinentId() == prm_continent.GetContinentId()) {
					this.RemoveCountry(c);
				}
			}
			this.lands.remove(prm_continent);
			return "successful";
		} else {
			return "notExist";
		}

	}

	/**
	 * this method verifies if a given country exists
	 * 
	 * @param prm_name
	 *            is the name of the country under question
	 * @return a boolean which is true if exists otherwise false
	 */
	public boolean DoesExistCountry(String prm_name) {
		boolean result = false;
		for (Country c : this.GetCountries()) {
			if (c.GetName().equals(prm_name)) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method verifies if a given continent exists
	 * 
	 * @param prm_name
	 *            is the name of the continent under question
	 * @return a boolean which is true if exists otherwise false
	 */
	public boolean DoesExistContinent(int prm_continentId) {
		boolean result = false;
		for (Continent c : this.GetContinents()) {
			if (c.GetId() == prm_continentId) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method adds an edge, before adding it checks if already exists
	 * 
	 * @param prm_edge
	 *            which is the edge to be added
	 * @return which is 1 if the edge was added otherwise -1
	 */
	public int AddEdge(Edge prm_edge) {
		int result = -1;
		if (!DoesExistEdge(prm_edge)) {
			this.edges.add(prm_edge);
			result = 1;
		}
		return result;
	}

	/**
	 * this method adds a country, before adding verifies if already exists
	 * 
	 * @param prm_name
	 *            is the name of the country
	 * @param prm_continentId
	 *            is the id of the continent to which the country belongs
	 * @param prm_x
	 *            is x coordinate of the country
	 * @param prm_y
	 *            is y coordinate of the country
	 * @return
	 */
	public String AddCountry(String prm_name, int prm_continentId, int prm_x, int prm_y) {
		if (!DoesExistCountry(prm_name)) {
			this.lands.add(Factory.GetLand("Country", prm_name, prm_continentId, prm_x, prm_y, -1));
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method verifies if a given edge exist
	 * 
	 * @param prm_edge
	 *            is the edge under question
	 * @return is boolean which is true if the edge exists otherwise false
	 */
	public boolean DoesExistEdge(Edge prm_edge) {
		boolean result = false;
		for (Edge e : this.edges) {
			if (e.compareTo(prm_edge) == 0) {
				return true;

			}

		}
		return result;
	}

	/**
	 * this method verifies if the edge exists by the id of the countries
	 * 
	 * @param prm_countryId1
	 *            which is the id of the first country of the edge under question
	 * @param prm_countryId2
	 *            which is the id of the second country
	 * @return true if the edge exists otherwise false
	 */
	public boolean DoesExistEdge(int prm_countryId1, int prm_countryId2) {
		boolean result = false;
		for (Edge e : this.edges) {
			if ((e.GetCountryId1() == prm_countryId1 && e.GetCountryId2() == prm_countryId2)
					|| (e.GetCountryId1() == prm_countryId2 && e.GetCountryId2() == prm_countryId1)) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this methos adds an edge, it verifies the existence before adding
	 * 
	 * @param prm_countryId1
	 *            is the id of the first country in the edge
	 * @param prm_countryId2
	 *            is the id of the second country in the edge
	 * @return is string and is successful if the edge was added otherwise is
	 *         duplicate
	 */
	public String AddEdge(int prm_countryId1, int prm_countryId2) {
		if (!DoesExistEdge(prm_countryId1, prm_countryId2)) {
			this.edges.add(new Edge(prm_countryId1, prm_countryId2));
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method verifies of a given continent exists
	 * 
	 * @param prm_name
	 *            is the name of the continent under question
	 * @return true if the continent exists otherwise is false
	 */
	public boolean DoesExistContinent(String prm_name) {
		boolean result = false;
		for (Continent c : this.GetContinents()) {
			if (c.GetName().equals(prm_name)) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method returns a country which does not belong to a player randomly
	 * 
	 * @return is an unassigned country randomly
	 */
	public Country GetNotAssignedCountryRandom() {
		List<Country> countries = GetCountriesNotAssigned();
		Country randCountry = countries.get(new Random().nextInt(countries.size()));
		return randCountry;
	}

	/**
	 * this method adds a continent before adding it verifies if the continent does
	 * exist before
	 * 
	 * @param prm_name
	 *            is the name of the continent
	 * @param prm_control
	 *            ,which its type is integer, is the control of the continent
	 * @return
	 */
	public String AddContinent(String prm_name, int prm_control) {
		if (!DoesExistCountry(prm_name)) {
			this.lands.add(Factory.GetLand("Continent", prm_name, -1, -1, -1, prm_control));
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method returns a list of countries which are not assigned to a player
	 * 
	 * @return a list of countries
	 */
	public List<Country> GetCountriesNotAssigned() {
		List<Country> countriesNotAssigned = new ArrayList<Country>();
		for (Land c : lands) {
			if (c instanceof Country) {
				if (((Country) c).GetPlayerId() == -1) {
					countriesNotAssigned.add((Country) c);
				}
			}
		}
		return countriesNotAssigned;
	}

	/**
	 * this method returns a list of countries belong to a given player
	 * 
	 * @param prm_playerId
	 *            is the id of the given player
	 * @return a list of country belong to the given player
	 */
	public List<Country> GetCountriesByPlayerId(int prm_playerId) {
		List<Country> countries = new ArrayList<Country>();
		for (Country c : GetCountries()) {
			if (c.GetPlayerId() == prm_playerId) {
				countries.add(c);
			}
		}
		return countries;
	}

	/**
	 * this method returns a country by its id
	 * 
	 * @param prm_countryId
	 *            is the id of the country
	 * @return is the country which its id is given
	 */
	public Country GetCountryById(int prm_countryId) {
		Country country = null;
		for (Country c : GetCountries()) {
			if (c.GetId() == prm_countryId)
				return c;
		}
		return country;
	}

	/**
	 * this method returns a continent id by its name
	 * 
	 * @param new_name
	 *            is the name of the continent to be returned
	 * @return the id of the continent
	 */
	public int GetContinentIdByName(String new_name) {
		int id = -1;
		for (Land l : this.lands) {
			if (l instanceof Continent && l.GetName().equals(new_name)) {
				return l.GetId();
			}
		}
		return id;
	}

	/**
	 * this method returns the list of all neighbor countries of a given country
	 * 
	 * @param prm_countryId
	 *            is the id of the given country
	 * @return is a list of the neighbor countries
	 */
	public List<Country> GetNeighbors(int prm_countryId) {
		List<Country> neighbors = new ArrayList<Country>();
		for (Edge e : edges) {
			if (e.DoesContainCountry(prm_countryId)) {
				neighbors.add(GetCountryById(e.GetNeighborId(prm_countryId)));
			}
		}
		return neighbors;

	}

	/**
	 * this method returns a country id by its name
	 * 
	 * @param prm_name
	 *            is the name of the country to be return
	 * @return is the id of the country
	 */
	public int GetCountryIdByName(String prm_name) {
		int id = -1;
		for (Land l : this.lands) {
			if (l instanceof Country && l.GetName().equals(prm_name)) {
				return l.GetId();
			}
		}
		return id;
	}

	/**
	 * this method returns a country by its name
	 * 
	 * @param prm_name
	 *            is the name of the country to be returned
	 * @return is a country which its name is given
	 */
	public Country GetCountryByName(String prm_name) {
		for (Land l : this.lands) {
			if (l instanceof Country && l.GetName().equals(prm_name)) {
				return (Country) l;
			}
		}
		return null;
	}

	/**
	 * this method verifies if a country exists
	 * 
	 * @param prm_countryId
	 *            is the id of the country under question
	 * @return true if the country exists otherwise false
	 */
	public boolean DoesExistCountry(int prm_countryId) {
		boolean result = false;
		for (Country c : this.GetCountries()) {
			if (c.GetId() == prm_countryId) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method verifies if a continent exists
	 * 
	 * @param prm_continent
	 *            is the country under question
	 * @return true if the country exists otherwise false
	 */
	public boolean DoesExistContinent(Continent prm_continent) {
		boolean result = false;
		for (Continent c : this.GetContinents()) {
			if (c instanceof Continent && c.GetName().equals(prm_continent.GetName())) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method returns a continent by its name
	 * 
	 * @param prm_name
	 *            is the name of the continent under question
	 * @return the continent which its name is given
	 */
	public Continent GetContinentByName(String prm_name) {

		for (Continent c : this.GetContinents()) {
			if (c instanceof Continent && c.GetName().equals(prm_name)) {
				return (Continent) c;
			}
		}
		return null;
	}

	/**
	 * this method removes a given country
	 * 
	 * @param prm_country
	 *            is the country to be removed
	 * @return is successful if the country was removed otherwise returns duplicate
	 */
	public String RemoveCountry(Country prm_country) {
		if (DoesExistCountry(prm_country.GetId())) {
			this.RemoveEdge(prm_country.GetId());
			this.lands.remove(prm_country);
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method removes all edges related to a given country
	 * 
	 * @param prm_countryId
	 *            is the id of the given country
	 * @return successful if they were removed
	 */
	public String RemoveEdge(int prm_countryId) {
		Iterator it = this.edges.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (((Edge) o).DoesContainCountry(prm_countryId)) {
				it.remove();
			}
		}
		return "successful";

	}

	/**
	 * this method returns the name of a continent which its id is given
	 * 
	 * @param prm_id
	 *            is the id of the continent
	 * @return the name of the continent
	 */
	public String GetContinentNameById(int prm_id) {
		String name = "";
		for (Land l : this.lands) {
			if (l instanceof Continent && l.GetId() == prm_id) {
				return l.GetName();
			}
		}
		return name;
	}

	/**
	 * this method returns the name of a country which its id is given
	 * 
	 * @param prm_id
	 *            is the id of the country
	 * @return the name of the country
	 */
	public String GetCountryNameById(int prm_id) {
		String name = "";
		for (Land l : this.lands) {
			if (l instanceof Country && l.GetId() == prm_id) {
				return l.GetName();
			}
		}
		return name;
	}

	/**
	 * this method returns all continents which exist
	 * 
	 * @return a list of all continents
	 */
	public List<Continent> GetContinents() {
		List<Continent> continents = new ArrayList<Continent>();
		for (Land l : this.lands) {
			if (l instanceof Continent) {
				continents.add((Continent) l);
			}
		}
		return continents;
	}

	/**
	 * this method returns all countries which exist
	 * 
	 * @return a list of all countries
	 */
	public List<Country> GetCountries() {
		List<Country> continents = new ArrayList<Country>();
		for (Land l : this.lands) {
			if (l instanceof Country) {
				continents.add((Country) l);
			}
		}
		return continents;
	}

	/**
	 * this method concatenates the name of all neighbor countries names
	 * 
	 * @param prm_country
	 * @return
	 */
	public List<String> GetNeighhboursName(Country prm_country) {
		List<String> neighborsName = new ArrayList<String>();
		for (Edge e : this.edges) {
			if (e.DoesExistCountry(prm_country.GetId())) {
				neighborsName.add(this.GetCountryNameById(e.GetNeighborId(prm_country.GetId())));
			}
		}
		return neighborsName;

	}

	/**
	 * this method convert all countries to line of the map file
	 * 
	 * @return a string which contains all countries in map format
	 */
	public List<String> CountriesToLine() {
		List<String> lines = new ArrayList<String>();
		String line;
		for (Country c : this.GetCountries()) {
			line = new String();
			line = c.GetName() + "," + c.GetX() + "," + c.GetY() + "," + this.GetContinentNameById(c.GetContinentId());
			for (String neighborName : this.GetNeighhboursName(c)) {
				line += "," + neighborName;
			}
			lines.add(line);
		}
		return lines;
	}

	/**
	 * this method converts the whole map into a lines of string in map format
	 * 
	 * @return
	 */
	public List<String> MapToLines() {
		List<String> lines = new ArrayList<String>();
		lines.add("[Map]");
		lines.add("author=" + this.GetAuthor());
		lines.add("image=" + this.GetImage());
		lines.add("wrap=yes");
		lines.add("scroll=yes");
		lines.add("warn=yes");
		lines.add("");
		lines.add("[Continents]");
		for (Continent c : this.GetContinents()) {
			lines.add(c.GetName() + "=" + c.GetContinentId());
		}
		lines.add("");
		lines.add("[Territories]");
		for (String l : CountriesToLine()) {
			lines.add(l);
		}
		return lines;
	}

	/**
	 * returns all countries of a given continent
	 * 
	 * @param prm_continentId
	 *            is the id of the continent
	 * @return lis of all countries of the given continent
	 */
	public List<Country> GetCountriesByContinentId(int prm_continentId) {
		List<Country> countries = new ArrayList<Country>();
		for (Country c : GetCountries()) {
			if (c.GetContinentId() == prm_continentId)
				countries.add(c);
		}
		return countries;
	}

	/**
	 * this method returns the list of the name of countries of a given continent
	 */
	public String[] GetCountriesByContinentIdInStrings(int prm_continentId) {
		List<String> countries = new ArrayList<String>();
		for (Country c : GetCountries()) {
			if (c.GetContinentId() == prm_continentId)
				countries.add(c.GetName());
		}

		String[] temp = new String[countries.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = countries.get(i);
			// System.out.println("inside getcountrybycontinentid"+temp[i]);
		}

		return temp;
	}

	/**
	 * this method return the id of the player to which a given country belong
	 * 
	 * @param prm_countryId,
	 *            which is the id of the player
	 * @return prm_playerId, which is the id of the player to which the country
	 *         belongs
	 */
	public int GetPlayerIdByCountryId(int prm_countryId) {
		return GetCountryById(prm_countryId).GetPlayerId();
	}

	public String[] getCountryListStringForCombobox(int id) {
		List<Country> countryList = StaticVariables.gb.map.GetCountriesByContinentId(id);
		String[] countryString = new String[countryList.size()];

		for (int j = 0; j < countryList.size(); j++) {
			countryString[j] = countryList.get(j).GetName();
		}
		return countryString;
	}

	/**
	 * this method returns the adjacent country of a given country which belong to
	 * the same player
	 * 
	 * @param prm_countryId,
	 *            which is the id of the given country
	 * @return is a list of countries which are adjacent of the given country
	 */
	public List<Country> GetNeighborsByCountryIdSamePlayer(int prm_countryId) {
		List<Country> neighbors = new ArrayList<Country>();
		for (Edge e : edges) {
			if (e.DoesContainCountry(prm_countryId)) {
				if (GetPlayerIdByCountryId(prm_countryId) == GetPlayerIdByCountryId(e.GetNeighborId(prm_countryId)))
					neighbors.add(GetCountryById(e.GetNeighborId(prm_countryId)));
			}
		}
		return neighbors;
	}
}
