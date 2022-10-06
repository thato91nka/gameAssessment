function startGame() {
    const BASE_URL = "http://localhost:8080";
    const payload = {
        players: [
            {
                name: document.getElementsByName("player").item(0).value,
            },
            {
                name: document.getElementsByName("player").item(1).value,
            },
        ],
        gameName: document.getElementsByName("game_name").item(0).value
    };
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify(payload);

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch(`${BASE_URL}/game/start`, requestOptions)
        .then(response => response.json())
        .then(board => {
            console.log(board)
        })
        .catch(error => console.log('error', error));
}

