import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';

export interface TicSquare {
  row: number,
  col: number
}

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.scss']
})
export class SquareComponent implements OnInit {

  @Output() clickEvent: EventEmitter<TicSquare> = new EventEmitter<TicSquare>();

  @Input() row: number;
  @Input() col: number;
  @Input() value: number;
  clicked: boolean;

  constructor() { }

  ngOnInit(): void {
    this.clicked = this.value !== 0;
  }

  onClick(): void {
    if (!this.clicked) {
      this.clickEvent.emit({ row: this.row, col: this.col });
    }
  }

}
