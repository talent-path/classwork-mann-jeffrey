// ee0cedd584282cb762dfdf86a0a4d126
const token = "8fbbdd47c3c46ca1a29065cbda5ac0b0"

const handleGet = function (data) {
    for (const color in data.colors) {
        const colorDiv = $("<div>").css({
            "background-color": color,
            "width": "50px",
            "height": "50px",
            "float": "left"
        }).text(data.colors[color].name);
        $("#Colors").append(colorDiv);
    }
    console.log(data);
}

$.get({
    url: `https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.colors.palettes.getInfo&palette=crayola&access_token=${token}`,
    success: handleGet
})

$.get({
    url: `https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.colors.palettes.getInfo&palette=css3&access_token=${token}`,
    success: handleGet
})

$.get({
    url: `https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.colors.palettes.getInfo&palette=css4&access_token=${token}`,
    success: handleGet
})