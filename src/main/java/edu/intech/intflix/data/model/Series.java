package edu.intech.intflix.data.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Series")
public class Series {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	private boolean seen = false;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
			@JoinColumn(name = "fk_series", referencedColumnName = "id")
	}) private Map<Integer, Season> seasons = new HashMap<Integer, Season>();

	/**
	 * Series constructor
	 */
	public Series() { }

	/**
	 * @return the series id
	 */
	public Long getId() {
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
	 * This method returns the visualization state of the Series.
	 *
	 * @return <code>true</code> if all Seasons of the Series have been fully
	 *         watched, <code>false</code> otherwise.
	 */
	public boolean isSeen() {
		if (this.seasons.size() > 0) {
			this.seen = true;
			for (Season s : this.seasons.values()) {
				if (!s.isSeen()) {
					this.seen = false;
					return this.seen;
				}
			}
		}
		return this.seen;
	}

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
		return Math.toIntExact(getId());
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Series)) {
			return false;
		}
		return ((Series) obj).getId() == getId();
	}
}
