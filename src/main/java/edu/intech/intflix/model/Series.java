package edu.intech.intflix.model;

import javax.persistence.*;

@Entity
@Table(name = "Series")
@NamedQuery(name = "Serie.findAll", query = "SELECT series FROM Series series")
public class Series {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private int id = -1;
	@Column(name = "name")
	private String name;
	//private boolean seen = false;
	//private Map<Integer, Season> season = new HashMap<Integer, Season>();

	/**
	 * Series constructor
	 * @param name Name of the series.
	 */
	public Series(final String name) {
		this.name = name;
	}

	/**
	 * @return the series id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name of the series
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Set the name of the series
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
