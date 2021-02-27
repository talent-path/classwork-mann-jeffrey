$("#ZipForm").on("submit", handleSubmit);

function handleSubmit(e) {
    e.preventDefault();

    const zip = e.target.zip.value;
    const unit = e.target.unit.value;
    let unitName = "";

    switch (unit) {
        case "imperial":
            unitName = "ºF";
            break;
        case "metric":
            unitName = "ºC";
            break;
        case "standard":
            unitName = "K";
            break;
        default:
            break;
    }

    $.getJSON(
        `https://api.openweathermap.org/data/2.5/weather?units=${unit}&zip=${zip},US&appid=b1035f80353b1f9352549b1928dcdb40`,
        (data) => {
            console.log(data);
            $("#Report").html(`${data.weather[0].main} in ${data.name}`).css("text-align", "center");
            $("#Sunrise").html(`Sunrise: ${formatAMPM(new Date(1000 * data.sys.sunrise))}<br>Sunset: ${formatAMPM(new Date(1000 * data.sys.sunset))}`);
            $("#Temperature").html(`Temperature: ${data.main.temp}${unitName}`);
            $("#Humidity").html(`Humidity: ${data.main.humidity}%`);
            $("#WeatherIcon").attr("src", `http://openweathermap.org/img/wn/${data.weather[0].icon}.png`, "alt", data.weather[0].description)
        }
    );
}

function formatAMPM(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'pm' : 'am';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0' + minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return strTime;
}