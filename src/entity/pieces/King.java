package entity.pieces;

import java.util.ArrayList;
import entity.player.*;

public class King extends Piece {

	public King(Location location, Color color, PieceListener listener) {
		super(location, color, listener);

	}

	public King(int row, int col, Color color, PieceListener listener) {
		super(row, col, color, listener);
	}

	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> pm = new ArrayList();

		if ((this.getLocation().getRow() <= 7) && (this.getLocation().getCol() <= 7))
			pm.add(new Location(this.getLocation().getRow() + 1, this.getLocation().getCol() + 1));

		if ((this.getLocation().getRow() <= 7) && (this.getLocation().getCol() >= 2))
			pm.add(new Location(this.getLocation().getRow() + 1, this.getLocation().getCol() - 1));

		if ((this.getLocation().getRow() <= 6) && (this.getLocation().getCol() <= 6))
			pm.add(new Location(this.getLocation().getRow() + 2, this.getLocation().getCol() + 2));

		if ((this.getLocation().getRow() <= 6) && (this.getLocation().getCol() >= 3))
			pm.add(new Location(this.getLocation().getRow() + 2, this.getLocation().getCol() - 2));

		if ((this.getLocation().getRow() >= 2) && (this.getLocation().getCol() <= 7))
			pm.add(new Location(this.getLocation().getRow() - 1, this.getLocation().getCol() + 1));

		if ((this.getLocation().getRow() >= 2) && (this.getLocation().getCol() >= 2))
			pm.add(new Location(this.getLocation().getRow() - 1, this.getLocation().getCol() - 1));

		if ((this.getLocation().getRow() >= 3) && (this.getLocation().getCol() <= 6))
			pm.add(new Location(this.getLocation().getRow() - 2, this.getLocation().getCol() + 2));

		if ((this.getLocation().getRow() >= 3) && (this.getLocation().getCol() >= 3))
			pm.add(new Location(this.getLocation().getRow() - 2, this.getLocation().getCol() - 2));

		return pm;
	}

	public boolean equals(Object obj) {
		Piece x = (Piece) obj;
		return (this.getLocation().equals(x.getLocation()));
	}
}
