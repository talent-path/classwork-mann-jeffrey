import { TupleType } from "typescript";

let boolVar : boolean = true;

let numVar : number = 50
// let bigInt : bigint = 20000000000000n;

let oldArray : string[] = ["alice", "bob", "charlie", "dave"];

let tupVar : [string, number] = ["bruh", 420];

let objVar : object = {};
// objVar.prop = 5;     // this cant work...


interface TicTacToePlayer {
    name: string,
    turn: boolean,

}

function printPlayerName(player : TicTacToePlayer) : string {
    console.log(player.name);
    return player.name;
}

interface Student {
    studentId?: number,
    name: string
}

let s : Student = { name: "Jeff" };

let ps : Partial<Student> = {};
ps.name = "Jeff";
ps.studentId = 5;

interface GoodStudent {
    readonly studentId?: number,
    readonly name: string
}

let gs : GoodStudent = { name: "Jeff"};
// gs.studentId = 5;        // this wont work

class TalentPathStudent implements Student {
    studentId: number;      // studentId is required now
    name: string;
    constructor(name:string, studentId:number) {
        this.name = name;
        this.studentId = studentId;
    }
}

