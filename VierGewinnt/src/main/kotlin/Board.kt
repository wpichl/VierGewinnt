class Board {
    private val mWidth: Int
    private val mHeight: Int
    private val mBoard: Array<Array<String>>

    constructor(width: Int, height: Int) {
        this.mWidth = width
        this.mHeight = height
        this.mBoard = Array(this.mWidth) { Array(this.mHeight) { "-" } }
    }

    fun placeToken(column: Int, player: String) : Boolean {
        for (i in (this.mBoard.size - 1) downTo 0) {
            if (this.mBoard[i][column] == "-") {
                this.mBoard[i][column] = player
                return true;
            }
        }

        return false;
    }

    val width get() = this.mWidth

    fun printBoard() {
        for (i in this.mBoard.indices) {
            println(this.mBoard[i].contentToString())
        }

        println()
    }
}