import java.util.*;
public class Game
{
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static final int BORDER_COLOR = Text.BLACK;
    private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

    public static void main(String[] args)
    {
        run();
    }
    public static void allspaces() // prints 80x30 spaces. this is so we can jump around the terminal in drawBG.
    {
        String thingy = "";
        for(int i = 0; i < 20; i++)
        {
            thingy += "    ";
        }
        for(int i = 0; i < 30; i++)
        {
            System.out.println(thingy);
        }
    }


    //Display the borders of your screen that will not change.
    //Do not write over the blank areas where text will appear or parties will appear.
    public static void drawBackground()
    {


        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        // draw borders i guess?

        String vborder = "X";
        for(int i = 0; i < 39; i++)
        {
            vborder += "--";
        }
        vborder += "X";
        drawText(vborder, 1, 1);
        drawText(vborder, 4, 1); // row then column (y then x);
        drawText(vborder, 29, 1);
        drawText(vborder, 26, 1);

        for(int y = 2; y < 31; y++)
        {
            if(y == 4 | y == 26 | y == 29)
                continue;
            drawText("|", y, 1);
            drawText("|", y, 80);
        }

        // this is apparently not counting from 0, very weird

        // draw vertical borders

        Text.go(31, 0);
    }

    //Display a line of text starting at
    //(columns and rows start at 1 (not zero) in the terminal)
    //use this method in your other text drawing methods to make things simpler.
    public static void drawText(String s,int startRow, int startCol)
    {
        Text.go(startRow, startCol);
        System.out.print(s);

        // it cant be this simple, i hope this works
    }

    /*Use this method to place text on the screen at a particular location.
    *When the length of the text exceeds width, continue on the next line
    *for up to height lines.
    *All remaining locations in the text box should be written with spaces to
    *clear previously written text.
    *@param row the row to start the top left corner of the text box.
    *@param col the column to start the top left corner of the text box.
    *@param width the number of characters per row
    *@param height the number of rows
    */
    public static void TextBox(int row, int col, int width, int height, String text)
    {
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }




    //return a random adventurer, excludes skeleton / boss
    public static Adventurer createRandomAdventurer()
    {
        int type = (int)(Math.random() * 3);

        Adventurer guy;
        if(type == 2)
            guy = new Knight("Mr. Knight", 27);
        else if(type == 1)
            guy = new Healer("Healy Healerson", 19);
        guy = new Thief("Stealer dude", 17);
        return guy;
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow)
    {
        // if drawing 3, each one has (80-2)/3 = 26 char for themselves. (effectively 25 to look good)
        if(party.size() == 3)
        {
            int realsize = 25;

            for(int i = 0; i < 3; i++)
            {
                int startcol = 3 + 26*i;
                Adventurer thisone = party.get(i);
                drawText(fixedLength(thisone.getName(), "", 25), startRow, startCol);

                // is it a good idea to cram hp and special in one line? idk...
                String hpstuff = "HP: " + colorByPercent(thisone.getHP(), thisone.getMaxHP());
                String specstuff = thisone.getSpecialName() + ": " + getSpecial();
                drawText(fixedLength(hpstuff, specstuff, 25), startRow+1, startCol);
            }
        }
        
    }

    public static String fixedLength(String start, String end, int len)
    {
        int toadd = len - (start.length() + end.length());
        String addy = "";
        for(int i = 0; i < toadd; i++)
        {
            addy += " ";
        }
        return start + addy + end;
    }


    //Use this to create a colorized number string based on the % compared to the max value.
    public static String colorByPercent(int hp, int maxHP)
    {
        String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
        //COLORIZE THE OUTPUT IF HIGH/LOW:
        // under 25% : red
        // under 75% : yellow
        // otherwise : white
        return output;
    }





    //Display the party and enemies
    //Do not write over the blank areas where text will appear.
    //Place the cursor at the place where the user will by typing their input at the end of this method.
    ArrayList<Adventurer> enemies = new ArrayList<Adventurer>();
    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    public static void drawScreen()
    {
        drawBackground();

        //draw player party

        drawParty(party, 2);

        //draw enemy party

    }

    public static String userInput(Scanner in)
    {
        //Move cursor to prompt location

        //show cursor

        String input = in.nextLine();

        //clear the text that was written

        return input;
    }

    public static void quit()
    {
        Text.reset();
        Text.showCursor();
        Text.go(32,1);
    }

    public static void run()
    {
        //Clear and initialize
        Text.hideCursor();
        Text.clear();
        allspaces();
        drawBackground();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.


    // TODO: add chosen boss mode later or something
    // TODO: add random code for 1/2/3 enemies

    if(true) // if 3 enemy mode
    {
        for(int i = 0; i < 3; i++)
        {
            enemies.add(createRandomAdventurer());
        }
    }
    

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.

    if(true) // also assume 3 player characters
    {
        for(int i = 0; i < 3; i++)
        {
            party.add(createRandomAdventurer());
        }
    }


    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen();//initial state.

    //Main loop

    if(true)
        return; // <-- this just stops errors while i do everything above it

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.equals("attack ") || input.equals("a ")){
          //number after
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("special ") || input.equals("sp ")){
          //number after
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen();


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
