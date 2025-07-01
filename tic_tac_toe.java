import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class tic_tac_toe {

   
    static JFrame frame = new JFrame("Tic Tac Toe");
    static JLabel label = new JLabel();
    static JPanel panel = new JPanel();
    static JPanel boardJPanel = new JPanel();
    static JButton[][] Bboard = new JButton[3][3];

    // Player  variable
    static String PlayerX = "X";  // User
    static String player0 = "0";  // Computer
    static String CurrentPlayer = PlayerX;

 
    boolean gameOver = false;
    int turn = 0;

    public void BoardCreated() {
       
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        label.setBackground(Color.DARK_GRAY);

        
        label.setForeground(Color.WHITE);
        label.setText("Tic-Tac-Toe");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);

      
        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);

       
        boardJPanel.setLayout(new GridLayout(3, 3));
        boardJPanel.setBackground(Color.black);
        frame.add(boardJPanel);

     
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton jButton = new JButton();
                Bboard[r][c] = jButton;
                boardJPanel.add(jButton);
                jButton.setForeground(Color.BLACK);
                jButton.setFont(new Font("Arial", Font.BOLD, 40));
                jButton.setFocusable(false);
                jButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;

                        JButton clickedButton = (JButton) e.getSource();
                        if (clickedButton.getText().equals("")) {
                            clickedButton.setText(CurrentPlayer);
                            turn++;
                            CheckWinner();

                            if (!gameOver) {
                                CurrentPlayer = CurrentPlayer.equals(PlayerX) ? player0 : PlayerX;
                                if (CurrentPlayer.equals(player0)) {
                                    computerTime();
                                } else {
                                    label.setText(CurrentPlayer + " 'S turn");
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void computerTime() {
        if (gameOver) return;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (Bboard[r][c].getText().equals("")) {
                    Bboard[r][c].setText(CurrentPlayer);
                    turn++;
                    CheckWinner();
                    if (!gameOver) {
                        CurrentPlayer = PlayerX;
                        label.setText(CurrentPlayer + " 'S turn");
                    }
                    return;
                }
            }
        }
    }

    public void CheckWinner() {
        for (int r = 0; r < 3; r++) {
            if (Bboard[r][0].getText().equals("")) continue;

            if (Bboard[r][0].getText().equals(Bboard[r][1].getText()) &&
                Bboard[r][1].getText().equals(Bboard[r][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    winnerIs(Bboard[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (Bboard[0][c].getText().equals("")) continue;

            if (Bboard[0][c].getText().equals(Bboard[1][c].getText()) &&
                Bboard[1][c].getText().equals(Bboard[2][c].getText())) {
                for (int i = 0; i < 3; i++) {
                    winnerIs(Bboard[i][c]);
                }
                gameOver = true;
                return;
            }
        }

     
        if (Bboard[0][0].getText().equals(Bboard[1][1].getText()) &&
            Bboard[1][1].getText().equals(Bboard[2][2].getText()) &&
            !Bboard[0][0].getText().equals("")) {
            for (int i = 0; i < 3; i++) {
                winnerIs(Bboard[i][i]);
            }
            gameOver = true;
            return;
        }

        if (Bboard[0][2].getText().equals(Bboard[1][1].getText()) &&
            Bboard[1][1].getText().equals(Bboard[2][0].getText()) &&
            !Bboard[0][2].getText().equals("")) {
            winnerIs(Bboard[0][2]);
            winnerIs(Bboard[1][1]);
            winnerIs(Bboard[2][0]);
            gameOver = true;
            return;
        }

        if (turn == 9 && !gameOver) {
            label.setText("It's a Tie!");
            gameOver = true;
        }
    }

    public void winnerIs(JButton jButton) {
        jButton.setForeground(Color.BLUE);
        jButton.setBackground(Color.BLACK);
        label.setText(CurrentPlayer + " is Winner");
    }

    public static void main(String[] args) {
        tic_tac_toe t = new tic_tac_toe();
        t.BoardCreated();
    }
}
