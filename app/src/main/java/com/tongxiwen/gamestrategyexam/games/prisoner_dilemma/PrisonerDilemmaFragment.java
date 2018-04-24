package com.tongxiwen.gamestrategyexam.games.prisoner_dilemma;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tongxiwen.gamestrategyexam.games.BaseGameFragment;
import com.tongxiwen.gamestrategyexam.MainActivity;
import com.tongxiwen.gamestrategyexam.R;

/**
 * Created by tong.xiwen on 2018/4/20.
 */
public class PrisonerDilemmaFragment extends BaseGameFragment implements View.OnClickListener{

    private PrisonerDilemma mGame;
    private TextView playerField;

    private boolean isATurn = true;
    private boolean pA;
    private boolean pB;

    public PrisonerDilemmaFragment(MainActivity activity) {
        super(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.prisoner_dilemma_fragment, container, false);
        setTitle("囚徒困境");
        getConsoleField().setHint(R.string.prisoner_dilemma_intro);
        mGame = new PrisonerDilemma(80);

        v.findViewById(R.id.coin_head).setOnClickListener(this);
        v.findViewById(R.id.coin_tail).setOnClickListener(this);
        v.findViewById(R.id.restart_button).setOnClickListener(this);
        playerField = v.findViewById(R.id.player_name);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.restart_button){
            mGame.restart(80);
            getConsoleField().setText(null);
            isATurn = true;
            playerField.setText("玩家A：");
            return;
        }
        if (isATurn){
            pA = view.getId() == R.id.coin_head;
        } else {
            pB = view.getId() == R.id.coin_head;
            mGame.play(pA, pB);
            showResult();
        }
        isATurn = !isATurn;
        playerField.setText("玩家" + (isATurn ? "A" : "B") + ":");
    }

    private void showResult() {
        TextView console = getConsoleField();
        StringBuilder sb = new StringBuilder();
        int[] cash = mGame.getCashStatus();
        sb
                .append("玩家A选择")
                .append(pA ? "坦白" : "隐瞒")
                .append("\n")
                .append("玩家B选择")
                .append(pB ? "坦白" : "隐瞒")
                .append("\n")
                .append("当前比分：")
                .append(cash[0])
                .append(":")
                .append(cash[1])
                .append("\n\n");
        console.append(sb);
    }
}
