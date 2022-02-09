
import java.io.Console;

public class TicTacToe {

    // Model
    TicTacToeGame game;

    // Views
    private View[] views;
    private int numberOfViews;

    int lines = 3, columns = 3, win = 3;
    Console console = System.console();

    public TicTacToe(int lines, int columns) {
        this(lines, columns, 3);
    }

    public TicTacToe(int lines, int columns, int win) {

        this.lines = lines;
        this.columns = columns;
        this.win = win;

        if (lines < 2) {
            System.out.println("Invalid argument, using default...");
            this.lines = 3;
        }
        if (columns < 2) {
            System.out.println("Invalid argument, using default...");
            this.columns = 3;
        }
        if (win < 2) {
            System.out.println("Invalid argument, using default...");
            this.win = 3;
        }

        startGame();

    }

    public void startGame() {

        views = new View[1];
        numberOfViews = 0;

        game = new TicTacToeGame(lines, columns, win);

        register(new TextView(game));

        // while (game.getGameState() == GameState.PLAYING) {
        // System.out.println(game.toString());
        // System.out.printf("%s to play: ", game.nextCellValue().name());
        // String index = System.console().readLine();
        // int i = Integer.parseInt(index) - 1;
        // game.play(i);
        // }
        update();

    }

    private void register(View view) {
        views[numberOfViews] = view;
        numberOfViews++;
    }

    private void update() {
        for (View view : views) {
            view.update();
        }
    }

}