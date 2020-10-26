package edu.intech.intflix.data.model;

import javax.persistence.*;

@Entity
@Table(name = "Episode")
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "number", nullable = false)
	private int number;

	private boolean seen = false;

	/**
	 * Episode constructor
	 * @param title Title of the episode
	 * @param number Number of the episode
	 */
	public Episode(String title, int number) {
		this.title = title;
		this.number = number;
	}

	public Episode() { }

	/**
	 * @return the id of the episode
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the title of the episode
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set title of the episode
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * @param number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * This method returns the visualization state of the Episode.
	 */
	public boolean isSeen() {
		return this.seen;
	}

	/**
	 * Set if the episode is seen or not
	 * @param seen
	 */
	public void setSeen(final boolean seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return getTitle();
	}

	@Override
	public int hashCode() {
		return Math.toIntExact(getId());
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Episode)) {
			return false;
		}
		return ((Episode) obj).getId().equals(getId());
	}

}
