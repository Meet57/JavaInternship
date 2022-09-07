<html>
<body>
<h2>Hello World!</h2>
<p><a href="/hello">Hello World</a></p>
<input type="text" placeholder="Name" id="name" />
<button id="button">
    Click Me
</button>
<div id="result" style="border: 1px solid black; padding: 30px; margin: 40px;">
</div>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"
        integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
    $('#button').click(function () {
        $.post("meet", {name: $('#name').val()}
            ,function (data) {
                console.log(data)
                $("#result").empty();
                for (const [key, value] of Object.entries(data)) {
                    $("#result").append(`${key}: ${value} <br>`);
                }
            });
    });
</script>
</body>
</html>
