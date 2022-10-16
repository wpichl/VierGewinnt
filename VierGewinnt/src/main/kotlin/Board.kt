class Board {
    private val mWidth: Int
    private val mHeight: Int
    private val mBoard: Array<Array<String>>

    constructor(width: Int, height: Int) {
        this.mWidth = width
        this.mHeight = height
        this.mBoard = Array(this.mHeight) { Array(this.mWidth) { " " } }
    }

    val width get() = this.mWidth
    val height get() = this.mHeight

    private fun checkVertical(row: Int, column: Int, icon: String): Boolean {
        var isContinuous = true

        if ((this.height - row) == 4) {
            for (i in 0 until 4) {
                if (this.mBoard[row + i][column] != icon) {
                    isContinuous = false
                }
            }
        }
        else {
            isContinuous = false
        }

        return isContinuous
    }

    private fun checkHorizontal(row: Int, column: Int, icon: String): Boolean {
        var isContinuous = true
        if ((this.width - column >= 4)) {
            for (i in 0 until 4) {
                if (this.mBoard[row][column + i] != icon) {
                    isContinuous = false
                }
            }
        }
        else if (column >= 3) {
            for (i in 0 until 4) {
                if (this.mBoard[row][column - i] != icon) {
                    isContinuous = false
                }
            }
        }
        else {
            isContinuous = false
        }

        return isContinuous
    }

    private fun checkDiagonal(icon: String): Boolean {
        for (row in 0 until (this.mBoard.size - 3)) {
            for (col in 0 until (this.mBoard[row].size - 3)) {
                if (this.mBoard[row][col] == icon && this.mBoard[row + 1][col + 1] == icon && this.mBoard[row + 2][col + 2] == icon && this.mBoard[row + 3][col +3] == icon) {
                    return true
                }
            }
        }

        return false
    }

    private fun checkCounterDiagonal(icon: String): Boolean {
        for (row in 0 until (this.mBoard.size - 3)) {
            for (col in 3 until this.mBoard[row].size) {
                if (this.mBoard[row][col] == icon && this.mBoard[row + 1][col - 1] == icon && this.mBoard[row + 2][col - 2] == icon && this.mBoard[row + 3][col - 3] == icon) {
                    return true
                }
            }
        }

        return false
    }

    private fun checkWin(row: Int, column: Int, player: String): Boolean {
        return this.checkVertical(row, column, player) || this.checkHorizontal(row, column, player) || this.checkDiagonal(player) || this.checkCounterDiagonal(player)
    }

    fun placeToken(column: Int, player: String) : Boolean {
        if (column == -1) {
            return true
        }

        for (i in (this.mBoard.size - 1) downTo 0) {
            if (this.mBoard[i][column] == " ") {
                this.mBoard[i][column] = player
                if (checkWin(i, column, player)) Engine.reportWin(player)
                return true
            }
        }

        return false
    }

    fun printBoard() {
        println()

        for (i in 0 until this.mBoard.size) {
            print("${this.height - i} |")

            for (j in 0 until this.mBoard[i].size) {
                print(" ${this.mBoard[i][j]} |")
            }

            println()
        }

        print("   ")
        for (i in 0 until this.mBoard[1].size) {
            print(" ${i + 1}  ")
        }

        println()
    }
}