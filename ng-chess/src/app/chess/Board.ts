import { Move } from "./Move";
import { Bishop } from "./Pieces/Bishop";
import { BlackPawn } from "./Pieces/BlackPawn";
import { King } from "./Pieces/King";
import { Knight } from "./Pieces/Knight";
import { Piece, PieceType } from "./Pieces/Piece"
import { Queen } from "./Pieces/Queen";
import { Rook } from "./Pieces/Rook";
import { WhitePawn } from "./Pieces/WhitePawn";
import { Position } from "./Position"

export interface Board {

    isWhiteTurn: boolean;
    allSquares: Piece[][];

    wKingSideCastle: boolean;
    wQueenSideCastle: boolean;
    bKingSideCastle: boolean;
    bQueenSideCastle: boolean;

    fiftyMoveCount: number;
    // enPassantCaptureLoc : Position;

    makeMove: (this: Board, toMake: Move) => Board;
    pieceAt: (loc: Position) => Piece;
    generateMoves: () => Move[];
}


export class ChessBoard implements Board {
    isWhiteTurn: boolean;
    allSquares: Piece[][];

    //TODO: capture this in the copy constructor at some point...
    fiftyMoveCount: number;

    wKingSideCastle: boolean;
    wQueenSideCastle: boolean;
    bKingSideCastle: boolean;
    bQueenSideCastle: boolean;

    // enPassantCaptureLoc?: Position;

    //  rnbqkbnr        7
    //  pppppppp        6
    //  ........        5
    //  ........        4
    //  ........        3
    //  ........        2
    //  PPPPPPPP        1
    //  RNBQKBNR        0

    //  01234567

    pieceAt(loc: Position): Piece {
        return this.allSquares[loc.row][loc.col];
    }

    constructor(copyFrom?: ChessBoard) {
        if (copyFrom) {
            this.allSquares = [];
            this.fiftyMoveCount = 0;

            this.wKingSideCastle = true;
            this.bKingSideCastle = true;
            this.wQueenSideCastle = true;
            this.bQueenSideCastle = true;

            this.buildFrom(copyFrom);
        } else {
            this.allSquares = [];
            this.isWhiteTurn = true;

            this.wKingSideCastle = true;
            this.bKingSideCastle = true;
            this.wQueenSideCastle = true;
            this.bQueenSideCastle = true;

            this.fiftyMoveCount = 0;

            for (let row = 0; row < 8; row++) {
                this.allSquares[row] = [];
                for (let col = 0; col < 8; col++) {

                    if (row === 1 || row === 6) {
                        this.allSquares[row][col] = row === 1 ? new WhitePawn() : new BlackPawn();
                    }

                    if ((row === 0 || row === 7) && (col === 0 || col === 7)) {
                        this.allSquares[row][col] = new Rook(row === 0);
                    }

                    if ((row === 0 || row === 7) && (col === 1 || col === 6)) {
                        this.allSquares[row][col] = new Knight(row === 0);
                    }

                    if ((row === 0 || row === 7) && (col === 2 || col === 5)) {
                        this.allSquares[row][col] = new Bishop(row === 0);
                    }

                    if (col === 3 && (row === 0 || row === 7)) {
                        this.allSquares[row][col] = new Queen(row === 0);
                    }

                    if (col === 4 && (row === 0 || row === 7)) {
                        this.allSquares[row][col] = new King(row === 0);
                    }

                    if (!this.allSquares[row][col]) {
                        this.allSquares[row][col] = null;
                    }

                }
            }
        }
    }

    buildFrom: (toCopy: Board) => void = (toCopy: Board) => {
        // [a, b, c]
        // [[a, b, c]]
        this.fiftyMoveCount = toCopy.fiftyMoveCount;

        this.wKingSideCastle = toCopy.wKingSideCastle;
        this.bKingSideCastle = toCopy.bKingSideCastle;
        this.wQueenSideCastle = toCopy.wQueenSideCastle;
        this.bQueenSideCastle = toCopy.bQueenSideCastle;

        this.isWhiteTurn = toCopy.isWhiteTurn;

        for (let i = 0; i < toCopy.allSquares.length; i++) {
            this.allSquares.push([...toCopy.allSquares[i]]);
        }
    }


    makeMove: (toMake: Move) => Board = toMake => {
        //TODO: enpassant stuff
        //TODO: 50 move rule stuff

        let nextBoard: ChessBoard = new ChessBoard(this);

        let oldPiece: Piece = nextBoard.allSquares[toMake.from.row][toMake.from.col];

        // 50 move rule
        if (nextBoard.pieceAt(toMake.to) !== null) {
            nextBoard.fiftyMoveCount = 0;
        } else if (oldPiece.kind !== PieceType.Pawn) {
            nextBoard.fiftyMoveCount++;
        } else {
            nextBoard.fiftyMoveCount = 0;
        }

        // castling rule
        if (oldPiece && (oldPiece.kind === PieceType.King || oldPiece.kind === PieceType.Rook)) {
            if (oldPiece.isWhite) {
                if (oldPiece.kind === PieceType.King) {
                    nextBoard.wKingSideCastle = false;
                    nextBoard.wQueenSideCastle = false;
                } else if (toMake.from.col === 0) {
                    nextBoard.wQueenSideCastle = false;
                } else if (toMake.from.col === 7) {
                    nextBoard.wKingSideCastle = false;
                }
            } else {
                if (oldPiece.kind === PieceType.King) {
                    nextBoard.bKingSideCastle = false;
                    nextBoard.bQueenSideCastle = false;
                } else if (toMake.from.col === 0) {
                    nextBoard.bQueenSideCastle = false;
                } else if (toMake.from.col === 7) {
                    nextBoard.bKingSideCastle = false;
                }
            }
        }

        nextBoard.allSquares[toMake.from.row][toMake.from.col] = null;
        nextBoard.allSquares[toMake.to.row][toMake.to.col] = oldPiece;
        nextBoard.isWhiteTurn = !this.isWhiteTurn;

        return nextBoard;
    }

    generateMoves(): Move[] {
        let allMoves: Move[] = [];

        for (let row = 0; row < 8; row++) {
            for (let col = 0; col < 8; col++) {
                if (this.allSquares[row][col] && this.allSquares[row][col].isWhite === this.isWhiteTurn) {
                    allMoves.push(...this.allSquares[row][col].generateMoves(this, { row, col }))
                }
            }
        }

        return allMoves;
    }

}

// console.log("attempting to create a board")
// let testBoard: Board = new ChessBoard();
// console.log("done creating a board:");

// // first white turn
// console.log("BOARD", testBoard.allSquares);
// console.log("White King Castle", testBoard.wKingSideCastle);
// console.log("Black King Castle", testBoard.bKingSideCastle);
// console.log("White Queen Castle", testBoard.wQueenSideCastle);
// console.log("Black Queen Castle", testBoard.bQueenSideCastle);

// console.log("MOVES", testBoard.generateMoves());

// testBoard = testBoard.makeMove({ from: { row: 0, col: 0 }, to: { row: 1, col: 0 } });

// console.log("BOARD", testBoard.allSquares);
// console.log("White King Castle", testBoard.wKingSideCastle);
// console.log("Black King Castle", testBoard.bKingSideCastle);
// console.log("White Queen Castle", testBoard.wQueenSideCastle);
// console.log("Black Queen Castle", testBoard.bQueenSideCastle);

// console.log("MOVES", testBoard.generateMoves());

