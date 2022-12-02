urlSymbol = window.location.href;
symbol = urlSymbol.substring(urlSymbol.lastIndexOf("/") + 1);
document.getElementById("symbol").innerHTML = symbol;

const url = "wss://stream.data.alpaca.markets/v2/iex"
const auth = {"action":"auth","key":"AKFOO6Z1ZA5J9L4WR4XD","secret":"NGCMczuSOV3LSdOE7v4azBhP1fwzFNyPHHVCH4rS"}
const subscribe = {"action":"subscribe","trades":[symbol],"quotes":[symbol],"bars":[symbol]}

const socket = new WebSocket(url);
socket.onmessage = function(event){
    const data = JSON.parse(event.data)
    const message = data[0]['msg']

    if (message === 'connected') {
        socket.send(JSON.stringify(auth))
    }

    if (message === 'authenticated') {
        socket.send(JSON.stringify(subscribe))
    }

    for (const key in data) {
        console.log(data[key])
        if(data[key]["T"] === 't'){
            console.log("trade")
            console.log(data[key])
        }
        if(data[key]["T"] === 'q'){
            console.log("quote")
            console.log(data[key])
        }
        if(data[key]["T"] === 'b'){
            console.log("bar")
            console.log(data[key])
        }
    }
}

$(document).ready(function() {
    $("#company_Symbol").autocomplete({
        source: "dataAutoComplete",
        minLength: 1
    });
});