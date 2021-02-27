import { Component, Input, OnInit } from '@angular/core';
import Vex from 'vexflow';

@Component({
  selector: 'test-control',
  templateUrl: './test-control.component.html',
  styleUrls: ['./test-control.component.css']
})
export class TestControlComponent implements OnInit {

  @Input() row;
  // {
  //   noteOrder: number[]
  //   composer: {
  //     name: string,
  //     href?: string,
  //     linkTitle?: string
  //   },  
  //   work: {
  //     title: string,
  //     href?: string,
  //     linkTitle?: string
  //   }
  // };

  constructor() { }

  ngOnInit(): void {

    const VF = Vex.Flow;

    let vf = new VF.Factory({ renderer: { elementId: "boo" } });
    let score = vf.EasyScore();
    let system = vf.System();

    system.addStave({
      voices: [
        score.voice(score.notes(TestControlComponent.parseRow(this.row.default[3].noteOrder), { stem: "up" }), { time: "24/4" })
      ]
    }).addClef("treble")

    vf.draw();
  }

  static parseRow(row: number[]): string {
    let noteStr: string = "";

    for (let note of row) {
      switch (note) {
        case 0:
          noteStr += "C4, ";
          break;
        case 1:
          noteStr += "C#4, ";
          break;
        case 2:
          noteStr += "D4, ";
          break;
        case 3:
          noteStr += "D#4, ";
          break;
        case 4:
          noteStr += "E4, ";
          break;
        case 5:
          noteStr += "F4, ";
          break;
        case 6:
          noteStr += "F#4, ";
          break;
        case 7:
          noteStr += "G4, ";
          break;
        case 8:
          noteStr += "G#4, ";
          break;
        case 9:
          noteStr += "A4, ";
          break;
        case 10:
          noteStr += "A#4, ";
          break;
        case 11:
          noteStr += "B4, ";
          break;
        default:
          break;
      }
    }

    noteStr = noteStr.replace(", ", "/h, ")

    noteStr = noteStr.slice(0, noteStr.lastIndexOf(","));
    console.log(noteStr);
    return noteStr;
  }
}
