public class Graphics
{
    public static void drawIcon(String[] icon, int row, int col)
    {drawIcon(icon, row, col, false);}

    public static void drawIcon(String[] icon, int row, int col, boolean flipped)
    {
        for(int i = 0; i < icon.length; i++)
        {
            String toprint = "";
            if(!flipped)
                toprint = icon[i];
            else
            {
                // reverses string
                for(int ch = 0; ch < icon[i].length(); ch++)
                {
                    char c = icon[i].charAt(ch);

                    if(c == '\\')
                        c = '/';
                    else if(c == '/')
                        c = '\\';
                    else if(c == 'B')
                        c = 'E';
                    else if(c == '(')
                        c = ')';
                    else if(c == 'L')
                        c = '⅃';


                    toprint = c + toprint;
                }
            }
            Game.drawText(toprint, row+i, col);
        }
    }

    public static void drawSprites()
    {
        // this should probably run only once per player/enemy "cycle", it may be strange otherwise

        for(int i = 0; i < Game.enemies.size(); i++)
        {

            drawIcon(Game.enemies.get(i).getIcon(), 5, 2 + i*13, false);
        }

        for(int i = 0; i < Game.party.size(); i++)
        {
            drawIcon(Game.party.get(i).getIcon(), 16, 68 - i*13, true);
        }
    }
}