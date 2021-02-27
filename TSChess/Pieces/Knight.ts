import { Board } from "../Board";
import { Move } from "../Move";
import { Position } from "../Position";
import { ChessPiece } from "./ChessPiece";
import { PieceType } from "./Piece";

export class Knight extends ChessPiece {
    
    constructor(isWhite: boolean) {
        super(PieceType.Knight, isWhite);
    }
    
    generateMoves: (moveOn: Board, loc: Position) => Move[] =
    (moveOn: Board, loc: Position) => {
        let knightMoves: Move[] = [];
        let knightDirections: Position[] = [];
        
        knightDirections.push({ row: 2, col: 1 });
        knightDirections.push({ row: 2, col: -1 });
        knightDirections.push({ row: -2, col: 1 });
        knightDirections.push({ row: -2, col: -1 });
        knightDirections.push({ row: 1, col: 2 });
        knightDirections.push({ row: -1, col: 2 });
        knightDirections.push({ row: 1, col: -2 });
        knightDirections.push({ row: -1, col: -2 });
        
        for (let direction of knightDirections) {
            let directionMoves: Move[] = Knight.slidePiece(moveOn, loc, direction, this.isWhite);
            knightMoves.push(...directionMoves);
        }
        
        return knightMoves;
    }

    static slidePiece(moveOn: Board, loc: Position, direction: Position, isWhite: boolean): Move[] {
        let allMoves : Move[] = [];

            let currentLoc : Position = { row : loc.row + direction.row, col : loc.col + direction.col };

            // if the current location is a viable square on the board AND if 
            if (Knight.isOnBoard( currentLoc ) && moveOn.pieceAt(currentLoc) === null ){
                allMoves.push( { from: loc, to: currentLoc  });
            }

            if( Knight.isOnBoard( currentLoc )){
                if( moveOn.pieceAt(currentLoc) && moveOn.pieceAt(currentLoc).isWhite != isWhite  ){
                    allMoves.push( {from: loc, to: currentLoc});
                }
            }

            return allMoves;
    }

    static isOnBoard(currentLoc: Position) {
        return currentLoc.col < 8 && currentLoc.col >= 0 && currentLoc.row < 8 && currentLoc.row >= 0;
    }

}