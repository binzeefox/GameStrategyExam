package com.tongxiwen.gamestrategyexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.tongxiwen.gamestrategyexam.games.coins_game.CoinsGameFragment;
import com.tongxiwen.gamestrategyexam.games.nim_game.NimGameFragment;
import com.tongxiwen.gamestrategyexam.games.prisoner_dilemma.PrisonerDilemmaFragment;
import com.tongxiwen.gamestrategyexam.games.shooters_puzzle.ShooterPuzzleFragment;
import com.tongxiwen.gamestrategyexam.games.tourist_puzzle.TouristPuzzleFragment;

public class MainActivity extends AppCompatActivity {

    private static final SparseArray<Class<? extends Fragment>> entrySA;
    static {
        entrySA = new SparseArray<>();
        entrySA.put(R.id.coins_game, CoinsGameFragment.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("请从右侧菜单选择模型");
        getConsoleField().setHint("请从菜单选择模型");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        changeFragment(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(int itemId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        int containId = R.id.fragment_container;
        switch (itemId){
            case R.id.coins_game:
                ft.replace(containId, new CoinsGameFragment(this));
                break;
            case R.id.prisoner_dilemma:
                ft.replace(containId, new PrisonerDilemmaFragment(this));
                break;
            case R.id.nim_game:
                ft.replace(containId, new NimGameFragment(this));
                break;
            case R.id.tourist_puzzle:
                ft.replace(containId, new TouristPuzzleFragment(this));
                break;
            case R.id.shooters_puzzle:
                ft.replace(containId, new ShooterPuzzleFragment(this));
                break;
            default:
                break;
        }
        ft.commit();
    }

    public TextView getConsoleField(){
        return findViewById(R.id.console_field);
    }

    public void setTitle(CharSequence title){
        getSupportActionBar().setTitle(title);
    }
}
