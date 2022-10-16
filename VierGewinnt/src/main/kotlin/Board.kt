class Board {
    private val mWidth: Int
    private val mHeight: Int
    private val mBoard: Array<Array<String>>

    constructor(width: Int, height: Int) {
        this.mWidth = width
        this.mHeight = height
        this.mBoard = Array(this.mWidth) { Array(this.mHeight) { " " } }
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

    private fun checkWin(row: Int, column: Int, player: String): Boolean {

        print("row: $row, column: $column")
        //return this.checkVertical(row, column, player)
        return this.checkHorizontal(row, column, player)
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
        for (i in 0 until this.mBoard.size) {
            print(" ${i + 1}  ")
        }

        println()
    }
}