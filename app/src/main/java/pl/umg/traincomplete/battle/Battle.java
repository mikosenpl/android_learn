package pl.umg.traincomplete.battle;

import android.content.Intent;
import android.os.CountDownTimer;

import pl.umg.traincomplete.MainGameActivity;


public class Battle {

    public boolean playerWin;
    public boolean gameOver;
    private final int playerId = 0;
    private final int enemyId = 1;
    private final Player[] players = new Player[2];
    private int winner;
    private CountDownTimer timer;

    public Battle(Player player, Player enemy) {
        players[playerId] = player;
        players[enemyId] = enemy;
        gameOver = false;
        playerWin = false;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }

    public void setPlayerWin(boolean playerWin) {
        this.playerWin = playerWin;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public CountDownTimer getTimer() {
        return timer;
    }

    public void setTimer(CountDownTimer timer) {
        this.timer = timer;
    }



    public void startGame() {
        enemyAI();
    }

    public void stopGame() {
        timer.cancel();
    }

    public void loadProgress(int player) {
        players[player].updateProgress(players[player].getTrain().getDamage());
        if (players[player].isLoaded()) {
            shoot(player);
            players[player].resetProgress();
        }
    }

    public void shoot(int attacker) {
        int victim = attacker == playerId ? enemyId : playerId;
        boolean playerDead = players[attacker].attack(players[victim]);

        if (playerDead) {
            winner = attacker;
            if (winner == 0)
                playerWin = true;
            gameOver = true;
            stopGame();
        }
    }
    private void enemyAI() {
        int attackSpeed = players[enemyId].getTrain().getAttackSpeed();
        timer = new CountDownTimer(10000, 700 / attackSpeed) {
            @Override
            public void onTick(long l) {
                loadProgress(enemyId);
            }
            @Override
            public void onFinish() {
                if (!gameOver)
                    enemyAI();
            }
        }.start();
    }
}

