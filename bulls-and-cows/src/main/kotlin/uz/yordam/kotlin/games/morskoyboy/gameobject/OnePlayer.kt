package uz.yordam.kotlin.games.morskoyboy.gameobject

const val MAX_GUESSES = 10

class OnePlayer(val idPlayer: Int) {
    var num = String()
    var guesses = 0
    var bulls = IntArray(MAX_GUESSES){-1}
    var cows = IntArray(MAX_GUESSES){-1}
    var guessArr = IntArray(MAX_GUESSES){-1}

    fun logic(guess: String, idOnePlayer: Int): String {
        var msg = ""

        if (guess == num) {
            msg = "You've won with ${++guesses} valid guesses!"

            val player = Room.playerList.find {
                it.idPlayer == idOnePlayer
            }!!

            Room.playerList.remove(player)
            return msg
        }
        val n = guess.toIntOrNull()
        if (n == null)
            msg = "Not a valid number"
        else if ('-' in guess || '+' in guess)
            msg = "Can't contain a sign"
        else if ('0' in guess)
            msg = "Can't contain zero"
        else if (guess.length != 4)
            msg = "Must have exactly 4 digits"
        else if (guess.toSet().size < 4)
            msg = "All digits must be distinct"
        else {
             bulls[guesses] = 0
             cows[guesses] = 0


            for ((i, c) in guess.withIndex()) {
                if (num[i] == c) bulls[guesses]++
                else if (c in num) cows[guesses]++
            }

            guessArr[guesses] = guess.toInt()

            var b = 0
            var c = 0
            var g = 0

            for(i in 0..9) {
                if(bulls[i] != -1) {
                    b = bulls[i]
                    c = cows[i]
                    g = guessArr[i]
                    msg += "$g - Bulls = $b  Cows = $c  "
                    //msg += "\n"
                }
            }

            guesses++
            if (guesses == MAX_GUESSES) {
                msg = "You've now had $guesses valid guesses, the maximum allowed. The secret number was $num"

                val player = Room.playerList.find {
                    it.idPlayer == idOnePlayer
                }!!

                Room.playerList.remove(player)

            }
        }
        return msg
    }


    fun getMaxGuesses(): Int {
        return MAX_GUESSES
    }


}
