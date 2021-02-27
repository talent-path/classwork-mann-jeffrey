//TODO:
//  reset button X
//  new game button X
//  hide secret number
//  add instructions X
let wins = 0;
let pastWins = [];

let populatePage = function () {
    const submitForm = document.getElementById("GuessForm");
    submitForm.addEventListener("submit", handleSubmit);

    const submitInput = document.createElement("input");
    submitInput.id = "GuessInput"
    submitInput.type = "text";
    submitInput.name = "guess";

    const submitButton = document.createElement("input");
    submitButton.id = "GuessButton"
    submitButton.type = "submit";
    submitButton.value = "Submit Guess";

    const secretNumber = document.createElement("input");
    secretNumber.id = "SecretNumber";
    secretNumber.type = "hidden";
    secretNumber.name = "secretNumber";
    secretNumber.value = generateNumber();

    submitForm.appendChild(submitInput);
    submitForm.appendChild(submitButton);
    submitForm.appendChild(secretNumber);

    const displayDiv = document.getElementById("Display");

    for (let i = 0; i < 4; i++) {
        const numberDiv = document.createElement("div");
        numberDiv.className = "num-display";

        displayDiv.appendChild(numberDiv);
    }
}

let handleSubmit = function (e) {
    e.preventDefault();

    let guessedNumbers = e.target.guess.value.toString().split("");

    if (guessedNumbers.length !== 4) {
        const errors = document.getElementById("Errors");
        errors.innerHTML = "Guess must be Four (4) digits long";
        errors.style.border = "3px solid #db5c5c";
        errors.style.margin = "1rem 0";
        return;
    }
    if (guessedNumbers.some(n => !/[0-9]/.test(n))) {
        const errors = document.getElementById("Errors");
        errors.innerHTML = "Guess must only contain digits";
        errors.style.border = "3px solid #db5c5c";
        errors.style.margin = "1rem 0";
        return;
    }

    const errors = document.getElementById("Errors");
    errors.innerHTML = "";
    errors.style.border = "0";
    errors.style.margin = "0";


    let secretNumbers = e.target.secretNumber.value.split(",");
    const numberDivs = document.getElementsByClassName("num-display");

    for (let i = 0; i < secretNumbers.length; i++) {
        numberDivs[i].innerHTML = guessedNumbers[i];
        numberDivs[i].style.border = "0px solid #dddddd";
        if (secretNumbers[i] === guessedNumbers[i]) {
            numberDivs[i].style.backgroundColor = "#4f964e";
            numberDivs[i].style.color = "white";
        } else if (secretNumbers.includes(guessedNumbers[i])) {
            numberDivs[i].style.backgroundColor = "#ffee59";
            numberDivs[i].style.color = "black";
            numberDivs[i].style.border = "0px solid #dddddd";
        } else {
            numberDivs[i].style.backgroundColor = "#db5c5c";
            numberDivs[i].style.color = "white";
            numberDivs[i].style.border = "0px solid #dddddd";
        }
    }

    if (arrayEquals(secretNumbers, guessedNumbers)) {
        const winFeedback = document.getElementById("WinState");
        winFeedback.style.border = "3px solid #4f964e";
        winFeedback.style.margin = "1rem 0";
        winFeedback.innerHTML = "You won!<br>" + winFeedback.innerHTML;
    }
}

let reset = function () {
    console.log("reset");

    const submitForm = document.getElementById("GuessForm");
    const formChildren = [...submitForm.childNodes];
    for (const child of formChildren) {
        submitForm.removeChild(child);
    }

    const displayDiv = document.getElementById("Display");
    const displayChildren = [...displayDiv.childNodes];
    for (const child of displayChildren) {
        displayDiv.removeChild(child);
    }

    document.getElementById("Errors").innerHTML = "";
    document.getElementById("Errors").style.border = "0";
    document.getElementById("Errors").style.margin = "0";
    document.getElementById("WinState").innerHTML = "";
    document.getElementById("WinState").style.border = "0";
    document.getElementById("WinState").style.margin = "0";

    populatePage();
}

let newGame = function () {
    const numberDivs = document.getElementsByClassName("num-display");
    const secretInput = document.getElementById("SecretNumber");
    const submitInput = document.getElementById("GuessInput");

    wins++;
    pastWins.push(secretInput.value.split(",").join(", "));

    document.getElementById("Errors").innerHTML = "";
    document.getElementById("WinState").innerHTML = "";

    submitInput.value = "";

    secretInput.value = generateNumber();

    for (const div of numberDivs) {
        div.style.backgroundColor = "white";
        div.style.border = "2px solid #000000";
        div.innerHTML = "";
    }

    document.getElementById("WinState").innerHTML = `Wins: ${wins}<hr>Past Codes Broken: <br>${pastWins.join("<br>")}`;

    console.log("new game");
}

let generateNumber = function () {
    let digits = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
    let number = [];
    for (let i = 0; i < 4; i++) {
        const randIdx = Math.trunc(Math.random() * digits.length);

        let digit = digits.splice(randIdx, 1)[0];
        number.push(digit);
    }

    return number;
}

let arrayEquals = function (a, b) {
    if (a === b) return true;
    if (a == null || b == null) return false;
    if (a.length !== b.length) return false;

    for (var i = 0; i < a.length; ++i) {
        if (a[i] !== b[i]) return false;
    }
    return true;
}

window.addEventListener("load", populatePage);