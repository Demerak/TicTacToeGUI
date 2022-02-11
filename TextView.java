public class TextView implements View {

    private TicTacToeGame game;

    public TextView(TicTacToeGame game) {
        this.game = game;
    }

    public void update() {

        if (game.gameState == GameState.PLAYING) {
            System.out.printf("%s turn to play \n", game.nextCellValue().name());
        }
        System.out.println(game.toString());

    }

}
