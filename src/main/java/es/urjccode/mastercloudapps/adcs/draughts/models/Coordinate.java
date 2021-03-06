package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {

    private int row;
    private int column;
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 7;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate getInstance(String format){
        assert format != null;
        try {
            int value = Integer.parseInt(format);
            int row = value / 10 - 1;
            int column = value % 10 - 1;
            if (row < Coordinate.LOWER_LIMIT || Coordinate.UPPER_LIMIT < row
                || column < Coordinate.LOWER_LIMIT || Coordinate.UPPER_LIMIT < column){
                return null;
            }
            return new Coordinate(row, column);

        } catch(Exception ex){
            return null;
        }
    }

    boolean isDiagonal(Coordinate coordinate) {
        assert coordinate != null;
        return this.row + this.column == coordinate.row + coordinate.column
                || this.row - this.column == coordinate.row - coordinate.column;
    }

    int diagonalDistance(Coordinate coordinate) {
        assert coordinate != null;
        assert this.isDiagonal(coordinate);
        return Math.abs(this.row - coordinate.row);
    }

    List<Coordinate> getAdjacents(){
        List<Coordinate> adjacentCoords = new ArrayList<Coordinate>();
        for(int i = 1; i <= 8; i++){
            if(this.isAdjacentBottomRightValid(i)){
                adjacentCoords.add(this.getAdjacentBottomRightCoordinate(i));
            }
            if(this.isAdjacentTopRightValid(i)){
                adjacentCoords.add(this.getAdjacentTopRightCoordinate(i));
            }
            if(this.isAdjacentBottomLeftValid(i)){
                adjacentCoords.add(this.getAdjacentBottomLeftCoordinate(i));
            }
            if(this.isAdjacentTopLeftValid(i)){
                adjacentCoords.add(this.getAdjacentTopLeftCoordinate(i));
            }
        }
        return adjacentCoords;
    }

    private Coordinate betweenDiagonal(Coordinate coordinate) {
        assert coordinate != null;
        assert this.diagonalDistance(coordinate) == 2;
        int rowShift = 1;
        if (coordinate.row - this.row < 0) {
            rowShift = -1;
        }
        int columnShift = 1;
        if (coordinate.column - this.column < 0) {
            columnShift = -1;
        }
        return new Coordinate(this.row + rowShift, this.column + columnShift);
    }

    List<Coordinate> getCoordinatesBetween(Coordinate target){
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        if(this.diagonalDistance(target) == 2){
            coordinates.add(this.betweenDiagonal(target));
            return coordinates;
        }else if(this.diagonalDistance(target) > 2) {
            Coordinate newOrigin;

            if (this.getRow() > target.getRow()){
                newOrigin = new Coordinate(this.getRow() - 1, Math.abs(this.getColumn() - 1));
            }
            else {
                newOrigin = new Coordinate(this.getRow() + 1, this.getColumn() + 1);
            }
            coordinates.add(newOrigin);
            coordinates.addAll(newOrigin.getCoordinatesBetween(target));
            return coordinates;
        }
        return coordinates;
    }

    boolean isBlack() {
        return (this.row + this.column) % 2 != 0;
    }

    int getRow() {
        return this.row;
    }

    int getColumn() {
        return this.column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        return true;
    }

    private boolean isAdjacentBottomRightValid(int increment){
        return this.getRow() + increment <= Coordinate.UPPER_LIMIT && this.getColumn() + increment <= Coordinate.UPPER_LIMIT;
    }

    private boolean isAdjacentTopRightValid(int increment){
        return this.getRow() - increment >= Coordinate.LOWER_LIMIT && this.getColumn() + increment <= Coordinate.UPPER_LIMIT;
    }

    private boolean isAdjacentBottomLeftValid(int increment){
        return this.getRow() + increment <= Coordinate.UPPER_LIMIT && this.getColumn() - increment >= Coordinate.LOWER_LIMIT;
    }

    private boolean isAdjacentTopLeftValid(int increment){
        return this.getRow() - increment >= Coordinate.LOWER_LIMIT && this.getColumn() - increment >= Coordinate.LOWER_LIMIT;
    }

    private Coordinate getAdjacentBottomRightCoordinate(int increment){
        return new Coordinate(this.getRow() + increment, this.getColumn() + increment);
    }

    private Coordinate getAdjacentTopRightCoordinate(int increment){
        return new Coordinate(this.getRow() - increment, this.getColumn() + increment);
    }

    private Coordinate getAdjacentBottomLeftCoordinate(int increment){
        return new Coordinate(this.getRow() + increment, this.getColumn() - increment);
    }

    private Coordinate getAdjacentTopLeftCoordinate(int increment){
        return new Coordinate(this.getRow() - increment, this.getColumn() - increment);
    }

}