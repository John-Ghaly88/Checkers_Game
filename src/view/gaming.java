package view;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import engine.*;
import entity.pieces.*;
import exceptions.*;

public class gaming extends JFrame implements GameListener, MouseListener {

	private Game game;
	private Piece select;
	private final int Square = 80;

	public gaming() {
		this.addMouseListener(this);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getSquare() {
		return Square;
	}

	public void paint(Graphics g) {
		paintBoard(g);
		paintPiece(g);
	}

	public void paintBoard(Graphics g) {

		for (int row = 0; row < this.game.getDimension(); row++) {

			for (int col = 0; col < this.game.getDimension(); col++) {
				if ((row + col) % 2 == 0)
					g.setColor(Color.BLACK);
				else if ((row + col) % 2 == 1)
					g.setColor(Color.WHITE);
				g.fillRect(row * getSquare() + 13, col * getSquare() + 30, getSquare(), getSquare());
			}
		}
	}

	public void paintPiece(Graphics g) {
		for (int i = 0; i < this.game.getPieces().size(); i++) {
			if (this.game.getPieces().get(i).getColor() == entity.pieces.Color.BLUE)
				g.setColor(Color.BLUE);
			else if (this.game.getPieces().get(i).getColor() == entity.pieces.Color.RED)
				g.setColor(Color.RED);

			int x = (this.game.getPieces().get(i).getLocation().getCol() - 1) * getSquare();
			int y = (this.game.getPieces().get(i).getLocation().getRow() - 1) * getSquare();

			if (this.game.getPieces().get(i) instanceof Pawn)
				g.fillOval(x + 23, y + 40, getSquare() - 20, getSquare() - 20);
			else if (this.game.getPieces().get(i) instanceof King) {
				g.fillOval(x + 23, y + 40, getSquare() - 20, getSquare() - 20);
				g.setColor(new Color(212, 175, 55));
				g.fillOval(x + 28, y + 45, getSquare() - 30, getSquare() - 30);
			}
		}
	}

	public Point gPoint(int x, int y) {
		Point p = new Point(1 + (y / getSquare()), 1 + (x / getSquare()));
		return p;
	}

	public Piece gPiece(Point p) {
		for (int i = 0; i < this.game.getPieces().size(); i++) {
			if (this.game.getPieces().get(i).equals(new Pawn(new Location(p.x, p.y), null, null)))
				return this.game.getPieces().get(i);
		}
		return null;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		select = gPiece(gPoint(e.getX(), e.getY()).getLocation());
	}

	public void mouseReleased(MouseEvent e) {
		Point p = gPoint(e.getX(), e.getY()).getLocation();

		if (select == null || select.getLocation() == null || select.getLocation().equals(new Location(p.x, p.y)))
			return;
		try {
			this.game.Play(select, new Location(p.x, p.y));
		} catch (CheckersException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "There's an error", JOptionPane.ERROR_MESSAGE);
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void GameOver(entity.pieces.Color winner) {
		JOptionPane.showMessageDialog(this, "Congratulations player with the color " + winner + ". You won!");
		System.exit(0);
	}

	public static void main(String[] args) {
		gaming c = new gaming();
		Game game = new Game(c);
		c.setGame(game);

		c.setTitle("Checkers Game");
		c.setSize(game.getDimension() * c.getSquare() + 25, game.getDimension() * c.getSquare() + 40);
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setResizable(false);
		c.setLocationRelativeTo(null);
		c.setVisible(true);
	}
}
