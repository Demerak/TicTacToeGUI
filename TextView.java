public class TextView implements View {

    private TicTacToeGame game;

    public TextView(TicTacToeGame game) {
        this.game = game;
    }

    public void update() {
        System.out.println(game.toString());
    }

}
