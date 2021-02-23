package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        if(getColor() == Color.WHITE)
        {
            p.SetValues(position.getRow() - 1, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.SetValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getBoard().PositionExists(p2) && !getBoard().ThereIsAPiece(p2) && getMoveCount() == 0)
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.SetValues(position.getRow()  - 1, position.getColumn() - 1);
            if(getBoard().PositionExists(p) && IsThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.SetValues(position.getRow() - 1, position.getColumn() + 1);
            if(getBoard().PositionExists(p) && IsThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }

            if(position.getRow() == 3)
            {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().PositionExists(left) && IsThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable());
                {
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().PositionExists(right) && IsThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable());
                {
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
            }

        }
        else
        {
            p.SetValues(position.getRow() + 1, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.SetValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getBoard().PositionExists(p2) && !getBoard().ThereIsAPiece(p2) && getMoveCount() == 0)
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.SetValues(position.getRow()  + 1, position.getColumn() - 1);
            if(getBoard().PositionExists(p) && IsThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.SetValues(position.getRow() + 1, position.getColumn() + 1);
            if(getBoard().PositionExists(p) && IsThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            if(position.getRow() == 4)
            {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().PositionExists(left) && IsThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable());
                {
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().PositionExists(right) && IsThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable());
                {
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
            }
        }
        return mat;
    }

    @Override
    public String toString()
    {
        return "P";
    }
}
