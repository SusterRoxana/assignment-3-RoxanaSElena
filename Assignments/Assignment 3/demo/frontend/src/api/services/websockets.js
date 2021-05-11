import SockJS from "sockjs-client";
import Stomp from "stomp-client";

var stompClient = null;

export default {
  showMessage(message) {
    // eslint-disable-next-line no-undef
    $("#consult").append("<tr><td>" + message + "</td></tr>");
  },
  connect() {
    var socket = new SockJS("/gs-guide-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      console.log("Connected: " + frame);
      stompClient.subscribe("/topic/consult", function (message) {
        // eslint-disable-next-line no-undef
        showMessage(JSON.parse(message.body).content);
      });
    });
  },
  disconnect() {
    if (stompClient !== null) {
      stompClient.disconnect();
    }
    console.log("Disconnected");
  },
  sendMessage(message) {
    stompClient.send("/app/consult", {}, JSON.stringify(message));
  },
};
