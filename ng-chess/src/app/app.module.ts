import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ChessSquareComponent } from './chess-square/chess-square.component';
import { ChessBoardComponent } from './chess-board/chess-board.component';

@NgModule({
  declarations: [
    AppComponent,
    ChessSquareComponent,
    ChessBoardComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
