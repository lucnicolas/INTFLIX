package edu.intech.intflix.model;

import javax.persistence.*;

@Entity
@Table(name = "Season")
@NamedQueries({
		@NamedQuery(name = "Season.findAll", query = "SELECT season FROM Season season"),
		// @NamedQuery(name = "Season.findBySeries",
		//		query = "SELECT season FROM Series series, IN(series.season) season WHERE series.id = :id")
})
public class Season {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private int id = -1;

	@Column(name = "number")
	private int number;

	//private boolean seen = false;
	//private Map<Integer, Episode> episodes = new HashMap<Integer, Episode>();


	/**
	 * Season constructor
	 * @param number
	 */
	public Season(final int number) {
		this.number = number;
	}

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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
