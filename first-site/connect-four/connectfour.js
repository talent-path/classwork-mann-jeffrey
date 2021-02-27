let populatePage = function () {
    let root = document.getElementById("root");
    root.style.margin = 0;
    root.style.padding = 0;
    root.style.width = "100vw";
    root.style.height = "100vh";
    root.style.display = "flex";
    root.style.flex = "1 1 auto";

    let gameSpace = document.createElement("div");
    gameSpace.id = "GameSpace";
    gameSpace.style.width = "50%";
    gameSpace.style.height = "100%";
    gameSpace.style.padding = "1.25rem";

    let gameDiv = document.createElement("div");
    gameDiv.id = "GameBoard"
    gameDiv.style.width = "100%";
    gameDiv.style.height = "auto";
    gameDiv.style.backgroundColor = "white";
    gameDiv.style.border = "2px solid blue";
    gameDiv.style.borderRadius = "8%";
    gameDiv.style.display = "flex";
    gameDiv.style.flexDirection = "column";
    gameDiv.style.flex = "1 1 auto";

    let boardElements = [];
    let isRedTurn = true;

    for (let row = 0; row < 6; row++) {
        boardElements.push([]);
        for (let col = 0; col < 7; col++) {
            let gamePos = document.createElement("div");
            gamePos.className = "game-position";
            gamePos.style.border = "3px solid blue";
            gamePos.style.borderRadius = "50%";
            gamePos.style.width = "16.6%";

            boardElements[row].push(gamePos);
        }
    }

    for (const row of boardElements) {
        let rowDiv = document.createElement("div");
        rowDiv.className = "row";
        rowDiv.style.display = "flex";
        rowDiv.style.flexDirection = "row"
        rowDiv.style.flex = "1 1 auto"
        gameDiv.appendChild(rowDiv);
        for (const el of row) {
            rowDiv.appendChild(el);
        }
    }

    let buttonRow = document.createElement("div");
    buttonRow.id = "ButtonRow";
    buttonRow.style.width = "100%";
    buttonRow.style.marginTop = "1%";
    buttonRow.style.display = "flex";
    for (let col = 0; col < 7; col++) {
        let colButton = document.createElement("button");
        colButton.innerHTML = col;
        colButton.style.fontSize = "20px";
        colButton.style.width = "16.6%";
        colButton.style.marginLeft = "0.25rem";
        colButton.style.border = "3px solid #bbbbff"
        colButton.style.borderRadius = "10%";
        colButton.dataset.column = col;
        colButton.addEventListener("click", click => {
            handleClick(click, boardElements, isRedTurn);
            isRedTurn = !isRedTurn;
        });

        buttonRow.appendChild(colButton);
    }

    gameSpace.appendChild(gameDiv);
    gameSpace.appendChild(buttonRow);

    let gameControls = document.createElement("div");
    gameControls.id = "GameControls";
    gameControls.style.height = "20%";

    let resetButton = document.createElement("button");
    resetButton.onclick = (click) => {
        reset(click, boardElements);
        isRedTurn = true;
    }
    resetButton.innerHTML = "RESET";
    resetButton.style.marginTop = "auto";
    resetButton.style.marginBottom = "auto";

    let winState = document.createElement("span");


    gameControls.appendChild(resetButton);

    root.appendChild(gameSpace);
    root.appendChild(gameControls);
}

let reset = function (click, boardElements) {
    let root = document.getElementById("root");
    let children = [...root.childNodes];
    console.log(boardElements);

    for (const child of children) {
        if (child.tagName === "DIV" || child.tagName === "BUTTON") {
            root.removeChild(child);
        }
    }

    boardElements = [];

    populatePage();
}

let handleClick = function (click, boardElements, isRedTurn) {
    let clickedColumn = click.target.dataset.column;
    let placedRow = -1;

    for (let row = boardElements.length -1; row >= 0; row--) {
        const clickedPos = boardElements[row][clickedColumn];
        if (!clickedPos.dataset.clicked) {
            clickedPos.style.backgroundColor = isRedTurn ? "#ff3333" : "#222222";
            clickedPos.dataset.clicked = true;
            placedRow = row;
            break;
        }
    }

    checkWin(boardElements, placedRow, clickedColumn);
}

let checkWin = function (boardElements, placedRow, placedCol) {
    console.log(placedCol);
    let rowCount = 0;
    for (let rOffset = 0; rOffset < 4; rOffset++) {
        let currRow = placedRow + rOffset;
        if (currRow > 5) break;
        else if (
            boardElements[currRow][placedCol].style.backgroundColor
            === boardElements[placedRow][placedCol].style.backgroundColor
        ) {
            rowCount++;
        }
    }

    // rowCount = 0;
    // for (let cOffset = 0; cOffset < 4; cOffset++) {
    //     let currCol = placedRow + cOffset;
    //     if (currCol > 5) break;
    //     else if (
    //         boardElements[placedCol][currCol].style.backgroundColor
    //         === boardElements[placedCol][placedRow].style.backgroundColor
    //     ) {
    //         rowCount++;
    //     }
    // }

    // rowCount = 0;
    // for (let rOffset = 0; rOffset < 4; rOffset++) {
    //     let currRow = placedRow + rOffset;
    //     if (currRow > 5) break;
    //     else if (
    //         boardElements[placedCol][currRow].style.backgroundColor
    //         === boardElements[placedCol][placedRow].style.backgroundColor
    //     ) {
    //         rowCount++;
    //     }
    // }

    // rowCount = 0;
    // for (let rOffset = 0; rOffset < 4; rOffset++) {
    //     let currRow = placedRow + rOffset;
    //     if (currRow > 5) break;
    //     else if (
    //         boardElements[placedCol][currRow].style.backgroundColor
    //         === boardElements[placedCol][placedRow].style.backgroundColor
    //     ) {
    //         rowCount++;
    //     }
    // }

    if (rowCount === 4) {
        alert("You win!");
    }
}

window.addEventListener("load", populatePage)