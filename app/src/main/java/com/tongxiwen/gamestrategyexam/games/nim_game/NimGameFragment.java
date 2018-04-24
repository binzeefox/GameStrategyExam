package com.tongxiwen.gamestrategyexam.games.nim_game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.tongxiwen.gamestrategyexam.games.BaseGameFragment;
import com.tongxiwen.gamestrategyexam.MainActivity;
import com.tongxiwen.gamestrategyexam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NimGameFragment extends BaseGameFragment implements View.OnClickListener{
    private NimGame mNimGame;
    private TextView mTotalField;
    private TextView mHandField;
    private EditText mTakeField;
    private Button mTakeButton;
    private TextView mNameField;

    public NimGameFragment(MainActivity activity) {
        super(activity);
        mNimGame = new NimGame();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nim_game, container, false);
        getConsoleField().setHint(R.string.nim_game_intro);
        setTitle("Nim游戏");

        mTotalField = v.findViewById(R.id.total_filed);
        mHandField = v.findViewById(R.id.hand_field);
        mNameField = v.findViewById(R.id.player_name);
        mTakeField = v.findViewById(R.id.take_field);

        mTakeButton = v.findViewById(R.id.take_button);
        mTakeButton.setOnClickListener(this);
        v.findViewById(R.id.restart_button).setOnClickListener(this);

        doRestart();
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.take_button:
                doTake();
                break;
            case R.id.restart_button:
                doRestart();
                break;
            default:
                break;
        }
        mTakeField.setText(null);
    }

    private void doRestart() {
        mNimGame.restart();
        getConsoleField().setText(null);
        mTakeButton.setEnabled(true);
        mNameField.setText("玩家A：");
        mTakeField.setText(null);
        mTotalField.setText("共有" + mNimGame.getTotal() + "单位");
        mHandField.setText("一次最多可拿取" + mNimGame.getHandSize() + "单位");
    }

    private void doTake() {
        if (mTakeField.getText() == null) {
            Toast.makeText(getActivity(), "请输入拾取数字", Toast.LENGTH_SHORT).show();
            return;
        }
        int take = Integer.parseInt(mTakeField.getText().toString());
        int hand = mNimGame.getHandSize();
        if (take > hand){
            Toast.makeText(getActivity(), "输入数字过大", Toast.LENGTH_SHORT).show();
            return;
        }
        mNimGame.take(take);
        StringBuilder sb = new StringBuilder();
        sb
                .append(mNimGame.getIfATurn() ? "玩家A" : "玩家B")
                .append("拿取了")
                .append(take)
                .append("单位")
                .append("\n")
                .append("当前剩余")
                .append(mNimGame.getTotal())
                .append("单位");
        if (mNimGame.getTotal() <= 0){
            sb
                    .append("\n")
                    .append(mNimGame.getIfATurn() ? "玩家A" : "玩家B")
                    .append("获胜了！！！");
        }
        sb.append("\n\n");

        if (mNimGame.getTotal() > 0) {
            mNameField.setText(mNimGame.getIfATurn() ? "玩家A：" : "玩家B：");
        } else {
            mTakeButton.setEnabled(false);
        }
        getConsoleField().setText(sb.append(getConsoleField().getText().toString()));
    }


}
