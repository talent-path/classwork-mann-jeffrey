import { Component, OnInit } from '@angular/core';
import { TicSquare } from '../square/square.component';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
  isXTurn: boolean;
  boardState: number[][];
  status: number;
  gameFinished: boolean;
  xWins: number;
  oWins: number;
  draws: number;

  constructor() { }

  ngOnInit(): void {
    this.isXTurn = true;
    this.boardState = [
      [0, 0, 0],
      [0, 0, 0],
      [0, 0, 0]
    ];
    this.status = -1;
    this.xWins = 0;
    this.oWins = 0;
    this.draws = 0;
    this.gameFinished = false;
  }

  handleClickEvent(clicked: TicSquare) {
    console.log(clicked);
    if (!this.gameFinished) {
      this.boardState[clicked.row][clicked.col] = this.isXTurn ? 1 : -1;
      this.status = BoardComponent.checkWin(this.boardState);
      this.isXTurn = !this.isXTurn;
      this.gameFinished = this.status >= 0;
    }
    if (this.gameFinished) {
      const overlay = document.getElementById("Overlay");
      overlay.style.display = "flex";
      return;
    }
  }

  reset() {
    if (this.status === 0) {
      this.draws++;
    } else if (this.isXTurn) {
      this.oWins++;
    } else {
      this.xWins++;
    }
    this.gameFinished = false;
    this.isXTurn = true;
    this.boardState = [
      [0, 0, 0],
      [0, 0, 0],
      [0, 0, 0]
    ];
    this.status = -1;
    this.gameFinished = false;
    const overlay = document.getElementById("Overlay");
    overlay.style.display = "none";
  }

  static checkWin(boardState: number[][]) {
    let status = -1;

    //draw check
    let product = 1;
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        product *= boardState[i][j];
      }
    }
    if (product != 0) status = 0;

    //win check
    for (let row = 0; row < 3; row++) {
      let rowSum = boardState[row][0] + boardState[row][1] + boardState[row][2];
      if (rowSum * rowSum == 9) status = 1;
    }

    // 0 1 2
    // 3 4 5
    // 6 7 8

    for (let col = 0; col < 3; col++) {
      let colSum = boardState[0][col] + boardState[1][col] + boardState[2][col];
      if (colSum * colSum == 9) status = 1;
    }

    let d1Sum = boardState[0][0] + boardState[1][1] + boardState[2][2];
    let d2Sum = boardState[0][2] + boardState[1][1] + boardState[2][0];

    if (d1Sum * d1Sum == 9 || d2Sum * d2Sum == 9) status = 1;

    return status;
  }

}
