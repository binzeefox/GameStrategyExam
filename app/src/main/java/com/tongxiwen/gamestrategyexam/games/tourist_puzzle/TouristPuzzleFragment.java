package com.tongxiwen.gamestrategyexam.games.tourist_puzzle;

import android.os.Bundle;
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
 * 旅行者问题
 */
public class TouristPuzzleFragment extends BaseGameFragment implements View.OnClickListener{

    private TouristPuzzle mTouristPuzzle;
    private TextView mNameField;
    private EditText mInputField;
    private Button mConfirmButton;
    private TextView mPriceField;
    private boolean isB;
    private int priceA;
    private int priceB;

    public TouristPuzzleFragment(MainActivity activity) {
        super(activity);
        mTouristPuzzle = new TouristPuzzle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tourist_puzzle, container, false);
        getConsoleField().setHint(R.string.tourist_puzzle_intro);
        setTitle("旅行者问题");

        mNameField = v.findViewById(R.id.player_name);
        mInputField = v.findViewById(R.id.input_field);
        mPriceField = v.findViewById(R.id.price_field);
        mConfirmButton = v.findViewById(R.id.confirm_button);
        mConfirmButton.setOnClickListener(this);
        v.findViewById(R.id.restart_button).setOnClickListener(this);

        startGame();
        return v;
    }

    private void startGame() {
        mTouristPuzzle.restart();
        mNameField.setText("旅行者A：");
        getConsoleField().setText(null);
        mPriceField.setText("真实金额" + mTouristPuzzle.getPrice());
        isB = false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_button:
                doConfirm();
                break;
            case R.id.restart_button:
                startGame();
                break;
            default:
                break;
        }
        mInputField.setText(null);
    }

    private void doConfirm() {
        String inputStr = mInputField.getText().toString();
        if (inputStr.isEmpty()){
            Toast.makeText(getActivity(), "请输入金额", Toast.LENGTH_SHORT).show();
            return;
        }
        int input = Integer.parseInt(inputStr);
        if (input<70 || input > 99){
            Toast.makeText(getActivity(), "输入金额不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isB){
            mNameField.setText("旅行者B：");
            priceA = input;
        }else {
            mNameField.setText("旅行者A：");
            priceB = input;
            showConsole();
        }
        isB = !isB;
    }

    private void showConsole() {
        int[] cashStatus = mTouristPuzzle.sign(priceA, priceB);
        StringBuilder sb = new StringBuilder();
        sb
                .append("A输入了").append(priceA).append(",当前剩余").append(cashStatus[0]).append("\n")
                .append("B输入了").append(priceB).append(",当前剩余").append(cashStatus[1]).append("\n\n");
        getConsoleField().setText(sb.append(getConsoleField().getText().toString()));
    }
}
