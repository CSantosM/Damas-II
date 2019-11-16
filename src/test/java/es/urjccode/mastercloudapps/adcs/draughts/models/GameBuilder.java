package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GameBuilder {

    private List<String> boardRows;
    private Map<Character, Piece> piecesMap = new HashMap<Character, Piece>();
    private Turn turn;

    GameBuilder(Color turnColor) {
        this.boardRows = new ArrayList<String>();
        this.initialiceMap();
        this.turn = new Turn(turnColor);
    }

    private void initialiceMap() {
        this.piecesMap.put('b', new Pawn(Color.WHITE));
        this.piecesMap.put('B', new Draught(Color.WHITE));
        this.piecesMap.put('n', new Pawn(Color.BLACK));
        this.piecesMap.put('N', new Draught(Color.BLACK));
    }

    GameBuilder row(String row) {
        boardRows.add(row);
        return this;
    }

    Game build() {
        Board board = new Board();
        for (int i = 0; i < this.boardRows.size(); i++) {
            for (int j = 0; j < this.boardRows.get(i).length(); j++) {
                char character = this.boardRows.get(i).charAt(j);
                if (character != ' ') {
                    Piece piece = this.piecesMap.get(character);
                    board.put(new Coordinate(i, j), piece);
                }
            }
        }
        System.out.println(board);
        return new Game(board, this.turn);
    }

}