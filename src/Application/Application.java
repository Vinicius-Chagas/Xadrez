package Application;

import boardGame.Board;
import boardGame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.*;

public class Application {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while(!chessMatch.getCheckMate())
        {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.ReadChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.PossibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.ReadChessPosition(sc);

                ChessPiece capturedtPiece = chessMatch.PerformChessMove(source, target);
                if(capturedtPiece != null)
                {
                    captured.add(capturedtPiece);
                }

                if(chessMatch.getPromoted() != null)
                {
                    System.out.print("Enter a piece to promotion (B/C/R/Q): ");
                    String type = sc.nextLine().toUpperCase(Locale.ROOT);
                    while(!type.equals("B") && !type.equals("C") && !type.equals("R") && !type.equals("Q"))
                    {
                        System.out.print("Invalid value! Enter a piece to promotion (B/C/R/Q): ");
                        type = sc.nextLine().toUpperCase(Locale.ROOT);
                    }
                    chessMatch.replacePromotedPiece(type);
                }
            }
            catch (ChessException e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
