import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Piece, PieceType } from '../chess/Pieces/Piece';
import { Position } from '../chess/Position';

@Component({
  selector: 'app-chess-square',
  templateUrl: './chess-square.component.html',
  styleUrls: ['./chess-square.component.sass']
})
export class ChessSquareComponent implements OnInit {

  @Input() piece: Piece;
  @Input() row: number;
  @Input() col: number;
  lightSquare: boolean;
  imgSrc: string = "./assets/chess-icons/";

  @Output() squareClickEvent: EventEmitter<Position> = new EventEmitter<Position>();

  constructor() {

  }

  ngOnInit(): void {
    this.lightSquare = (this.row + this.col) % 2 === 0;
    if (this.piece) {
      this.imgSrc += this.piece.isWhite ? "w" : "b";
      switch (this.piece.kind) {
        case PieceType.Bishop: this.imgSrc += "B"; break;
        case PieceType.King: this.imgSrc += "K"; break;
        case PieceType.Knight: this.imgSrc += "N"; break;
        case PieceType.Queen: this.imgSrc += "Q"; break;
        case PieceType.Pawn: this.imgSrc += "P"; break;
        case PieceType.Rook: this.imgSrc += "R"; break;
        default: break;
      }
      this.imgSrc += ".png";
    } else {
      this.imgSrc = " ";
    }
  }

  onClick(): void {
    this.squareClickEvent.emit({ row: this.row, col: this.col })
  }

}
