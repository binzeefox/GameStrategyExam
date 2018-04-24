package com.tongxiwen.gamestrategyexam.games.coins_game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.tongxiwen.gamestrategyexam.games.BaseGameFragment;
import com.tongxiwen.gamestrategyexam.MainActivity;
import com.tongxiwen.gamestrategyexam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoinsGameFragment extends BaseGameFragment implements View.OnClickListener{

    private CoinsGame mGames;
    private boolean isATurn = true;
    private boolean coinA;
    private boolean coinB;

    private TextView playerField;

    public CoinsGameFragment(MainActivity activity) {
        super(activity);
        mGames = new CoinsGame(50);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coins_game, container, false);

        setTitle("硬币赌局");

        v.findViewById(R.id.coin_head).setOnClickListener(this);
        v.findViewById(R.id.coin_tail).setOnClickListener(this);
        v.findViewById(R.id.restart_button).setOnClickListener(this);
        playerField = v.findViewById(R.id.player_name);

        getConsoleField().setHint(R.string.coins_game_intro);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.restart_button){
            mGames.restart(50);
            getConsoleField().setText(null);
            isATurn = true;
            playerField.setText("玩家A：");
            return;
        }
        if (isATurn){
            coinA = view.getId() == R.id.coin_head;
        } else {
            coinB = view.getId() == R.id.coin_head;
            mGames.play(coinA, coinB);
            showResult();
        }
        isATurn = !isATurn;
        playerField.setText("玩家" + (isATurn ? "A" : "B") + ":");
    }

    private void showResult() {
        TextView console = getConsoleField();
        StringBuilder sb = new StringBuilder();
        int[] cash = mGames.getCashStatus();
        sb
                .append("玩家A出示")
                .append(coinA ? "正面" : "反面")
                .append("\n")
                .append("玩家B出示")
                .append(coinB ? "正面" : "反面")
                .append("\n")
                .append("当前比分：")
                .append(cash[0])
                .append(":")
                .append(cash[1])
                .append("\n\n");
        console.append(sb);
    }
}
