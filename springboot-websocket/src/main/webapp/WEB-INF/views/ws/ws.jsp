<%@ page language="java" contentType="text/html; charset="
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Boot+WebSocket+广播式</title>
</head>
<body onload="disconnect()">
<noscript><h2 style="color: #ff0000">貌似你的浏览器不支持websocket</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">连接</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>
    <div id="conversationDiv">
        <label>输入你的名字</label><input type="text" id="name" />
        <button id="sendName" onclick="sendName();">发送</button>
        <p id="response"></p>
    </div>
</div>
<script src="../js/jquery/jquery-3.2.1.min.js"></script>
<script src="../js/sockjs/sockjs-0.3.4.min.js"></script>
<script src="../js/stomp/stomp.js"></script>
<script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        $('#response').html();
    }
    
    function connect() {
        var socket = new SockJS('/endpointWisely'); //1
        stompClient = Stomp.over(socket);//2
        stompClient.connect({}, function(frame) {//3
            setConnected(true);
            console.log('开始进行连接Connected: ' + frame);
            stompClient.subscribe('/topic/getResponse', function(respnose){ //4
                showResponse(JSON.parse(respnose.body).responseMessage);
            });
        });
    }
    
    
    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendName() {
        var name = $('#name').val();
        stompClient.send("/welcome", {}, JSON.stringify({ 'name': name }));//5
    }

    function showResponse(message) {
          var response = $("#response");
          response.html(message);
    }
</script>
</body>
</html>