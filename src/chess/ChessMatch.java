package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch()
    {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn()
    {
        return turn;
    }

    public Color getCurrentPlayer()
    {
        return currentPlayer;
    }

    public boolean getCheck()
    {
        return check;
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

    public boolean[][] PossibleMoves(ChessPosition SourcePosition)
    {
        Position position = SourcePosition.toPosition();
        ValidateSourcePosition(position);
        return board.piece(position).PossibleMoves();
    }
    public ChessPiece PerformChessMove(ChessPosition SourcePosition, ChessPosition TargetPosition)
    {
        Position source = SourcePosition.toPosition();
        Position target = TargetPosition.toPosition();
        ValidateSourcePosition(source);
        ValidateTargetPosition(source, target);
        Piece CapturedPiece = MakeMove(source, target);

        if(testCheck(currentPlayer))
        {
            undoMove(source, target, CapturedPiece);
            throw new ChessException("You can not put yourself in check");
        }
        check = (testCheck(opponent(currentPlayer))) ? true : false;

        NextTurn();
        return (ChessPiece)CapturedPiece;
    }

    private void ValidateSourcePosition(Position position)
    {
        if(!board.ThereIsAPiece(position))
        {
            throw new ChessException("There is no piece on source position.");
        }
        if(!board.piece(position).IsThereAnyPossibleMove())
        {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor())
        {
            throw new ChessException(("This piece is not yours."));
        }
    }

    private void ValidateTargetPosition(Position source, Position target)
    {
        if(!board.piece(source).PossibleMove(target))
        {
            throw new ChessException("The chosen piece can not move to target position");
        }
    }

    private void NextTurn()
    {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Piece MakeMove(Position source, Position target)
    {
        Piece p = board.RemovePiece(source);
        Piece CapturedPiece = board.RemovePiece(target);
        board.PlacePiece(p, target);

        if(CapturedPiece != null)
        {
            piecesOnTheBoard.remove(CapturedPiece);
            capturedPieces.add(CapturedPiece);
        }
        return CapturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece)
    {
        Piece p = board.RemovePiece(target);
        board.PlacePiece(p, source);

        if(capturedPiece != null)
        {
            board.PlacePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private Color opponent(Color color)
    {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color)
    {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list)
        {
            if(p instanceof  King)
            {
                return (ChessPiece) p;
            }
        }
        throw  new IllegalStateException("There is no" + color + "King on the board");
    }

    private boolean testCheck(Color color)
    {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for(Piece p : opponentPieces)
        {
            boolean[][] mat = p.PossibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]);
            return true;
        }
        return false;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece)
    {
        board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
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
