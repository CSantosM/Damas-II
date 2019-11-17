package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    Error canMove(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        Error error = super.canMove(origin, target, pieceProvider);
         if ( error != null) {
            return error;
        }
        List<Coordinate> coordinateList = origin.getCoordinatesBetween(target);
        if (this.getPiecesNumberOnWay(coordinateList, pieceProvider) > 1 ){
            return Error.MORE_THAN_ONE_PIECE;
        }
        return null;
    }

    private int getPiecesNumberOnWay(List<Coordinate> coordinateList, PieceProvider pieceProvider){
        int piecesNumber = 0;
        for (Coordinate coordinate : coordinateList) {
            if (pieceProvider.getPiece(coordinate) != null) {
                piecesNumber++;
            }
        }
        return piecesNumber;
    }
}