package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }


    @Override
    public String toString()
    {
        return "Q";
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // above
        p.SetValues(position.getRow() - 1, position.getColumn());
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // left
        p.SetValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // right
        p.SetValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // below
        p.SetValues(position.getRow() + 1, position.getColumn());
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // NW
        p.SetValues(position.getRow() - 1, position.getColumn()-1);
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.SetValues(p.getRow() - 1, p.getColumn() -1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // NE
        p.SetValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.SetValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SE
        p.SetValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.SetValues(p.getRow() + 1, p.getColumn() + 1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // DW
        p.SetValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.SetValues(p.getRow() + 1, p.getColumn() - 1);
        }
        if (getBoard().PositionExists(p) && IsThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}

