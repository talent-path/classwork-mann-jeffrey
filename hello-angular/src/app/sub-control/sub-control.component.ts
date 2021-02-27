import { Component, OnInit } from '@angular/core';
import * as rowJSON from "../../assets/rows.json";

@Component({
  selector: 'sub-control',
  templateUrl: './sub-control.component.html',
  styleUrls: ['./sub-control.component.css']
})
export class SubControlComponent implements OnInit {

  rows: any;

  constructor() { }

  ngOnInit(): void {
   this.rows = rowJSON;
  }

}
