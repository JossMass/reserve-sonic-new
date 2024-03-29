package pe.edu.upc.reservesonic.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
@SequenceGenerator(name = "sequenceRoom", sequenceName = "Rooms_room_id_seq", initialValue = 1, allocationSize = 1)
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceRoom")
	@Column(name = "room_id", columnDefinition = "DECIMAL(4)")
	private Integer id;
	
	@Column(name = "room_number", columnDefinition = "NUMERIC(4)", nullable = false)
	private Integer roomNumber; // numero de la sala

	@Column(name = "capacity", columnDefinition = "NUMERIC(4)", nullable = false)
	private Integer capacity;
	

	@Column(name = "speecs", length = 120)
	private String speecs; // caracteristicas
	
	// OneToMany relationships
	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Instrument> instruments;
		
	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Review> reviews;

	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Reservation> reservations;

	// ManyToOne relationships
	@ManyToOne
	@JoinColumn(name = "studio_id")
	private Studio studio;

	// Constructor, getters & setters
	public Room() {
		super();
		instruments = new ArrayList<Instrument>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}

	public Studio getStudio() {
		return studio;
	}

	public void setStudio(Studio studio) {
		this.studio = studio;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getSpeecs() {
		return speecs;
	}

	public void setSpeecs(String speecs) {
		this.speecs = speecs;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}