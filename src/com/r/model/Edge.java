package com.r.model;

/**
 * This class is used to represent connection between two countries.
 * it maintains id of two countries which are neighbor.
 * it has different methods to change and set
 * the state of the object.
 * 
 * @author Raghav verma
 * @version 1.0.0.0
 */
public class Edge implements Comparable  {
	private static int counter = 0;
	private int id;
	private int countryId1;
	private int countryId2;

	/**
	 * This the constructor of the edge which set the object id and id of the two
	 * neighbour countries.
	 * 
	 * @param prm_countryId1,
	 *            which is integer, is the id of the first country
	 * @param prm_countryId2,
	 *            which is integer, is the id of the second country
	 */
	public Edge(int prm_countryId1, int prm_countryId2) {
		counter++;
		this.id = this.counter;
		this.countryId1 = prm_countryId1;
		this.countryId2 = prm_countryId2;
	}

	/**
	 * This method returns the id of the first country
	 * 
	 * @return countryId1, which is a integer, and it will provide the id of the first country
	 */
	public int GetCountryId1() {
		return this.countryId1;
	}

	/**
	 * This method returns the id of the second country
	 * 
	 * @return countryId2, which is integer, is the id of the second country
	 */
	public int GetCountryId2() {
		return this.countryId2;
	}

	/**
	 * This method returns the id of the object @return, which is integer, is the id
	 * of the object
	 */
	public int GetId() {
		return this.id;
	}

	@Override
	/**
	 * This method implements the compareTo method from comparable interface
	 * 
	 * @param o,
	 *            which is an edge, is an edge which will be compared to the object
	 *            in terms of the id of the neighbor countries
	 * @return an integer which could be 0 or -1, 0 means that two edges are equal
	 *         and -1 means the two edges are not equal
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Edge edge = (Edge) o;
		if ((this.GetCountryId1() == edge.GetCountryId1() && this.GetCountryId2() == edge.GetCountryId2())
				|| (this.GetCountryId1() == edge.GetCountryId2() && this.GetCountryId2() == edge.GetCountryId1())) {
			return 0;
		} else
			return -1;
	}

	// this is a duplicate method should be removed
	/**
	 * This method verifies if a given country exists in the current edge
	 * 
	 * @param prm_countryId
	 *            , which is integer, is the id of the given country
	 * @return , which is boolean, is true if the country exists in the edge,
	 *         otherwise returns false
	 */
	public boolean DoesExistCountry(int prm_countryId) {
		boolean result = false;
		if (this.GetCountryId1() == prm_countryId || this.GetCountryId2() == prm_countryId) {
			return true;
		}
		return result;
	}

	/**
	 * this method returns the neighbor of a given country in the current edge
	 * 
	 * @param prm_countryId
	 *            , which is integer, is the id of a given country
	 * @return the neighbor's id in the current edge
	 */
	public int GetNeighborId(int prm_countryId) {
		if (this.countryId1 == prm_countryId) {
			return this.countryId2;
		} else if (this.countryId2 == prm_countryId) {
			return this.countryId1;
		} else {
			return -1;
		}

	}

	/**
	 * the method verifies if the current edge contains a given country
	 * 
	 * @param prm_countryId
	 *            ,which is integer, is the id of the country under query
	 * @return a boolean type which is true if the country under the query is
	 *         contained in the current edge
	 */
	public boolean DoesContainCountry(int prm_countryId) {
		if (this.countryId1 == prm_countryId || this.countryId2 == prm_countryId) {
			return true;
		} else {
			return false;
		}

	}

}
