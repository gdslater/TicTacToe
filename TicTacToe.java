import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


// A game of tic tac toe. Can be played two player,
// or vs the CPU. CPU is on easy or impossible mode.
// Uses graphics to enhance gameplay experience.


public class TicTacToe extends JFrame {
    
    private char currentTurn = 'x';
    private boolean gameOver = false;
    private Square[][] squares = new Square[3][3];
    private JLabel status = new JLabel(Character.toUpperCase(currentTurn) + "'s Turn!", SwingConstants.CENTER);
    
    public TicTacToe() {
        status.setFont(new Font("Ariel",1,64));
        JPanel p = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                p.add(squares[i][j] = new Square());
            }
        }
        p.setBorder(new LineBorder(Color.red, 10));
        status.setBorder(new LineBorder(Color.blue, 10));
        add(p, BorderLayout.CENTER);
        add(status, BorderLayout.NORTH);
    }
    
    public boolean isFull() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j].getType() == ' ') {
                  return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkForWin() {
        // Check Horizontal
        for (int i = 0; i < squares.length; i++) {
            if (squares[i][0].getType() == currentTurn
                    && squares[i][1].getType() == currentTurn
                    && squares[i][2].getType() == currentTurn) { 
                return true;
            }
        }
        // Check Vertical
        for (int i = 0; i < squares.length; i++) {
            if (squares[0][i].getType() == currentTurn
                    && squares[1][i].getType() == currentTurn
                    && squares[2][i].getType() == currentTurn) { 
                return true;
            }
        }
        // Check Diagonal
        if (squares[0][2].getType() == currentTurn
                && squares[1][1].getType() == currentTurn
                &squares[2][0].getType() == currentTurn) {
            return true;
        }
        if (squares[0][0].getType() == currentTurn
                && squares[1][1].getType() == currentTurn
                && squares[2][2].getType() == currentTurn) {
            return true;
        }
        return false;
    }
    
    public void reset() {
        System.out.println("reset");
    }
        
    
    
    // Squares represent each possible move
    // on a tic tac toe board.
    public class Square extends JPanel{
        
        // represents X, O, or Empty.
        private char type = ' ';
        
        public Square() {
            setBorder(new LineBorder(Color.black, 2));
            addMouseListener(new EventListener());
        }
        
        public void set(char c) {
            type = c;
            repaint();
        }
        
        public char getType() {
            return type;
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (type == 'x') {
                g.setColor(Color.red);
                g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
                g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
            } else if (type == 'o') {
                g.setColor(Color.BLUE);
                g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
            } 
        }
        
        
        private class EventListener extends MouseAdapter {
            
            public void mouseClicked(MouseEvent e) {
                boolean added = false;
                if (!gameOver && type == ' ') {
                    set(currentTurn);
                    added = true;
                }
                if (checkForWin()) {
                    status.setText(Character.toUpperCase(currentTurn) + " is the winner!");
                    gameOver = true;
                } else if (isFull()) {
                    status.setText("It's a tie!");
                    gameOver = true;
                }
                if (added && !gameOver) {
                    if (currentTurn == 'x') {
                        currentTurn = 'o';
                    } else {
                        currentTurn = 'x';
                    }
                    status.setText(Character.toUpperCase(currentTurn) + "'s Turn!");
                }
            }
            
        }   
    }
}