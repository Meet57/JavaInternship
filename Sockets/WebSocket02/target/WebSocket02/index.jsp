<html>
<body>
<div id="result">Welcome</div>
<label for="A">Number 1</label><input type="number" id="A"/>
<label for="B">Number 2</label><input type="number" id="B"/>
<br/>
<button onclick="add()"> Add</button>
<button onclick="sub()"> Sub</button>

<script>
    var websocket = new WebSocket("ws://"+window.location.host+"/Calculator");
    var result = document.getElementById("result");
    var A = document.getElementById("A");
    var B = document.getElementById("B");
    var C = document.getElementById("C");
    websocket.onopen = function (message) {
        processOnOpen(message);
    };
    websocket.onmessage = function (message) {
        processOnMessage(message)
    }
    websocket.onclose = function (message) {
        processOnClose(message);
    };
    websocket.onerror = function (message) {
        processOnError(message);
    };

    function processOnOpen(message) {
        console.log(message)
        console.log("Connecting")
    }

    function processOnMessage(message) {
        result.innerHTML = message.data;
    }

    function sendMessageToServer(a, b, operation) {
        websocket.send(operation+" "+a+" "+b);
        // if(a==0 && b==0) websocket.close();
    }

    function add(){
        sendMessageToServer(A.value,B.value,"add")
    }

    function sub(){
        sendMessageToServer(A.value,B.value,"sub")
    }

    function processOnClose(message) {
        websocket.send("Client Disconnected.....");
        result.value += "Server Disconnected....\n";
    }

    function processOnError(message) {
        result.value += "Error......"
    }
</script>

</body>
</html>
