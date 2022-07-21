package pl.umg.traincomplete.trains;

public class Train implements ITrain {
    private int hp;
    private int armour;
    private int power;
    private int damage;
    private int attackSpeed;
    private int image;

    public Train(int hp, int armour, int power, int damage, int attackSpeed, int image) {
        this.hp = hp;
        this.armour = armour;
        this.power = power;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.image = image;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int getDefense() {
        return this.getArmour() + this.getHp();
    }

    @Override
    public int getOffense() {
        return this.getAttackSpeed() * this.getDamage();
    }

    public void decreseHp(int value) {
        int rest = 0;
        if (this.armour - value > 0) {
            this.armour -= value;
            return;
        }

        rest = Math.abs(armour - value);
        this.armour = 0;
        this.hp -= rest;
    }
}
