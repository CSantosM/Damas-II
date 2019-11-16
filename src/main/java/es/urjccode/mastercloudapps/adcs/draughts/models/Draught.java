package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    private static final int MAX_DISTANCE = 8;

    Draught(Color color) {
        super(color);
    }

    @Override
    Error canMove(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        Error error = super.canMove(origin, target, pieceProvider);
         if ( error != null) {
            return error;
        }
        return null;
    }

    int getMaxDistance(){
        return Draught.MAX_DISTANCE;
    }

}