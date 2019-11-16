package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    Error canMove(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
         if (super.canMove(origin, target, pieceProvider) != null) {
            return super.canMove(origin, target, pieceProvider);
        }
        return null;
    }

}