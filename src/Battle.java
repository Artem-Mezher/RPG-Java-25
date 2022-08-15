public class Battle {
    public void fight(Character hero, Character monster, Realm.FightCallback fightCallback) {

        Runnable runnable = () -> {

            int turn = 1;

            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Move: " + turn + "----");

                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Character defender, Character attacker, Realm.FightCallback fightCallback) {

        int hit = attacker.attack();

        int defenderHealth = defender.getHealth() - hit;

        if (hit != 0) {
            System.out.println(String.format("%s Nanes udar v %d edinic!", attacker.getName(), hit));
            System.out.println(String.format("U %s ostalos' %d edinic zdoroviya...", defender.getName(), defenderHealth));
        } else {

            System.out.println(String.format("%s promahnulsya!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Player) {

            System.out.println("Vi proigrali...");

            fightCallback.fightLost();
            return true;
        } else if(defenderHealth <= 0) {

            System.out.println(String.format("Vrag pobejden! Vi poluchaete %d opit i %d zolota", defender.getExp(), defender.getGold()));
            attacker.setExp(attacker.getExp() + defender.getExp());
            attacker.setGold(attacker.getGold() + defender.getGold());

            fightCallback.fightWin();
            return true;
        } else {

            defender.setHealth(defenderHealth);
            return false;
        }
    }
}