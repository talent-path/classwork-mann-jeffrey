const client_id = config.spotify_client_id;
const client_secret = config.spotify_client_secret;
const encoded = window.btoa(client_id + ":" + client_secret);
let spotify_access;

let handleAuth = function (data, status, jqxhr) {

    const { access_token } = data;
    spotify_access = access_token;

    return access_token;
}

const getRowJson = function (data) {
    const access_token = data.access_token;
    $.getJSON("./assets/rows.json", data => {
        for (let i = 0; i < data.length; i++) {
            const { noteOrder, composer, work } = data[i];
            const workTitle = work.linkTitle ? work.linkTitle : work.title;

            $("table > tbody").append(
                `<tr id="${composer.name.replace(/\s+/g, "") + i}">
                    <th>${i + 1}</th>
                    <td>${noteOrder.join(", ")}</td>
                    <td>${composer.href ? `<a href=${composer.href}>${composer.name}</a>` : composer.name}</td>
                    <td>${work.href ? `<a href=${work.href}>${workTitle}</a>` : workTitle}</td>
                </tr>`
            );
        }
        return data;
    })
        .done((d) => getSpotifyData(d, access_token));
}

const getSpotifyData = function (data, access_token) {
    for (let i = 0; i < data.length; i++) {
        const { composer, work } = data[i];
        const workTitle = work.linkTitle ? work.linkTitle : work.title;
        $.get({
            url: `https://api.spotify.com/v1/search?q=${composer.name + "%20" + workTitle}&type=track&limit=1`,
            headers: {
                "Authorization": "Bearer " + access_token,
            },
            success: (data) => {
                const foundTracks = data.tracks.items;
                if (data.tracks.items.length > 0) {
                    $(`#${composer.name.replace(/\s+/g, "") + i}`)
                        .append(`<td><a href=${foundTracks[0].external_urls.spotify} target="_blank">Link</a></td>
                    <td>
                        <img src=${foundTracks[0].album.images[2].url}
                        alt=${foundTracks[0].album.name}
                        style="width:${foundTracks[0].album.images[2].width};height:${foundTracks[0].album.images[2].height}">
                    </td>
                    <td>
                        ${foundTracks[0].preview_url
                                ? `<audio controls src=${foundTracks[0].preview_url}>
                                    Your browser does not support the
                                    <code>audio</code> element.
                                </audio>`
                                : "N/A"}
                    </td>`);
                } else {
                    $(`#${composer.name.replace(/\s+/g, "") + i}`)
                        .append(`<td>N/A</td><td>N/A</td><td>N/A</td>`);
                }
            }
        })
    }
}

window.addEventListener("load", () => {
    $.post({
        url: "https://accounts.spotify.com/api/token",
        headers: {
            "Authorization": "Basic " + encoded
        },
        data: {
            grant_type: "client_credentials"
        },
        success: handleAuth,
    })
        .done(getRowJson);
})
