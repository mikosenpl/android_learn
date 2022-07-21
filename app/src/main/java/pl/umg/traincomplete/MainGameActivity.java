package pl.umg.traincomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import pl.umg.traincomplete.battle.Battle;
import pl.umg.traincomplete.battle.Player;
import pl.umg.traincomplete.trains.Train;
import pl.umg.traincomplete.trains.TrainStore;

public class MainGameActivity extends AppCompatActivity {

    Battle battle;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Train playerTrain = TrainStore.getPlayerTrain();
        Train enemyTrain = TrainStore.trains.get(Utils.getRandomNumber(0, TrainStore.trains.size() - 1));
        ImageView enemyTrainImage = findViewById(R.id.enemyImage);
        enemyTrainImage.setImageResource(enemyTrain.getImage());
        ImageView playerTrainImage = findViewById(R.id.playerImage);
        playerTrainImage.setImageResource(playerTrain.getImage());
        ProgressBar playerHPBar = findViewById(R.id.playerHpBar);
        ProgressBar playerArmour = findViewById(R.id.playerArmourBar);
        ProgressBar playerLoading = findViewById(R.id.playerLoading);
        ProgressBar enemyHPBar = findViewById(R.id.enemyHpBar);
        ProgressBar enemyLoading = findViewById(R.id.enemyLoading);
        ProgressBar enemyArmour = findViewById(R.id.enemyArmourBar);

        Player player = new Player(playerHPBar, playerArmour, playerLoading, playerTrain);
        Player enemy = new Player(enemyHPBar, enemyArmour, enemyLoading, enemyTrain);

        battle = new Battle(player, enemy);

        Button shootButton = findViewById(R.id.shootButton);
        battle.startGame();

        shootButton.setOnClickListener(view -> {
            battle.loadProgress(0);
            if(battle.gameOver==true && battle.playerWin == true) {
                Intent intent = new Intent(getApplicationContext(), WinActivity.class);
                startActivity(intent);
            }
            else if(battle.gameOver==true && battle.playerWin == false) {
                Intent intent = new Intent(getApplicationContext(), LoseActivity.class);
                startActivity(intent);
            }
        });


    }
}