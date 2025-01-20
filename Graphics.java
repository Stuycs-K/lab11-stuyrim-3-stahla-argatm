public class Graphics
{
    public static void drawIcon(String[] icon, int row, int col)
    {
        drawIcon(icon, row, col, false);
    }

    public static void drawIcon(String[] icon, int row, int col, boolean flipped)
    {
        for(int i = 0; i < icon.length; i++)
        {
            String toprint = "";
            if(!flipped)
            {
                toprint = icon[i];
            }
            else
            {
                for(int c = 0; c < icon[i].length(); c++)
                {
                    char currentChar = icon[i].charAt(c);
                    char flippedChar;
                    if (currentChar == '/')
                    {
                        flippedChar = '\\';
                    }
                    else if (currentChar == 'L') 
                    {
                        flippedChar = '⅃';
                    }
                    else if (currentChar == '\\')
                    {
                        flippedChar = '/';
                    }
                    else if (currentChar == 'L')
                    {
                        flippedChar = '⅃';
                    }
                    else if (currentChar == 'B')
                    {
                        flippedChar = 'E';
                    }
                    else
                    {
                        flippedChar = currentChar;
                    }

                    toprint = flippedChar + toprint;
                }
            }
            Game.drawText(toprint, row + i, col);
        }
    }

    public static void drawSprites()
    {
        // Drawing enemies, no flip
        for(int i = 0; i < Game.enemies.size(); i++)
        {
            System.out.print("\033[31m");
            drawIcon(Game.enemies.get(i).getIcon(), 5, 2 + i * 13, false);
            System.out.print("\033[0m");
        }

        // Drawing party, with flip
        for(int i = 0; i < Game.party.size(); i++)
        {
            System.out.print("\033[32m");
            drawIcon(Game.party.get(i).getIcon(), 16, 68 - i * 13, true);
            System.out.print("\033[0m");
        }
    }
}

