package entity.player;

import engine.*;
import entity.pieces.*;

public class Player implements PieceListener {
	private Color color;
	private int remainingPieces;
	private PlayerListener listener;

	public Player(Color color, PlayerListener listener) {
		this.color = color;
		this.remainingPieces = 12;
		this.listener = listener;
	}

	public Color getColor() {
		return this.color;
	}

	public void onPieceCaptured() {
		this.remainingPieces--;
		if (this.remainingPieces == 0)
			this.listener.onLose(this);
	}
}
