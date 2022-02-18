import java.util.Random;

public class Minefield {
    
    public int sideSize = 9;
    public int mineQuantity = 15;
    Random rand = new Random();
    public int buttonsClicked = 0;
    public int buttonsToClick;

    MineButton minefield[][];

    public Minefield(int newSideSize, int newMineQuantity)
    {
        sideSize = newSideSize;
        mineQuantity = newMineQuantity;
        minefield = new MineButton[sideSize][sideSize];

        buttonsToClick = (sideSize * sideSize) - mineQuantity;

        //Se crean todos los botones del array y se llena
        for(int i = 0; i < sideSize; i++)
        {
            for(int j = 0; j < sideSize; j++)
            {
                minefield[i][j] = new MineButton(i, j);
            }
        }

                
        //Se asignan las minas de forma aleatoria
        randomizeMines();

    }

    public int getSideSize()
    {
        return sideSize;
    }

    public void reset()
    {
        for(int i = 0; i < sideSize; i++)
        {
            for(int j = 0; j < sideSize; j++)
            {
                minefield[i][j].reset();
            }
        }

        buttonsClicked = 0;

        randomizeMines();
        

    }

    public void randomizeMines()
    {
        int mineCounter = 0;

        while(mineCounter < mineQuantity)
        {
            for(int i = 0; i < sideSize; i++)
            {
                for(int j = 0; j < sideSize; j++)
                {
                    if(mineCounter < mineQuantity && (rand.nextInt() % 15 == 0) && minefield[i][j].isMine == 0)
                    {
                        minefield[i][j].isMine = 1;
                        mineCounter++;
                    } 
                }
            }
        }
    }

    public MineButton getButton(int x, int y)
    {
        return minefield[x][y];
    }

    public boolean clickButton(int x, int y)
    {
        minefield[x][y].isClicked = true;
        
        if(getButton(x, y).isMine == 1)
        {
            getButton(x, y).pressedMine();
            return true;
        }
        else
        {
            buttonsClicked++;
            
            int nearbyMines = getNearbyMines(x, y);

            switch(nearbyMines)
            {
                case 0: getButton(x, y).pressedZero(); break;
                case 1: getButton(x, y).pressedOne(); break;
                case 2: getButton(x, y).pressedTwo(); break;
                case 3: getButton(x, y).pressedThree(); break;
                case 4: getButton(x, y).pressedFour(); break;
                case 5: getButton(x, y).pressedFive(); break;
                case 6: getButton(x, y).pressedSix(); break;
                case 7: getButton(x, y).pressedSeven(); break;
                case 8: getButton(x, y).pressedEight(); break;
                default:                

            }

            return false;
        }
    }

    public int getNearbyMines(int x, int y)
    {
        int result = 0;
        boolean b1 = true, b2 = true, b3 = true, b4 = true, b5 = true, b6 = true, b7 = true, b8 = true;
        


        if(y == 0)
            {
                b1 = false;
                b4 = false;
                b6 = false;
            }

        if(y == sideSize - 1)
            {
                b3 = false;
                b5 = false;
                b8 = false;
            }

        if(x == 0)
            {
                b1 = false;
                b2 = false;
                b3 = false;
            }

        if(x == sideSize - 1)
            {
                b6 = false;
                b7 = false;
                b8 = false;
            }

            //Suma la cantidad de minas que hay en las casillas que lo rodean

            if(b1) result += minefield[x-1][y-1].isMine;
            if(b2) result += minefield[x-1][y].isMine;
            if(b3) result += minefield[x-1][y+1].isMine;
            if(b4) result += minefield[x][y-1].isMine;
            if(b5) result += minefield[x][y+1].isMine;   
            if(b6) result += minefield[x+1][y-1].isMine;
            if(b7) result += minefield[x+1][y].isMine;
            if(b8) result += minefield[x+1][y+1].isMine;

            if(result == 0)
            {
                                //En base a los booleanos de los límites del array, ejecuta la acción de hacer click en los botones adyacentes.

                if(b2)
                {
                    if(!minefield[x-1][y].isClicked && !minefield[x-1][y].isFlagged) clickButton(x - 1, y);
                }

                if(b4)
                {
                    if(!minefield[x][y-1].isClicked && !minefield[x][y-1].isFlagged) clickButton(x, y - 1);
                }

                if(b5)
                {
                    if(!minefield[x][y+1].isClicked && !minefield[x][y+1].isFlagged) clickButton(x, y + 1);
                }

                if(b7)
                {
                    if(!minefield[x+1][y].isClicked && !minefield[x+1][y].isFlagged) clickButton(x + 1, y);
                }
            }


        return result;
    }

    //Te muestra todas las minas cuando pierdes
    public void gameOverDisplay(int pressedX, int pressedY)
    {
        for(int i = 0; i < sideSize; i++)
        {
            for(int j = 0; j < sideSize; j++)
            {
                if(minefield[i][j].isMine == 1) minefield[i][j].neutralMine();
                else clickButton(i, j);
            }
        }

        minefield[pressedX][pressedY].pressedMine();
    }
}
