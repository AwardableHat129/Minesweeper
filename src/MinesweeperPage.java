import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperPage extends JFrame {

    ImageIcon MinaLogo = new ImageIcon("src/Logo.png");
    JButton Opcion1 = new JButton("Facil");
    JButton Opcion2 = new JButton("Media");
    JButton Opcion3 = new JButton("Dificil");
    JLabel Seleccion = new JLabel("Elige la dificultad del juego: ");

    public MinesweeperPage() {

        setTitle("MineSweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(MinaLogo.getImage());
        setSize(350,350);
        JLabel fondopantalla = new JLabel(new ImageIcon("src/Fondo.png"));
        add(fondopantalla);

        setLocationRelativeTo(null);
        Seleccion.setFont(new Font("Agency FB",Font.BOLD,32));
        
        fondopantalla.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        fondopantalla.add(Seleccion,gbc);
        fondopantalla.add(new JLabel("  "),gbc);
        fondopantalla.add(Opcion1,gbc);
        fondopantalla.add(new JLabel("  "),gbc);
        fondopantalla.add(Opcion2,gbc);
        fondopantalla.add(new JLabel("  "),gbc);
        fondopantalla.add(Opcion3,gbc);

        Opcion1.setBounds(250,80,160,100);
        Opcion2.setBounds(125,40,85,50);
        Opcion3.setBounds(125,40,85,50);

        ActionListener Mapa = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if (event.getSource() == Opcion1){

                    setVisible(false);
                    new Minesweeper(8,10,450).setVisible(true);

                } else if (event.getSource() == Opcion2){

                    setVisible(false);
                    new Minesweeper(16,40,700).setVisible(true);

                } else if (event.getSource() == Opcion3){

                    setVisible(false);
                    new Minesweeper(22,90,900).setVisible(true);

                }
               
            }
        };

        Opcion1.addActionListener(Mapa);
        Opcion2.addActionListener(Mapa);
        Opcion3.addActionListener(Mapa);

        //add(Opcion1);
        //add(Opcion2);
        //add(Opcion3);
        

    }

    
    public static void main(String[] args) {

        new MinesweeperPage().setVisible(true);
        
    }
}
