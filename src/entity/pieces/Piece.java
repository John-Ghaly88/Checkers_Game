package entity.pieces;

import java.util.ArrayList;
import entity.player.*;

public abstract class Piece {
	private Location location;
	private Color color;
	private PieceListener listener;

	public Piece(Location location, Color color, PieceListener listener) {
		this.location = location;
		this.color = color;
		this.listener = listener;
	}

	public Piece(int row, int col, Color color, PieceListener listener) {
		this.location = new Location(row, col);
		this.color = color;
		this.listener = listener;
	}

	public Color getColor() {
		return this.color;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location x) {
		this.location = x;
	}

	public void captured() {
		this.listener.onPieceCaptured();
	}

	public King upgrade() {
		return new King(this.location, this.color, this.listener);
	}

	public abstract ArrayList<Location> possibleMoves();

	public boolean equals(Object obj) {
		Piece x = (Piece) obj;
		return (this.location == x.location);
	}

}
