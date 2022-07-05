<!DOCTYPE html>
    <html lang="en">
        <head>
               <meta charset="utf-8">
               <title>${title!"--"}</title>
               <link rel = "stylesheet" href = "assets/css/style.css">
               <link rel = "icon" href = "assets/img/favicon.ico" type="image/x-icon">
        </head>

	<body style="background-image: url(http://img.juimg.com/tuku/yulantu/120301/6388-12030121562814.jpg); background-repeat: no-repeat; background-attachment: fixed; background-size: 100% 100% " >

            <button>
            	<a href="..">back to home page</a>
            </button>

            <p>
                All guesses should have exactly 4 distinct digits excluding zero.
            </p>
            <p>
                Keep guessing until you guess the chosen number (maximum ${max} valid guesses).
            </p>
                 <form action="send-guess">
                    <input type="text" id="guess" name="guess" placeholder="Enter your guess"><br><br>
                    <input type="submit" value="Submit">
                 </form>

             <p>
                ${text}
             </p>

        </body>
    </html>