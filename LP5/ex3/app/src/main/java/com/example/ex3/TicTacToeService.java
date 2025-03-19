package com.example.ex3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TicTacToeService extends Service {

    private static String[][] board = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };
    public static String[][] getBoard() {
        return board;
    }

    private String currentPlayer = "X";
    private boolean gameOver = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if ("MAKE_MOVE".equals(action)) {
            int row = intent.getIntExtra("row", -1);
            int col = intent.getIntExtra("col", -1);

            if (row >= 0 && col >= 0 && board[row][col].equals("") && !gameOver) {
                board[row][col] = currentPlayer;
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";

                if (checkWinner()) {
                    gameOver = true;
                    sendGameStateUpdate("Game Over, Winner: " + (currentPlayer.equals("X") ? "O" : "X"));
                } else {
                    sendGameStateUpdate("Move Made");
                }
            }
        }

        return START_STICKY;
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }

        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void sendGameStateUpdate(String state) {
        Intent intent = new Intent("GAME_STATE_UPDATE");
        intent.putExtra("gameState", state);
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
