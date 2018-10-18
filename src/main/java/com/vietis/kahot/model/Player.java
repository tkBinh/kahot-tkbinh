package com.vietis.kahot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "score")
	private int score;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "game_id")
	private Game game;

	public Player(String username, int score) {
		super();
		this.username = username;
		this.score = score;
	}

	public Player(String username, int score, Game game) {
		super();
		this.username = username;
		this.score = score;
		this.game = game;
	}

	public Player() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
