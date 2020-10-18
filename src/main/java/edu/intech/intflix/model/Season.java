package edu.intech.intflix.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Season")
@NamedQueries({
		@NamedQuery(name = "Season.findAll", query = "SELECT season FROM Season season"),
		@NamedQuery(name = "Season.findBySeries",
				query = "SELECT season FROM Series series, IN(series.seasons) season WHERE series.id = :id")
})
public class Season {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "number", nullable = false)
	private int number;

	private boolean seen = false;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
			@JoinColumn(name = "fk_season", referencedColumnName = "id")
	}) private Map<Integer, Episode> episodes = new HashMap<Integer, Episode>();

	/**
	 * Season constructor
	 * @param number Number of the season
	 */
	public Season(final int number) {
		this.number = number;
	}

	public Season() { }

	/**
	 * @return the season id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the number of the season
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number set the number of the season
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return episodes of the current season
	 */
	public Map<Integer, Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Map<Integer, Episode> episodes) {
		this.episodes = episodes;
	}

	/**
	 * @return true if the episode have been seen and false if not
	 */
	public boolean isSeen() {
		this.seen = true;
		if (this.episodes.size() > 0) {
			for (Episode episode : this.episodes.values()) {
				if (!episode.isSeen()) {
					this.seen = false;
					return this.seen;
				}
			}
		}
		return this.seen;
	}

	/**
	 * @param seen set if the episode is seen or not
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return "Season " + getNumber();
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Season)) {
			return false;
		}
		return ((Season) obj).getId() == getId();
	}
}
