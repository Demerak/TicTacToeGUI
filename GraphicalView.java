import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GraphicalView extends JFrame implements View {
    private TicTacToeGame game;
    private JLabel gameState;

    public GraphicalView(TicTacToeGame game, TicTacToe controller) {
        super("TicTacToe Game");

        this.game = game;

        JPanel controlPanel = new JPanel();
        gameState = new JLabel(game.nextCellValue().name() + " to play", SwingConstants.CENTER);
        gameState.setFont(new Font("Serif", Font.BOLD, 25));
        gameState.setBackground(Color.WHITE);
        controlPanel.add(gameState, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.BLACK);
        boardPanel.setLayout(new GridLayout(game.getLines(), game.getColumns(), 5, 5));
        for (int i = 0; i < (game.getLines() * game.getColumns()); i++) {
            JButton button = new JButton();
            button.setName(Integer.toString(i));
            button.setBackground(Color.WHITE);
            button.addActionListener(controller);
            boardPanel.add(button);
        }

        add(boardPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setVisible(true);

    }

    public void setControlPanel() {
        if (game.getGameState() == GameState.PLAYING) {
            gameState.setText(game.nextCellValue().name() + " to play");
        } else {
            gameState.setText(game.getGameState().name());
        }

    }

    public void update() {
        // I change my mind using this update method
        // To delete and need to change in TicTacToe.java
        ;
    }

}
