'use strict';
const url = "ws://localhost:8080/chat";
const client = new StompJs.Client({
    brokerURL: url,
});

let buttonConnect;
let buttonDisConnect;
let buttonSendMessage;
let buttonSendPrivateMessage;
let clearMessages
let sessionIdInput;
let connection;
let messages;

window.addEventListener('load', () => {
    buttonConnect = document.getElementById("connect");
    buttonDisConnect = document.getElementById("disconnect");
    buttonSendMessage = document.getElementById("sendMessage");
    buttonSendPrivateMessage = document.getElementById("sendPrivateMessage");
    clearMessages = document.getElementById("clearMessages");
    connection = document.getElementById("connection");
    messages = document.getElementById("messages");
    sessionIdInput = document.getElementById("sessionId");

    buttonDisConnect.disabled = true;
    buttonSendMessage.disabled = true;
    buttonSendPrivateMessage.disabled = true;
})

client.onConnect = () => {
    client.subscribe('/user/topic/connected', (message) => {
        printMessage(connection, message.body);
    });
    client.subscribe('/topic/messages', (message) => {
        printMessage(messages, message.body);
    });
    client.subscribe('/user/topic/messages', (message) => {
        printMessage(messages, message.body);
    });
    client.publish({
        destination: '/app/connected'
    });
};

client.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

client.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function connect() {
    client.activate();
    buttonConnect.disabled = true;
    buttonDisConnect.disabled = false;
    buttonSendMessage.disabled = false;
    buttonSendPrivateMessage.disabled = false;
    clearMessage(messages);
    clearMessage(connection);
}

function disconnect() {
    client.deactivate();
    buttonConnect.disabled = false;
    buttonDisConnect.disabled = true;
    buttonSendMessage.disabled = true;
    buttonSendPrivateMessage.disabled = true;
    clearMessage(messages);
    clearMessage(connection)
}

function sendMessage() {
    client.publish({
        destination: '/app/chat',
        body: JSON.stringify({from: "foo", message: "Spring Boot Websocket"})
    });
}

function sendPrivateMessage() {
    client.publish({
        destination: '/app/private',
        body: JSON.stringify({from: "foo private", message: "Spring Boot Websocket Private", sessionId: sessionIdInput.value})
    });
}

function printMessage(textId, data) {
    textId.innerHTML += data + "<br/>";
}

function clearMessage(textId) {
    textId.innerHTML = "";
}