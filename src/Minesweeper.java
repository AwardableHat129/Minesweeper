import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.event.MouseInputAdapter;

import java.util.Random;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;




public class Minesweeper extends JFrame{
    
    //El objeto que contiene el array con todos los botones y los métodos para manejarlos
    Minefield minefield;
    //Esta variable se activa si el jugador activa una mina y pierde el juego
    boolean gameOver = false;
    //La cantidad de minas que fueron marcadas correctamente con una bandera
    int flaggedMines = 0;
    //La cantidad de banderas que le quedan al jugador
    int remainingFlags = 0;

    
    
    public Minesweeper(int sideSize, int mineQuantity, int windowSize)
    {
        

        setSize(windowSize, windowSize);
        setTitle("Minesweeper");
        ImageIcon MinaLogo = new ImageIcon("src/Logo.png");
        setIconImage(MinaLogo.getImage());
                
        remainingFlags = mineQuantity;
        

        //Se añade la etiqueta que le mostrará al jugador las banderas que le quedan disponibles
        JLabel remainingFlagsLabel = new JLabel("Flags remaining: " + String.valueOf(remainingFlags));
        add(remainingFlagsLabel);
        remainingFlagsLabel.setBounds(10, 10, 150, 20);

        JLabel gameStatusLabel = new JLabel();
        add(gameStatusLabel);
        gameStatusLabel.setBounds(160, 10, 150, 20);

        //Botón para reiniciar el juego
        JButton resetButton = new JButton("Reset");
        add(resetButton);
        resetButton.setBounds(250, 10, 150, 20);

        ActionListener resetListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                minefield.reset();
                gameOver = false;
                gameStatusLabel.setText("");
                flaggedMines = 0;
                remainingFlags = mineQuantity;
                remainingFlagsLabel.setText("Flags remaining: " + String.valueOf(remainingFlags));
               
            }
        };

        resetButton.addActionListener(resetListener);



        minefield = new Minefield(sideSize, mineQuantity);

        for(int i = 0; i < sideSize; i++)
        {
            for(int j = 0; j < sideSize; j++)
            {
                MineButton button = minefield.getButton(i, j);
                add(button);
                button.setBounds((i * 32) + 20, (j * 32) + 60, 32, 32);

                ActionListener l = new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        MineButton b = (MineButton) event.getSource();
                        if(!b.isFlagged && !gameOver && !b.isClicked)
                        {
                            if(minefield.clickButton(b.x, b.y))
                            {
                                gameOver = true;
                                minefield.gameOverDisplay(b.x, b.y);
                                gameStatusLabel.setText("You lost!");

                            } 
                            else if(flaggedMines == mineQuantity && minefield.buttonsClicked == minefield.buttonsToClick)
                            {
                                gameOver = true;
                                gameStatusLabel.setText("You won!");
                            }
                        }                       
                    
                    }
                };

                button.addActionListener(l);

                MouseAdapter m = new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent event)
                    {
                        if(event.getButton() == 3)
                        {
                            MineButton b = (MineButton) event.getSource();

                            if(!b.isFlagged && !b.isClicked && !gameOver && remainingFlags > 0)
                            {
                                b.isFlagged = true;
                                b.flaggedButton();

                                remainingFlags--;
                                remainingFlagsLabel.setText("Flags remaining: " + String.valueOf(remainingFlags));
                                if(b.isMine == 1) flaggedMines++;

                                if(flaggedMines == mineQuantity && minefield.buttonsClicked == minefield.buttonsToClick)
                                {
                                    gameOver = true;
                                    gameStatusLabel.setText("You won!");
                                }

                            } 
                            else if(b.isFlagged && !b.isClicked && !gameOver)
                            {
                                b.isFlagged = false;
                                b.neutralButton();

                                remainingFlags++;
                                remainingFlagsLabel.setText("Flags remaining: " + String.valueOf(remainingFlags));
                                if(b.isMine == 1) flaggedMines--;
                            }

                        }
                    }

                };

                button.addMouseListener(m);
                
            }  
        }



        JLabel label1 = new JLabel();
        add(label1);

        

        

        initComponents();


    }


    private void initComponents()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);              
       
    }

    public static void main(String[] args) throws Exception{
        
        new Minesweeper(9, 15, 450).setVisible(true);

    }
    

}
