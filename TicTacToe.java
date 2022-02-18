import java.awt.*;
import java.awt.event.*;
import java.io.Console;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TicTacToe implements ActionListener {

    // Model
    TicTacToeGame game;

    // Views
    private View[] views;
    private int numberOfViews;

    int lines = 3, columns = 3, win = 3;
    Console console = System.console();

    ImageIcon x;
    ImageIcon o;

    public TicTacToe() {
        this(3, 3, 3);
    }

    public TicTacToe(int lines, int columns) {
        this(lines, columns, 3);
    }

    public TicTacToe(int lines, int columns, int win) {

        this.lines = lines;
        this.columns = columns;
        this.win = win;

        x = new ImageIcon(getClass().getResource("images//x.png"));
        o = new ImageIcon(getClass().getResource("images//o.png"));

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

        views = new View[2];
        numberOfViews = 0;

        game = new TicTacToeGame(lines, columns, win);

        register(new GraphicalView(game, this));
        register(new TextView(game));

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

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void actionPerformed(ActionEvent e) {

        if (game.getGameState() == GameState.PLAYING) {
            JButton pressedButton = (JButton) e.getSource();
            int index = Integer.parseInt(pressedButton.getName());

            // change the button to the corresponding CellValue
            if (game.nextCellValue() == CellValue.X) {
                pressedButton.setIcon(resizeIcon(x, pressedButton.getWidth(), pressedButton.getHeight()));
            } else if (game.nextCellValue() == CellValue.O) {
                pressedButton.setIcon(resizeIcon(o, pressedButton.getWidth(), pressedButton.getHeight()));
            }

            game.play(index);

            GraphicalView view = (GraphicalView) views[0];

            view.setControlPanel();

            update();
        }

    }

}