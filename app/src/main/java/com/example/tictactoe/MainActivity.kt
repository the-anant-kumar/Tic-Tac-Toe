package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var player = true
    var turn_count = 0
    var boardStatus = Array(3){IntArray(3)}

    lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        reset_game()
        reset.setOnClickListener{
            reset_game()
        }
    }

    private fun reset_game(){
        player = true
        turn_count = 0
        initializeBoardStatus()
        Turn.setText("Player X Turn")
    }
    private fun initializeBoardStatus(){
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }
    override fun onClick(v: View) {
        when(v){
            btn1 ->{
                updateValue(0,0,player)
            }
            btn2 ->{
                updateValue(0,1,player)
            }
            btn3 ->{
                updateValue(0,2,player)
            }
            btn4 ->{
                updateValue(1,0,player)
            }
            btn5 ->{
                updateValue(1,1,player)
            }
            btn6 ->{
                updateValue(1,2,player)
            }
            btn7 ->{
                updateValue(2,0,player)
            }
            btn8 ->{
                updateValue(2,1,player)
            }
            btn9 ->{
                updateValue(2,2,player)
            }
        }
        player = !player
        turn_count++
        if(player){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player 0 Turn")
        }
        if(turn_count == 9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal Rows
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    buttonDisable()
                    updateDisplay("Player X Won")
                    break
                }
                else if(boardStatus[i][0] == 0){
                    buttonDisable()
                    updateDisplay("Player 0 Won")
                    break
                }
            }
        }
        //Vertical Column
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    buttonDisable()
                    updateDisplay("Player X Won")
                    break
                }
                else if(boardStatus[0][i] == 0){
                    buttonDisable()
                    updateDisplay("Player 0 Won")
                    break
                }
            }
        }
        // (0,0) -> (2,2) Diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][2]){
            if(boardStatus[0][0] == 1){
                buttonDisable()
                updateDisplay("Player X Won")
            }
            else if(boardStatus[0][0] == 0){
                buttonDisable()
                updateDisplay("Player 0 Won")
            }
        }
        // (0,2) -> (2,0) Diagonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                buttonDisable()
                updateDisplay("Player X Won")
            }
            else if(boardStatus[0][2] == 0){
                buttonDisable()
                updateDisplay("Player 0 Won")
            }
        }
    }

    private fun updateDisplay(text: String) {
        Turn.setText(text)
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "0"
        val value = if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }


    private fun buttonDisable(){
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled = false;
            }
        }
    }
}