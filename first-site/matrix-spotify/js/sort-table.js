
// const getCellValue = (tr, idx) => tr.children[idx].innerText || tr.children[idx].textContent;

// const comparer = (idx, asc) => (a, b) => ((v1, v2) =>
//     v1 !== '' && v2 !== '' && !isNaN(v1) && !isNaN(v2) ? v1 - v2 : v1.toString().localeCompare(v2)
// )(getCellValue(asc ? a : b, idx), getCellValue(asc ? b : a, idx));

// document.querySelectorAll('th').forEach(th => th.addEventListener('click', (() => {
//     const table = th.closest('table');
//     Array.from(table.querySelectorAll('tr:nth-child(n+2)'))
//         .sort(comparer(Array.from(th.parentNode.children).indexOf(th), this.asc = !this.asc))
//         .forEach(tr => table.appendChild(tr));
// })));

const handleSubmit = function (e) {
    e.preventDefault();

    const query = e.target.query.value;
    const filter = e.target.filter.value;
    const headerIndex = $("#" + filter).data("index");
    console.log("submit", query, filter, headerIndex);

    const searchedRows = $(`tbody > tr > td:nth-child(${headerIndex})`).filter(function () {
        switch (filter) {
            case "Row":
                return $(this).text().replace("10", "t").replace("11", "e").replace(", ", "").includes(query);
            case "Composer":
                return $(this).text().toLowerCase().includes(query.toLowerCase());
            case "Work":
                return $(this).text().toLowerCase().includes(query.toLowerCase());
            default:
                return false;
        }
    }).closest("tr");

    $("#Body").empty().append(searchedRows);
}

const reset = function () {
    $("#Body").empty();
    getRowJson(spotify_access);
}

$("#Reset").on("click", reset);
$("form").first().on("submit", handleSubmit);