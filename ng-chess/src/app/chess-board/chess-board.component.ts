import { Component, OnInit } from '@angular/core';
import { Board, ChessBoard } from '../chess/Board';
import { Move } from '../chess/Move';
import { Piece } from '../chess/Pieces/Piece';
import { Position } from '../chess/Position';

@Component({
  selector: 'app-chess-board',
  templateUrl: './chess-board.component.html',
  styleUrls: ['./chess-board.component.sass']
})
export class ChessBoardComponent implements OnInit {

  firstSquareSel: Position = null;
  secondSquareSel: Position = null;
  board: Board = new ChessBoard();
  moves: Move[];

  constructor() { }

  ngOnInit(): void {
    this.moves = this.board.generateMoves();
  }

  handleSquareEvent(pos: Position): void {
    console.log(this.moves);
    if (!this.firstSquareSel) {
      let pieceAtPos: Piece = this.board.pieceAt(pos);
      console.log(pieceAtPos);

      if (pieceAtPos) {
        if (this.board.isWhiteTurn === pieceAtPos.isWhite) {
          this.firstSquareSel = pos;
        }
      }
    } else {
      let move: Move = this.moves
        .find(m => m.from.row === this.firstSquareSel.row
          && m.from.col === this.firstSquareSel.col
          && m.to.col === pos.col
          && m.to.row === pos.row);
      if (move) {
        this.board = this.board.makeMove(move);
        this.moves = this.board.generateMoves();
        this.firstSquareSel = null;
        this.secondSquareSel = null;
      }
    }
  }


}
