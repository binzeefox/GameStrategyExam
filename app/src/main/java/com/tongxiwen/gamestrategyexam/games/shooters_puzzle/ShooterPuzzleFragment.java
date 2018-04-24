package com.tongxiwen.gamestrategyexam.games.shooters_puzzle;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RadioGroup;
import com.tongxiwen.gamestrategyexam.games.BaseGameFragment;
import com.tongxiwen.gamestrategyexam.MainActivity;
import com.tongxiwen.gamestrategyexam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShooterPuzzleFragment extends BaseGameFragment implements View.OnClickListener {

    private ShootersPuzzle mGame;
    private RadioGroup groupA;
    private RadioGroup groupB;
    private RadioGroup groupC;

    private int round;

    public ShooterPuzzleFragment(MainActivity activity) {
        super(activity);
        mGame = new ShootersPuzzle();
        round = 1;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shooter_puzzle, container, false);
        getConsoleField().setHint(R.string.shooters_puzzle_intro);
        setTitle("枪手困境");

        v.findViewById(R.id.fire_button).setOnClickListener(this);
        v.findViewById(R.id.clean_button).setOnClickListener(this);

        groupA = v.findViewById(R.id.shooterA_field);
        groupB = v.findViewById(R.id.shooterB_field);
        groupC = v.findViewById(R.id.shooterC_field);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fire_button:
                doFire();
                break;
            case R.id.clean_button:
                getConsoleField().setText(null);
                mGame.restart();
                checkFieldStatus(mGame.getShooterStatus());
                round = 1;
                break;
            default:
                break;
        }
    }

    private void doFire() {
        final SparseArray<String> shooters = new SparseArray<>();
        shooters.put(ShootersPuzzle.A, "射向了枪手A");
        shooters.put(ShootersPuzzle.B, "射向了枪手B");
        shooters.put(ShootersPuzzle.C, "射向了枪手C");
        shooters.put(ShootersPuzzle.DEAD, "");

        int targetA = ShootersPuzzle.DEAD;
        int targetB = ShootersPuzzle.DEAD;
        int targetC = ShootersPuzzle.DEAD;
        boolean[] shooterStatus = mGame.getShooterStatus();
        if (shooterStatus[0])
            targetA = groupA.getCheckedRadioButtonId() == R.id.shooterB ? ShootersPuzzle.B : ShootersPuzzle.C;
        if (shooterStatus[1])
            targetB = groupA.getCheckedRadioButtonId() == R.id.shooterA ? ShootersPuzzle.A : ShootersPuzzle.C;
        if (shooterStatus[2])
            targetC = groupA.getCheckedRadioButtonId() == R.id.shooterA ? ShootersPuzzle.A : ShootersPuzzle.B;

        boolean[] result = mGame.fire(targetA, targetB, targetC);
        StringBuilder sb = new StringBuilder();
        sb
                .append("第").append(round).append("轮射击").append("\n")
                .append("枪手A").append(shooters.get(targetA)).append("，当前").append(result[0] ? "存活" : "死亡").append("\n")
                .append("枪手B").append(shooters.get(targetB)).append("，当前").append(result[1] ? "存活" : "死亡").append("\n")
                .append("枪手C").append(shooters.get(targetC)).append("，当前").append(result[2] ? "存活" : "死亡").append("\n\n");

        getConsoleField().setText(sb.append(getConsoleField().getText().toString()));
        checkFieldStatus(result);
        round++;
    }

    private void checkFieldStatus(boolean[] status) {
        groupA.getChildAt(0).setEnabled(status[0]);
        groupA.getChildAt(1).setEnabled(status[0]);
        groupB.getChildAt(0).setEnabled(status[1]);
        groupB.getChildAt(1).setEnabled(status[1]);
        groupC.getChildAt(0).setEnabled(status[2]);
        groupC.getChildAt(1).setEnabled(status[2]);
    }
}
