package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;


import java.awt.*;

public class ChessMatch {
    private Board board;

    public ChessMatch()
    {
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece[][] getPieces()
    {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i< board.getRows(); i++)
        {
            for(int j=0; j< board.getColumns(); j++)
            {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public ChessPiece PerformChessMove(ChessPosition SourcePosition, ChessPosition TargetPosition)
    {
        Position source = SourcePosition.toPosition();
        Position target = TargetPosition.toPosition();
        ValidadeSourcePosition(source);
        Piece CapturedPiece = MakeMove(source, target);
        return (ChessPiece)CapturedPiece;
    }

    private void ValidadeSourcePosition(Position position)
    {
        if(!board.ThereIsAPiece(position))
        {
            throw new ChessException("There is no piece on source position.");
        }
    }

    private Piece MakeMove(Position source, Position target)
    {
        Piece p = board.RemovePiece(source);
        Piece CapturedPiece = board.RemovePiece(target);
        board.PlacePiece(p, target);
        return CapturedPiece;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece)
    {
        board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
            placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
