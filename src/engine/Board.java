package engine;

import java.util.ArrayList;
import entity.pieces.*;

public class Board {
	private int dimension;
	private ArrayList<Piece> pieces;

	public Board(ArrayList<Piece> pieces, int dimension) {
		this.pieces = pieces;
		this.dimension = dimension;
	}

	public ArrayList<Piece> getPieces() {
		return this.pieces;
	}

	public Piece getPieceAt(Location location) {
		for (int i = 0; i < this.pieces.size(); i++) {
			if (location.equals(this.pieces.get(i).getLocation()))
				return this.pieces.get(i);
		}
		return null;
	}

	public ArrayList<Location> possiblePlays(Piece piece) {
		ArrayList<Location> pp = new ArrayList();

		for (Location L : piece.possibleMoves()) {
			if (getPieceAt(L) != null)
				continue;

			if ((Math.abs(L.getRow() - piece.getLocation().getRow()) == 1)
					&& (Math.abs(L.getCol() - piece.getLocation().getCol()) == 1))
				pp.add(L);

			if ((Math.abs(L.getRow() - piece.getLocation().getRow()) == 2)
					&& (Math.abs(L.getCol() - piece.getLocation().getCol()) == 2)) {
				Piece middle = this.getPieceAt(new Location((L.getRow() + piece.getLocation().getRow()) / 2,
						(L.getCol() + piece.getLocation().getCol()) / 2));
				if ((middle != null) && !(middle.getColor().equals(piece.getColor())))
					pp.add(L);
			}
		}
		return pp;
	}

	public boolean movePiece(Piece piece, Location destination) {

		if ((Math.abs(destination.getRow() - piece.getLocation().getRow()) == 2)
				&& (Math.abs(destination.getCol() - piece.getLocation().getCol()) == 2)) {
			Piece middle = this.getPieceAt(new Location((destination.getRow() + piece.getLocation().getRow()) / 2,
					(destination.getCol() + piece.getLocation().getCol()) / 2));
			middle.captured();
			this.getPieces().remove(middle);
		}

		if (piece.getColor().equals(Color.BLUE) && destination.getRow() == 8) {
			piece.setLocation(destination);
			this.pieces.remove(piece);
			this.pieces.add(piece.upgrade());
		}

		else if (piece.getColor().equals(Color.RED) && destination.getRow() == 1) {
			piece.setLocation(destination);
			this.pieces.remove(piece);
			this.pieces.add(piece.upgrade());
		}

		else
			piece.setLocation(destination);

		return true;
	}

	public ArrayList<Piece> captureCapable(Color color) {
		ArrayList<Piece> cc = new ArrayList();

		for (Piece piece : this.getPieces()) {
			if (piece.getColor().equals(color)) {
				for (Location destination : possiblePlays(piece)) {
					if ((Math.abs(destination.getRow() - piece.getLocation().getRow()) == 2)
							&& (Math.abs(destination.getCol() - piece.getLocation().getCol()) == 2)) {
						Piece middle = this
								.getPieceAt(new Location((destination.getRow() + piece.getLocation().getRow()) / 2,
										(destination.getCol() + piece.getLocation().getCol()) / 2));
						if ((middle != null) && !(middle.getColor().equals(piece.getColor())))
							cc.add(piece);
					}
				}
			}
		}
		return cc;
	}

	public boolean canMakeMove(Color color) {
		for (int i = 0; i < this.pieces.size(); i++) {
			if ((this.pieces.get(i).getColor() == color) && (!(possiblePlays(this.pieces.get(i)).isEmpty())))
				return true;
		}
		return false;
	}
}
