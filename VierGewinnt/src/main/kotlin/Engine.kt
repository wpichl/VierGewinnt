object Engine {
    private var mGameRunning: Boolean = true
    private var mBoardSetup: Boolean = false
    private var mCurrentPlayer: String = "X"
    private lateinit var mBoard: Board

    private fun getColumn(): Int {
        print("[Player: ${this.mCurrentPlayer}] Column to place token in: ")

        var readInput = true
        var column = 0

        while (readInput)
        {
            try {
                val input: String = readLine()!!

                if (input.lowercase() == "n") {
                    this.mBoardSetup = false
                    return -1
                }

                column = input.toInt()

                if (column == 0) {
                    this.mGameRunning = false
                    return -1
                }

                if (column < 1 || column > this.mBoard.width) {
                    println("column must be greater than 1 and less or equal to the boards column size!")
                }

                readInput = false
            }
            catch (ex: NumberFormatException) {
                println("Please input a number!")
            }
        }

        return column - 1;
    }

    private fun switchPlayer() {
        if ((this.mCurrentPlayer == "X")) this.mCurrentPlayer = "Y" else this.mCurrentPlayer = "X"
    }

    fun gameLoop() {
        while (this.mGameRunning) {
            if (!this.mBoardSetup) {
                try {
                    print("Board width: ")
                    val boardWidth: Int = readLine()!!.toInt()
                    print("Board height: ")
                    val boardHeight: Int = readLine()!!.toInt()

                    if (boardHeight < 1 || boardWidth < 1) {
                        println("height or width cannot be less than 1")
                    }
                    else {
                        this.mBoardSetup = true
                        this.mBoard = Board(boardWidth, boardHeight)
                    }
                }
                catch (ex: NumberFormatException) {
                    println("Please input a number!")
                }
            }

            while (!this.mBoard.placeToken(this.getColumn(), this.mCurrentPlayer)) {
                println("Column full!")
            }

            if (this.mGameRunning && this.mBoardSetup) {
                this.mBoard.printBoard()
                this.switchPlayer()
            }
        }
    }

    fun reportWin(player: String) {
        this.mBoard.printBoard()

        println("Player $player has won!")

        this.mBoardSetup = false
    }
}