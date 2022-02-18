import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MineButton extends JButton{
    
    public boolean isClicked = false;
    public boolean isFlagged = false;

    public int isMine = 0;
    public int x;
    public int y;

    Icon defaultIcon = new ImageIcon("src/NeutralButton.png");

    public MineButton(int x, int y)
    {
        super();

        this.x = x;
        this.y = y;
        this.setIcon(defaultIcon);
        

    }
   
    
    public void pressedMine()
    {
        Icon pressedMineIcon = new ImageIcon("src/PressedMine.png");
        setIcon(pressedMineIcon);
    }

    public void neutralMine()
    {
        Icon icon = new ImageIcon("src/NeutralMine.png");
        setIcon(icon);
    }

    public void pressedZero()
    {
        Icon icon = new ImageIcon("src/PressedZero.png");
        setIcon(icon);
    }

    public void pressedOne()
    {
        Icon icon = new ImageIcon("src/PressedOne.png");
        setIcon(icon);
    }

    public void pressedTwo()
    {
        Icon icon = new ImageIcon("src/PressedTwo.png");
        setIcon(icon);
    }

    public void pressedThree()
    {
        Icon icon = new ImageIcon("src/PressedThree.png");
        setIcon(icon);
    }

    public void pressedFour()
    {
        Icon icon = new ImageIcon("src/PressedFour.png");
        setIcon(icon);
    }

    public void pressedFive()
    {
        Icon icon = new ImageIcon("src/PressedFive.png");
        setIcon(icon);
    }

    public void pressedSix()
    {
        Icon icon = new ImageIcon("src/PressedSix.png");
        setIcon(icon);
    }

    public void pressedSeven()
    {
        Icon icon = new ImageIcon("src/PressedSeven.png");
        setIcon(icon);
    }

    public void pressedEight()
    {
        Icon icon = new ImageIcon("src/PressedEight.png");
        setIcon(icon);
    }

    public void neutralButton()
    {
        Icon icon = new ImageIcon("src/NeutralButton.png");
        setIcon(icon);
    }

    public void flaggedButton()
    {
        Icon icon = new ImageIcon("src/FlaggedButton.png");
        setIcon(icon);
    }

    public void reset()
    {
        isClicked = false;
        isFlagged = false;
        isMine = 0;
        neutralButton();
    }

}
