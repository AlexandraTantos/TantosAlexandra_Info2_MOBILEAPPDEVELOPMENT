package com.example.ex3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private TextView gameStatus;
    private Button resetButton;
    private String[][] board = new String[3][3];
    private boolean isXTurn = true;
    private BroadcastReceiver gameStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        gameStatus = findViewById(R.id.gameStatus);
        resetButton = findViewById(R.id.resetButton);

        for (int i = 0; i < 9; i++) {
            Button button = new Button(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            int size = (int) (getResources().getDisplayMetrics().widthPixels * 0.25); // butoane mai mici
            params.width = size;
            params.height = size;
            button.setLayoutParams(params);
            button.setTextSize(24);
            button.setAllCaps(false);
            button.setOnClickListener(this::onCellClick);
            gridLayout.addView(button);
        }

        gameStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String gameState = intent.getStringExtra("gameState");
                gameStatus.setText(gameState);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(gameStateReceiver, new IntentFilter("GAME_STATE_UPDATE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(gameStateReceiver);
    }

    private void onCellClick(View v) {
        Button button = (Button) v;
        int position = gridLayout.indexOfChild(button);
        int row = position / 3;
        int col = position % 3;

        if (board[row][col] == null || board[row][col].equals("")) {
            String symbol = isXTurn ? "X" : "O";
            board[row][col] = symbol;
            button.setText(symbol);
            button.setEnabled(false);

            Intent intent = new Intent(this, TicTacToeService.class);
            intent.setAction("MAKE_MOVE");
            intent.putExtra("row", row);
            intent.putExtra("col", col);
            startService(intent);

            if (checkForWinner()) {
                gameStatus.setText(isXTurn ? "X Wins!" : "O Wins!");
                disableAllButtons();
                return;
            }

            isXTurn = !isXTurn;
        }
    }

    private boolean checkForWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) return true;
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) return true;
        }
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) return true;
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) return true;
        return false;
    }

    private void disableAllButtons() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setEnabled(false);
        }
    }

    public void onResetClick(View v) {
        resetGame();

        Intent resetIntent = new Intent(this, TicTacToeService.class);
        resetIntent.setAction("RESET_GAME");
        startService(resetIntent);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setText("");
            button.setEnabled(true);
        }

        gameStatus.setText("Game Started");
    }

    private void resetGame() {
        board = new String[3][3];
        isXTurn = true;
    }
}
