package tictoktac;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tictoktac {

    int boardwidth = 600;
    int boardheight = 650;

    JFrame frame = new JFrame("Tic - Tac - Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameover = false;
    int turns = 0;




        Tictoktac()
        {
            frame.setVisible(true);
            frame.setSize(boardwidth, boardheight);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());


            textLabel.setBackground(Color.darkGray);
            textLabel.setForeground(Color.white);
            textLabel.setFont(new Font("Arial",Font.BOLD,50));
            textLabel.setHorizontalAlignment(JLabel.CENTER);
            textLabel.setText("TIC - TAC - TOE");
            textLabel.setOpaque(true);

            textPanel.setLayout(new BorderLayout());
            textPanel.add(textLabel);
            frame.add(textPanel , BorderLayout.NORTH);


            boardPanel.setLayout(new GridLayout(3,3));
            boardPanel.setBackground(Color.darkGray);
            frame.add(boardPanel);


            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    JButton tile = new JButton();
                    board[r][c] = tile;
                    boardPanel.add(tile);

                    tile.setBackground(Color.darkGray);
                    tile.setForeground(Color.white);
                    tile.setFont(new Font("Arial",Font.BOLD,120));
                    tile.setFocusable(false);


                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        if(gameover) return;
                           JButton tile = (JButton) e.getSource();

                           if(tile.getText() == "")
                           {
                                tile.setText(currentPlayer);
                                turns++;
                                checkWinner();

                                if (!gameover) {
                                   currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                   textLabel.setText(currentPlayer + "'s turn.");
                               }

                           }
                    }
                });
                }
            }

        }


    public static void main(String[] args) {
        Tictoktac tk = new Tictoktac();

    }


    void checkWinner()
    {
        // Horizonat check
        for(int r=0 ; r < 3 ;r++)
        {
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() && 
               board[r][1].getText() == board[r][2].getText())
            {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameover = true;
                return;

            }
        }


        // vertical check
        for(int c=0 ; c < 3 ;c++)
        {
            if(board[0][c].getText() == "") continue;

            if(board[0][c].getText() == board[1][c].getText() && 
               board[1][c].getText() == board[2][c].getText())
            {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameover = true;
                return;

            }
        }



    //Diagonally
    if(board[0][0].getText() == board[1][1].getText() && 
           board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "")
    {
        for (int i = 0; i < 3; i++) {
            setWinner(board[i][i]);
        }
        gameover = true;
        return;

    }
    
    
    
    //anti - Diagonally
    if(board[0][2].getText() == board[1][1].getText() && 
           board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "")
    {
        setWinner(board[0][2]);
        setWinner(board[1][1]);
        setWinner(board[2][0]);
        
        
        gameover = true;
        return;

    }
    
    
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
                
            }
            
            gameover = true;
        }


    }

     void setWinner(JButton tile) {
       tile.setBackground(Color.gray);
       tile.setForeground(Color.green);
       textLabel.setText(currentPlayer + " is the Winner !");
    }

    private void setTie(JButton tile) {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.orange);
        textLabel.setText("Tie !");
    }

}
