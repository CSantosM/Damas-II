package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Pawn extends Piece {

    private static final int MAX_DISTANCE = 2;

    Pawn(Color color) {
        super(color);
    }

    @Override
    Error canMove(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        if (super.canMove(origin, target, pieceProvider) != null) {
            return super.canMove(origin, target, pieceProvider);
        }
        if (!this.isAdvanced(origin, target)) {
            return Error.NOT_ADVANCED;
        }
        int distance = origin.diagonalDistance(target);
        if (distance > Pawn.MAX_DISTANCE) {
            return Error.BAD_DISTANCE;
        }
        if (distance == Pawn.MAX_DISTANCE) {
            if (pieceProvider.getPiece(origin.betweenDiagonal(target)) == null) {
                return Error.EATING_EMPTY;
            }
        }
        return null;
    }

    int getMaxDistance(){
        return Pawn.MAX_DISTANCE;
    }

}