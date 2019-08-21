import java.awt.*;
import javax.swing.JFrame;

public class Main {
    
    public static int WIDTH = 1400, HEIGHT = 1400;
    
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.setTitle("Tic-Tac-Toe");
        game.setSize(WIDTH, HEIGHT);
        game.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        game.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
    }
    
}
