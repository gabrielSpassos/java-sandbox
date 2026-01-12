let stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility =
        connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    const socket = new SockJS("/chat");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, frame => {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/messages', message => {
            showMessageOutput(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    const from = document.getElementById('from').value;
    const text = document.getElementById('text').value;

    stompClient.send(
        "/app/chat",
        {},
        JSON.stringify({ from, message: text })
    );
}

function showMessageOutput(messageOutput) {
    const response = document.getElementById('response');
    const p = document.createElement('p');

    p.style.wordWrap = 'break-word';
    p.appendChild(
        document.createTextNode(
            `${messageOutput.from}: ${messageOutput.message} (${messageOutput.dateTime})`
        )
    );

    response.appendChild(p);
}
