package pl.umg.traincomplete.battle;

import android.widget.ProgressBar;

import pl.umg.traincomplete.trains.Train;

public class Player {
    private final Train train;
    private final int maxHp;
    private final int maxArmour;
    private final ProgressBar hpBar;
    private final ProgressBar armourBar;
    private final ProgressBar loadingBar;



    public Player(ProgressBar hpBar, ProgressBar armourBar, ProgressBar loadingBar, Train train) {
        this.hpBar = hpBar;
        this.armourBar = armourBar;
        this.loadingBar = loadingBar;
        this.train = new Train(train.getHp(), train.getArmour(), train.getPower(), train.getDamage(), train.getAttackSpeed(), train.getImage());
        this.maxHp = train.getHp();
        this.maxArmour = train.getArmour();
        hpBar.setMax(maxHp);
        armourBar.setMax(maxArmour);
    }

    public Train getTrain() {
        return train;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxArmour() {
        return maxArmour;
    }

    public ProgressBar getHpBar() {
        return hpBar;
    }

    public ProgressBar getArmourBar() {
        return armourBar;
    }

    public ProgressBar getLoadingBar() {
        return loadingBar;
    }

    public void resetProgress() {
        loadingBar.setProgress(0);
    }

    public void updateProgress(int value) {
        int newValue = loadingBar.getProgress() + value;

        loadingBar.setProgress(newValue);
    }

    public void updateDefenseBars() {
        armourBar.setProgress(Math.max(train.getArmour(), 0));
        hpBar.setProgress(Math.max(train.getHp(), 0));
    }

    public boolean isLoaded() {
        return loadingBar.getProgress() >= 100;
    }
    public boolean attack(Player player) {
        int damage = this.train.getDamage();
        player.train.decreseHp(damage);
        player.updateDefenseBars();
        this.updateDefenseBars();

        return player.train.getDefense() <= 0;
    }







}