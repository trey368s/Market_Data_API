document.getElementById("symbol").innerHTML = window.location.href;
urlSymbol = window.location.href;
tickerSymbol = urlSymbol.substring(urlSymbol.lastIndexOf("/") + 1);
document.getElementById("symbol").innerHTML = tickerSymbol;

const url = "wss://stream.data.alpaca.markets/v2/iex"
const auth = {"action":"auth","key":"AKFOO6Z1ZA5J9L4WR4XD","secret":"NGCMczuSOV3LSdOE7v4azBhP1fwzFNyPHHVCH4rS"}
const subscribe = {"action":"subscribe","trades":[tickerSymbol],"quotes":[tickerSymbol],"bars":[tickerSymbol]}

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