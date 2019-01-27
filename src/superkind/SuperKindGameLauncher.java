package superkind;

import others.GameLauncher;
import others.superkind.GameMonitor;
import others.superkind.GameModel;
import edu.calpoly.testy.TestRunnable;
import java.io.IOException;
import java.util.List;

public final class SuperKindGameLauncher implements GameLauncher {

    @Override
    public String getName() {
        return "sk:";
    }

    @Override
    public boolean run(List<String> args) {
        if (args.size() < 1) {
            return false;
        }
        double seconds = 0.0;
        try {
            seconds = Double.parseDouble(args.get(0));
        } catch (NumberFormatException ex) {
            System.err.println("Error:  " + ex);
            return false;
        }

        try {
            SuperKindGame.loadImages();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);     // Quit the program with an error code
        }
        SuperKindGame game = new SuperKindGame(seconds);

        // Set up game monitors:
        for (int i = 1; i < args.size(); i++) {
            GameMonitor m = new GameMonitor(args.get(i), game.getModel());
            m.start();
        }

        if (game.play()) {
            System.out.println();
            System.out.println("***  Congratulations!  BABYMETAL should give " +
                               "*you* chocolate!  ***");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("***  No chocolate for you ***");
            System.out.println();
        }
        return true;
    }

    @Override
    public void addUnitTests(List<TestRunnable> tests) {
        tests.add(() -> SuperKindGame.loadImages());
    }

    @Override
    public void printUsage() {
        System.err.println("            sk: seconds [ monitor_name * ]");
        System.err.println("                Launches superkind game lasting seconds");
        System.err.println("                May have zero or more game monitors");
    }

}
