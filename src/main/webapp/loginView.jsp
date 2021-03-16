<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>


        body {
            background: lightblue;
            font-family: Arial, sans-serif;
            font-size: 20px;
        }
        form {
            background: lightyellow;
            padding: 4em 4em 2em;
            max-width: 500px;
            max-height: 700px;
            margin: 200px auto 0;
            box-shadow: 0 0 1em #222;

        }
    </style>
</head>
<body>

<form action="login" method="post">
    <h2>Sign In</h2>
    <p>
        <label for="Email" class="floatLabel">Email</label>
        <input id="Email" name="Email" type="text">
    </p>
    <p>
        <label for="password" class="floatLabel">Password</label>
        <input id="password" name="Password" type="password">
        <br>
    </p>

    <p>
        <input type="submit" value="Login" id="submit">
    </p>
</form>

</body>
</html>