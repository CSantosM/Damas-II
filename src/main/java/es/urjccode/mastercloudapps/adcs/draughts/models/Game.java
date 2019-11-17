package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Game {

	private Board board;
	private Turn turn;

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getInitialPiece(coordinate);
				if (piece != null) {
					this.board.put(coordinate, piece);
				}
			}
		}
	}

	public Game(Board board, Turn turn){
		this.turn = turn;
		this.board = board;
	}

	private Piece getInitialPiece(Coordinate coordinate) {
		assert coordinate != null;
		if (coordinate.isBlack()) {
			final int row = coordinate.getRow();
			Color color = null;
			if (row <= 2) {
				color = Color.BLACK;
			} else if (row >= 5) {
				color = Color.WHITE;
			}
			if (color != null) {
				return new Pawn(color);
			}
		}
		return null;
	}

	public void move(Coordinate origin, Coordinate target) {
		assert this.isCorrect(origin, target) == null;
		this.removePieceOnWay(origin, target);
		this.board.move(origin, target);
		Piece piece = this.board.getPiece(target);
		if (piece.isLastRow(target)){
			this.board.remove(target);
			this.board.put(target, new Draught(piece.getColor()));
		}
		this.turn.change();
	}

	public Error isCorrect(Coordinate origin, Coordinate target){
		assert origin != null;
		assert target != null;
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		if (this.turn.getColor() != this.board.getColor(origin)) {
			return Error.OPPOSITE_PIECE;
		}
		return this.board.getPiece(origin).canMove(origin, target, board);
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	public Board getBoard() {
		return this.board;
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	private void removePieceOnWay(Coordinate origin, Coordinate target){
		for (Coordinate coordinate : origin.getCoordinatesBetween(target)) {
			if (this.board.getPiece(coordinate) != null){
				this.board.remove(coordinate);
				break;
			}
		}
	}
}