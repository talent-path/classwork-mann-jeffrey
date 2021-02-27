import { Board } from "../Board";
import { Move } from "../Move";
import { Position } from "../Position";
import { ChessPiece } from "./ChessPiece";
import { PieceType } from "./Piece";

export class BlackPawn extends ChessPiece {
    constructor() {
        super(PieceType.Pawn, false);
    }

    generateMoves: (moveOn: Board, loc: Position) => Move[] =
        (moveOn: Board, loc: Position) => {
            let pawnMoves: Move[] = [];

            let pawnDirections: Position[] = [];
            if (loc.row === 6) {
                pawnDirections.push({ row: -2, col: 0 });
            }
            pawnDirections.push({ row: -1, col: 0 });

            for (let direction of pawnDirections) {
                let moveDirections: Move[] = BlackPawn.movePiece(moveOn, loc, direction);
                pawnMoves.push(...moveDirections);
            }

            let attackDirections: Position[] = [];
            attackDirections.push({ row: -1, col: 1 });
            attackDirections.push({ row: -1, col: -1 });

            for (let direction of attackDirections) {
                let moveDirections: Move[] = BlackPawn.takePiece(moveOn, loc, direction);
                pawnMoves.push(...moveDirections);
            }

            return pawnMoves;
        }

    static movePiece(moveOn: Board, loc: Position, direction: Position): Move[] {
        let allMoves: Move[] = [];

        let currentLoc: Position = { row: loc.row + direction.row, col: loc.col + direction.col };

        if (BlackPawn.isOnBoard(currentLoc) && moveOn.pieceAt(currentLoc) === null) {
            allMoves.push({ from: loc, to: currentLoc });
        }

        return allMoves;
    }

    static takePiece(moveOn: Board, loc: Position, direction: Position): Move[] {
        let allMoves: Move[] = [];
        let currentLoc: Position = { row: loc.row + direction.row, col: loc.col + direction.col };

        if (moveOn.pieceAt(currentLoc) && !moveOn.pieceAt(currentLoc).isWhite) {
            allMoves.push({ from: loc, to: currentLoc });
        }

        return allMoves;
    }

    static isOnBoard(currentLoc: Position) {
        return currentLoc.col < 8 && currentLoc.col >= 0 && currentLoc.row < 8 && currentLoc.row >= 0;
    }
}