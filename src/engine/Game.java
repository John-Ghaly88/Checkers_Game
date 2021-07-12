package engine;

import java.util.ArrayList;
import entity.pieces.*;
import entity.player.*;
import exceptions.*;
import view.*;

public class Game implements PlayerListener {
	private Player currentPlayer;
	private Player opponentPlayer;
	private Board board;
	private GameListener listener;
	private final int DIMENSION = 8;

	public Game(GameListener listener) {
		this.listener = listener;

		this.currentPlayer = new Player(Color.BLUE, this);
		this.opponentPlayer = new Player(Color.RED, this);

		ArrayList<Piece> p = new ArrayList<Piece>();

		Piece pb1 = new Pawn(1, 1, Color.BLUE, this.currentPlayer);
		p.add(pb1);
		Piece pb2 = new Pawn(1, 3, Color.BLUE, this.currentPlayer);
		p.add(pb2);
		Piece pb3 = new Pawn(1, 5, Color.BLUE, this.currentPlayer);
		p.add(pb3);
		Piece pb4 = new Pawn(1, 7, Color.BLUE, this.currentPlayer);
		p.add(pb4);
		Piece pb5 = new Pawn(2, 2, Color.BLUE, this.currentPlayer);
		p.add(pb5);
		Piece pb6 = new Pawn(2, 4, Color.BLUE, this.currentPlayer);
		p.add(pb6);
		Piece pb7 = new Pawn(2, 6, Color.BLUE, this.currentPlayer);
		p.add(pb7);
		Piece pb8 = new Pawn(2, 8, Color.BLUE, this.currentPlayer);
		p.add(pb8);
		Piece pb9 = new Pawn(3, 1, Color.BLUE, this.currentPlayer);
		p.add(pb9);
		Piece pb10 = new Pawn(3, 3, Color.BLUE, this.currentPlayer);
		p.add(pb10);
		Piece pb11 = new Pawn(3, 5, Color.BLUE, this.currentPlayer);
		p.add(pb11);
		Piece pb12 = new Pawn(3, 7, Color.BLUE, this.currentPlayer);
		p.add(pb12);

		Piece pr1 = new Pawn(8, 8, Color.RED, this.opponentPlayer);
		p.add(pr1);
		Piece pr2 = new Pawn(8, 6, Color.RED, this.opponentPlayer);
		p.add(pr2);
		Piece pr3 = new Pawn(8, 4, Color.RED, this.opponentPlayer);
		p.add(pr3);
		Piece pr4 = new Pawn(8, 2, Color.RED, this.opponentPlayer);
		p.add(pr4);
		Piece pr5 = new Pawn(7, 7, Color.RED, this.opponentPlayer);
		p.add(pr5);
		Piece pr6 = new Pawn(7, 5, Color.RED, this.opponentPlayer);
		p.add(pr6);
		Piece pr7 = new Pawn(7, 3, Color.RED, this.opponentPlayer);
		p.add(pr7);
		Piece pr8 = new Pawn(7, 1, Color.RED, this.opponentPlayer);
		p.add(pr8);
		Piece pr9 = new Pawn(6, 8, Color.RED, this.opponentPlayer);
		p.add(pr9);
		Piece pr10 = new Pawn(6, 6, Color.RED, this.opponentPlayer);
		p.add(pr10);
		Piece pr11 = new Pawn(6, 4, Color.RED, this.opponentPlayer);
		p.add(pr11);
		Piece pr12 = new Pawn(6, 2, Color.RED, this.opponentPlayer);
		p.add(pr12);

		this.board = new Board(p, 8);
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Player getOpponentPlayer() {
		return this.opponentPlayer;
	}

	public int getDimension() {
		return this.DIMENSION;
	}

	public ArrayList<Piece> getPieces() {
		return this.board.getPieces();
	}

	public void Play(Piece piece, Location destination)
			throws NotYourTurnException, InvalidMoveException, CaptureBybassException {
		boolean f = false;

		if (piece.getColor() != currentPlayer.getColor())
			throw new NotYourTurnException("It's not your turn, wait for your opponent");

		if ((board.captureCapable(this.currentPlayer.getColor()).size() > 0)
				&& (!(board.captureCapable(this.currentPlayer.getColor()).contains(piece))))
			throw new CaptureBybassException("You have the availability to capture, find it out");

		if (!(board.possiblePlays(piece).contains(destination)))
			throw new InvalidMoveException("This is an invalid move, try again");

		if ((Math.abs(destination.getRow() - piece.getLocation().getRow()) == 2)
				&& (Math.abs(destination.getCol() - piece.getLocation().getCol()) == 2))
			f = true;

		this.board.movePiece(piece, destination);

		if ((this.board.captureCapable(this.getCurrentPlayer().getColor()).size() == 0) || (!f))
			endTurn();
	}

	public void endTurn() {
		Player temp = this.currentPlayer;
		this.currentPlayer = this.opponentPlayer;
		this.opponentPlayer = temp;

		if (!(board.canMakeMove(this.currentPlayer.getColor())))
			onLose(currentPlayer);
	}

	public void onLose(Player player) {
		if (player.getColor() == Color.BLUE)
			this.listener.GameOver(Color.RED);
		else if (player.getColor() == Color.RED)
			this.listener.GameOver(Color.BLUE);
	}
}
