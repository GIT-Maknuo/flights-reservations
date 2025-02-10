package com.reservations.flights.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String origin;
    private String destiny;
    
    @Column(name ="seats_quantity")
    private int seatsQuantity;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Seat> seats;

	public Flight() {
		super();
	}

	public Flight(Long id, String origin, String destiny, int seatsQuantity, List<Seat> seats) {
		super();
		this.id = id;
		this.origin = origin;
		this.destiny = destiny;
		this.seatsQuantity = seatsQuantity;
		this.seats = seats;
	}

	public Flight(String origin, String destiny, int seatsQuantity) {
        this.origin = origin;
        this.destiny = destiny;	
        this.seatsQuantity = seatsQuantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public int getSeatsQuantity() {
		return seatsQuantity;
	}

	public void setSeatsQuantity(int seatsQuantity) {
		this.seatsQuantity = seatsQuantity;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	
}
