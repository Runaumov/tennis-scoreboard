<html>
<head>
    <%@include file="header.jsp"%>
</head>
<main>
    <div class="container">
        <div>
            <h1>Start new match</h1>
            <div class="new-match-image"></div>
            <div class="form-container center">
                <form method="post" action="/tennis-scoreboard/new-match">
                    <p style="color: red;">Sample error message</p>
                    <label class="label-player" for="playerOne">Player one</label>
                    <input class="input-player" placeholder="Name" type="text" id="playerOne"
                           name="playerOne" pattern="[A-Za-z]\. [A-Za-z]+" required
                           title="Enter a name in the format n. surname ">
                    <label class="label-player" for="playerTwo">Player two</label>
                    <input class="input-player" placeholder="Name" type="text" id="playerTwo"
                           name="playerTwo" pattern="[A-Za-z]\. [A-Za-z]+" required
                           title="Enter a name in the format n. surname ">
                    <input class="form-button" type="submit" value="Start">
                </form>
            </div>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>