<!DOCTYPE html>
<html>
<head>
    <title>Servlet JSON POC</title>
</head>
<body>

<h2>Enter values</h2>

<input type="text" id="field1" placeholder="Field 1"/>
<input type="text" id="field2" placeholder="Field 2"/>

<button onclick="send()">Send</button>

<pre id="result"></pre>

<script>
    function send() {
        const f1 = document.getElementById("field1").value;
        const f2 = document.getElementById("field2").value;

        fetch(`/api?f1=${encodeURIComponent(f1)}&f2=${encodeURIComponent(f2)}`)
            .then(res => res.json())
            .then(data => {
                document.getElementById("result").innerText =
                    JSON.stringify(data, null, 2);
            });
    }
</script>

</body>
</html>