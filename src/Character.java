public abstract class Character implements Fighter {

    String name;
    int health;
    int gold;
    int exp;
    int dexterity;
    int strength;

    public Character(String name, int health, int gold, int exp, int dexterity, int strength) {
        super();
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.exp = exp;
        this.dexterity = dexterity;
        this.strength = strength;
    }
    @Override
    public int attack() {
        if (dexterity * 3 > getRandomValue()) return strength;
        else return  0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }
    @Override
    public String toString() {
        return String.format("%s Health:%d", name, health);
    }
}



