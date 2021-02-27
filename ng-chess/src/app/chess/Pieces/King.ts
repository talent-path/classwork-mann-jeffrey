import { Board } from "../Board";
import { Move } from "../Move";
import { Position } from "../Position";
import { ChessPiece } from "./ChessPiece";
import { PieceType } from "./Piece";

export class King extends ChessPiece {
    // canKingSideCastle: boolean;
    // canQueenSideCastle: boolean;

    constructor(isWhite: boolean) {
        super(PieceType.King, isWhite);
    }

    generateMoves: (moveOn: Board, loc: Position) => Move[] =
        (moveOn: Board, loc: Position) => {
            let kingMoves: Move[] = [];
            let kingDirections: Position[] = [];
            kingDirections.push({ row: 1, col: 0 });
            kingDirections.push({ row: -1, col: 0 });
            kingDirections.push({ row: 0, col: 1 });
            kingDirections.push({ row: 0, col: -1 });
            kingDirections.push({ row: 1, col: 1 });
            kingDirections.push({ row: -1, col: 1 });
            kingDirections.push({ row: 1, col: -1 });
            kingDirections.push({ row: -1, col: -1 });

            if (King.canKingSideCastle(moveOn, this.isWhite)) kingDirections.push({ row: 0, col: 2 });
            if (King.canQueenSideCastle(moveOn, this.isWhite)) kingDirections.push({ row: 0, col: -2 });

            for (let direction of kingDirections) {
                let moveDirections: Move[] = King.slideMoves(moveOn, loc, direction, this.isWhite);
                kingMoves.push(...moveDirections);
            }

            return kingMoves;
        }

    static slideMoves(moveOn: Board, loc: Position, dir: Position, isWhite: boolean): Move[] {
        let allMoves: Move[] = [];
        let currentLoc: Position = { row: loc.row + dir.row, col: loc.col + dir.col };

        if (King.isOnBoard(currentLoc) && moveOn.pieceAt(currentLoc) === null) {
            allMoves.push({ from: loc, to: currentLoc });
        }

        if (King.isOnBoard(currentLoc) && moveOn.pieceAt(currentLoc) && moveOn.pieceAt(currentLoc).isWhite !== isWhite) {
            allMoves.push({ from: loc, to: currentLoc })
        }

        return allMoves;
    }

    static canKingSideCastle(board: Board, isWhite: boolean) {
        let row: number = isWhite ? 0 : 7;
        if ((isWhite && board.wKingSideCastle) || (!isWhite && board.bKingSideCastle)) {
            return board.pieceAt({ row, col: 5 }) === null
                && board.pieceAt({ row, col: 6 }) === null
        }
    }

    static canQueenSideCastle(board: Board, isWhite: boolean) {
        let row: number = isWhite ? 0 : 7;
        if ((isWhite && board.wQueenSideCastle) || (!isWhite && board.bQueenSideCastle)) {
            return board.pieceAt({ row, col: 1 }) === null
                && board.pieceAt({ row, col: 2 }) === null
                && board.pieceAt({ row, col: 3 }) === null
        }
    }

    // checkCastle(board: Board, isWhite: boolean) {
    //     if (isWhite
    //         && board.pieceAt({ row: 0, col: 1 }) === null
    //         && board.pieceAt({ row: 0, col: 2 }) === null
    //         && board.pieceAt({ row: 0, col: 3 }) === null) {
    //         this.canQueenSideCastle = true;
    //     }
    //     else if (isWhite
    //         && board.pieceAt({ row: 0, col: 5 }) === null
    //         && board.pieceAt({ row: 0, col: 6 }) === null) {
    //         this.canKingSideCastle = true;
    //     }

    //     if (!isWhite
    //         && board.pieceAt({ row: 7, col: 1 }) === null
    //         && board.pieceAt({ row: 7, col: 2 }) === null
    //         && board.pieceAt({ row: 7, col: 3 }) === null) {
    //         this.canQueenSideCastle = true;
    //     }
    //     else if (!isWhite
    //         && board.pieceAt({ row: 7, col: 5 }) === null
    //         && board.pieceAt({ row: 7, col: 6 }) === null) {
    //         this.canKingSideCastle = true;
    //     }
    // }

    static isOnBoard(currentLoc: Position) {
        return currentLoc.col >= 0 && currentLoc.col < 8 && currentLoc.row >= 0 && currentLoc.row < 8;
    }

}