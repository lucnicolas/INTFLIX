package edu.intech.intflix.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Series")
@NamedQuery(name = "Series.findAll", query = "SELECT series FROM Series series")
public class Series {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	private boolean seen = false;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
			@JoinColumn(name = "fk_series", referencedColumnName = "id")
	}) private Map<Integer, Season> seasons = new HashMap<Integer, Season>();

	/**
	 * Series constructor
	 * @param name Name of the series.
	 */
	public Series(final String name) {
		this.name = name;
	}

	public Series() { }

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

	/**
	 *
	 * This method returns the visualization state of the Serie.
	 *
	 * @return <code>true</code> if all Seasons of the Series have been fully
	 *         watched, <code>false</code> otherwise.
	 */
	public boolean isSeen() {
		this.seen = true;
		if (this.seasons.size() > 0) {
			for (Season s : this.seasons.values()) {
				if (!s.isSeen()) {
					this.seen = false;
					return this.seen;
				}
			}
		}
		return this.seen;	}

	/**
	 * @param seen Set true if the series is seen and false if not
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public Map<Integer, Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(Map<Integer, Season> seasons) {
		this.seasons = seasons;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Series)) {
			return false;
		}
		return ((Series) obj).getId() == getId();
	}
}
