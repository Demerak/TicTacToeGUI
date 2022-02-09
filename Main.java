public class Main {

    public static void main(String[] args) {
        if (args.length == 2) {
            new TicTacToe(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } else if (args.length == 3) {
            new TicTacToe(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        } else {
            System.out.println("Please enter 2 or 3 arguments.");
            System.out.println("The first argument: # rows");
            System.out.println("The second argument: # columns");
            System.out.println("The third argument: # win");
        }

    }
}
