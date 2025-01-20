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
                for(int c = 0; c < icon[i].length; c++)
                {
                    toprint = c + toprint;
                }
            }
            Game.drawText(toprint, row+i, col);
        }
    }
}