import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader br;

    private static Character player = null;

    private static Battle battleScene = null;

    public static void main(String[] args) {

        br = new BufferedReader(new InputStreamReader(System.in));

        battleScene = new Battle();

        System.out.println("Enter player name:");

        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {

        if (player == null) {
            player = new Player(
                    string,
                    100,
                    20,
                    20,
                    30,
                    20
            );
            System.out.println(String.format("Spasti mir vizvalsya %s! Da budet ego bronya krepka", player.getName()));

            printNavigation();
        }

        switch (string) {
            case "1": {
                System.out.println("Torgovec ne priehal");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "Da":
                command("2");
                break;
            case "Net": {
                printNavigation();
                command(br.readLine());
            }
        }

        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Kuda vi hotote poiti?");
        System.out.println("1. K torgovcu");
        System.out.println("2. V temnii les");
        System.out.println("3. Vihod");
    }

    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s pobedil! Teper u vas %d opita i %d zolota, takje ostalos' %d edinic zdoroviya.", player.getName(), player.getExp(), player.getGold(), player.getHealth()));
                System.out.println("Prodoljit' ili vernutsya v gorod? (Da/Net)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }

        });
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    private static Character createMonster() {

        int random = (int) (Math.random() * 10);

        if (random % 2 == 0) return new Goblin(
                "Goblin",
                50,
                10,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Skeleton",
                25,
                20,
                20,
                100,
                10
        );
    }
}


