import java.util.*;
public class Game
{
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static final int BORDER_COLOR = Text.BLACK;
    private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

    private static Scanner in;
    public static void main(String[] args)
    {
        int mode = -1;
        System.out.println("Which mode would you like to play? (Easy) / (Med)ium / (Hard) Boss Battle");
        in = new Scanner(System.in);
        while(mode == -1)
        {
            String input = in.nextLine();
            if(input.toLowerCase().equals("easy"))
                mode = 0;
            if(input.toLowerCase().equals("med"))
                mode = 1;
            if(input.toLowerCase().equals("hard"))
                mode = 2;
        }
        run(mode);
    }
    public static void allspaces() // prints 80x30 spaces. this is so we can jump around the terminal in drawBG.
    {
        String thingy = "";
        for(int i = 0; i < 20; i++)
        {
            thingy += "    ";
        }
        for(int i = 1; i < 31; i++)
        {
            drawText(thingy, i, 1);
        }
    }

    //Display the borders of your screen that will not change.
    //Do not write over the blank areas where text will appear or parties will appear.
    public static void drawBackground()
    {
        //allspaces();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        // draw borders i guess?

        String vborder = "┏";
        for(int i = 0; i < 39; i++)
        {
            vborder += "━━";
        }
        vborder += "┓";
        drawText(vborder, 1, 1);
        vborder = "┣";
        for(int i = 0; i < 39; i++)
        {
            vborder += "━━";
        }
        vborder += "┫ ";
        drawText(vborder, 4, 1); // row then column (y then x);
        drawText(vborder, 29, 1);
        drawText(vborder, 26, 1);

        for(int y = 2; y < 31; y++)
        {
            if(y == 4 | y == 26 | y == 29)
                continue;
            drawText("┃", y, 1);
            drawText("┃", y, 80);
        }

        // this is apparently not counting from 0, very weird

        // draw vertical borders

        Text.go(31, 1);
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
        //TODO: MAKE SURE NONE OF THESE NAMES ARE OVER 13 CHARACTERS OR OVERFLOW ISSUE
        String[] knames = {"Alexander", "Richard", "Arthur", "Edward", "Henry 8th", "Joan of Arc", "Caesar"};
        String[] hnames = {"Clara Barton", "Walt Whitman", "Robert Liston", "Florence", "Asclepius", "Hippocrates", "Cornelius"};
        String[] tnames = {"Ma Barker", "Hubert", "Liam", "Robin Hood", "Jesse James", "Al Capone", "Humpfrey"};

        int type = (int)(Math.random() * 3);
        int name = (int)(Math.random() * 7);
        Adventurer guy;
        if(type == 2)
            guy = new Knight(knames[name], 27);
        else if(type == 1)
            guy = new Healer(hnames[name], 19);
        else
            guy = new Thief(tnames[name], 17);
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
    public static void drawParty(ArrayList<Adventurer> party,int startRow, String color)
    {
        // if drawing 3, each one has (80-2)/3 = 26 char for themselves. (effectively 25 to look good)
        System.out.print(color);
        if(party.size() == 3)
        {
            int realsize = 25;

            for(int i = 0; i < 3; i++)
            {
                
                int startcol = 3 + 26*i;
                Adventurer thisone = party.get(i);

                if(i != 0) {
                    drawText(fixedLength(thisone.getName(), thisone.getType() + "(" + (i+1) + ") ", 24), startRow, startcol+1);
                } else {
                    drawText(fixedLength(thisone.getName(), thisone.getType() + "(" + (i+1) + ") ", 25), startRow, startcol);
                }

                // is it a good idea to cram hp and special in one line? idk...
                String hpstuff = "";
                String specstuff = "";
                if(i != 0) // makes it have a border of 1 space, gotta do this cause screen doesn't cleanly divide
                {
                    hpstuff = " ";
                    specstuff = " ";
                }
                String cbp = String.format("%2s", thisone.getHP()+"")+"/"+String.format("%2s", thisone.getmaxHP()+"");
                hpstuff += "HP: " + cbp;


                specstuff += thisone.getSpecialName() + ": " + thisone.getSpecial() + " ";

                drawText(fixedLength(hpstuff, specstuff, 25, colorByPercent(thisone.getHP(), thisone.getmaxHP()), -1), startRow+1, startcol);
                System.out.print(color);
            }
            drawText("│", startRow, 28);
            drawText("│", startRow+1, 28);           
            drawText("│", startRow, 54);
            drawText("│", startRow+1, 54);
        }
        else if(party.size() == 2)
        {
            drawText("│", startRow, 41);
            drawText("│", startRow+1, 41);           

            for(int i = 0; i < 2; i++)
            {
                Adventurer thisone = party.get(i);
                int startcol = 3 + 40*i;
                int availablewidth = 39-i-2;
                drawText(fixedLength(thisone.getName(), "(" + (i+1) + ")", availablewidth), startRow, startcol);
                String hpstuff = "HP: " + String.format("%2s", thisone.getHP()+"")+"/"+String.format("%2s", thisone.getmaxHP()+"");
                String specstuff = thisone.getSpecialName() + ": " + thisone.getSpecial();
                drawText(fixedLength(hpstuff, specstuff, availablewidth), startRow+1, startcol);
            }
        }
        else if(party.size() == 1)
        {
            Adventurer thisone = party.get(0);
            drawText(fixedLength(thisone.getName(), "(1)", 76), startRow, 3);
            String hpstuff = "HP: " + String.format("%2s", thisone.getHP()+"")+"/"+String.format("%2s", thisone.getmaxHP()+"");
            String specstuff = thisone.getSpecialName() + ": " + thisone.getSpecial();
            drawText(fixedLength(hpstuff, specstuff, 76), startRow+1, 3);
        }
        else
        {
            drawText(fixedLength("", "", 76), startRow, 3);
            drawText(fixedLength("", "", 76), startRow+1, 3);
        }
        System.out.print("\033[0m");
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

    public static String fixedLength(String start, String end, int len, int startColor, int endColor) // if color = -1, don't
    {
        int toadd = len - (start.length() + end.length());
        String addy = "";
        for(int i = 0; i < toadd; i++)
        {
            addy += " ";
        }
        if(startColor != -1)
            start = Text.colorize(start, startColor);
        if(endColor != -1)
            end = Text.colorize(end, endColor);
        return start + addy + end;
    }

    //Use this to create a colorized number string based on the % compared to the max value.

    // method changed, it returns the int color the text should be instead
    public static int colorByPercent(int hp, int maxHP)
    {
        //COLORIZE THE OUTPUT IF HIGH/LOW:
        // under 25% : red
        if(((double)hp) / maxHP <= 0.25)
            return Text.RED;
        // under 75% : yellow
        else if(((double)hp) / maxHP <= 0.75)
            return Text.YELLOW;
        // otherwise : white
        return -1;
    }





    //Display the party and enemies
    //Do not write over the blank areas where text will appear.
    //Place the cursor at the place where the user will by typing their input at the end of this method.
    static ArrayList<Adventurer> enemies = new ArrayList<Adventurer>();
    static ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    public static void drawScreen()
    {
        Text.clear();
        drawBackground();

        //draw player party

        checkDead();

        drawParty(party, 27, "\033[32m");

        drawParty(enemies, 2, "\033[31m");

        Text.go(30, 2);

    }

    public static String userInput(Scanner in)
    {
        //Move cursor to prompt location
        Text.go(30, 2);
        //show cursor
        //Text.hideCursor();

        String input = in.nextLine();

        //clear the text that was written
        drawText(fixedLength("", "", 78), 30, 2);

        return input;
    }

    public static void quit()
    {
        Text.reset();
        Text.showCursor();
        Text.go(32,1);
    }

    public static void run(int mode)
    {
        //Clear and initialize
        Text.hideCursor();
        Text.clear();
        allspaces();
        //drawBackground();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.


    // TODO: add chosen boss mode later or something
    // TODO: add random code for 1/2/3 enemies

    if(mode != 2)
    {
        for(int i = 0; i < ((mode == 0) ? 2 : 3); i++)
        {
            enemies.add(createRandomAdventurer());
        }
    }
    else
    {
        String[] snames = {"Jack", "Cleopatra", "Albert", "BONES-4000", "Van Bone"};
        enemies.add(new Boss("Skeleton King", 50));
        enemies.add(new Skeleton(snames[(int)(Math.random()*5)], 10));
        enemies.add(new Skeleton(snames[(int)(Math.random()*5)], 10));
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
    //Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen();//initial state.
    Graphics.drawSprites();
    //Main loop

    //display this prompt at the start of the game.
    String preprompt = party.get(whichPlayer) + "'s turn: choose (a)ttack/(sp)ecial/(su)pport/(q)uit + no. of target";

    drawText(preprompt, 31, 1);

    while(!(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")))
    {
        // win / lose condition?

        if(enemies.size() == 0)
        {
            // Winning condition
            String todraw = "Congratulations! You won the battle!";
            drawText(fixedLength(todraw, "[Enter]", 80), 31, 1);
            userInput(in);
            break;
        }
        else if(party.size() == 0)
        {
            // Lose condition
            String todraw = "Unfortunately, you lost. You should try again!";
            drawText(fixedLength(todraw, "[Enter]", 80), 31, 1);
            userInput(in);
            break;
        }

        input = userInput(in);

        //display event based on last turn's input
        if(partyTurn)
        {
            boolean validinput = false;
            //Process user input for the last Adventurer:
            if(input.startsWith("attack") || input.startsWith("a"))
            {
                String[] split = input.split(" ");
                if(split.length == 2)
                {
                    int whichenemy = Integer.parseInt(split[1]);
                    if(whichenemy > 0 & whichenemy <= enemies.size())
                    {
                        validinput = true;
                        String todraw = party.get(whichPlayer).attack(enemies.get(whichenemy-1));
                        drawScreen(); // update healths
                        drawText(fixedLength(todraw, "[Enter]", 80), 31, 1);
                        userInput(in); // lets user press enter
                    }
                }

                //drawText(fixedLength(party.get(whichPlayer) + " attacks someone idk", "[Enter]", 78), 31, 1);

            }
            else if(input.startsWith("special") || input.startsWith("sp"))
            {
                boolean targeted = party.get(whichPlayer).specialTargeted();
                if(targeted) // needs number also
                {
                    String[] split = input.split(" ");
                    if(split.length == 2)
                    {
                        // I don't have to handle the case of a targeted special on player party, it'll never happen here
                        int whichenemy = Integer.parseInt(split[1]);
                        if(whichenemy > 0 & whichenemy <= enemies.size())
                        {
                            validinput = true;
                            // actually do special
                            ArrayList<Adventurer> al = new ArrayList<Adventurer>();
                            al.add(enemies.get(whichenemy-1));
                            String todraw = party.get(whichPlayer).specialAttack(al);
                            drawScreen(); // update healths
                            drawText(fixedLength(todraw, "[Enter]", 80), 31, 1);
                            userInput(in); // lets user press enter
                        }
                    }
                }
                else // just needs special / sp
                {
                    validinput = true;
                    ArrayList<Adventurer> al;
                    if(party.get(whichPlayer).specialParty())
                        al = party;
                    else
                        al = enemies;
                    String todraw = party.get(whichPlayer).specialAttack(al);
                    drawScreen(); // update healths
                    drawText(fixedLength(todraw, "[Enter]", 80), 31, 1);
                    userInput(in); // lets user press enter
                }
            }
            else if(input.startsWith("support") || input.startsWith("su"))
            {
                 String[] split = input.split(" ");
                 if(split.length == 2)
                 {
                     int whichparty = Integer.parseInt(split[1]);
                     if(whichparty > 0 & whichparty <= party.size())
                     {
                         validinput = true;
                         String todraw = party.get(whichPlayer).support(party.get(whichparty-1));
                         drawScreen(); // update healths
                         drawText(fixedLength(todraw, "[Enter]", 80), 31, 1);
                         userInput(in); // lets user press enter
                     }
                 }
            }
            if(validinput)
                whichPlayer++;

            if(whichPlayer < party.size())
            {
                //This is a player turn.
                //Decide where to draw the following prompt:
                String prompt;
                if(validinput)
                    prompt = party.get(whichPlayer) + "'s turn: choose (a)ttack/(sp)ecial/(su)pport/(q)uit + no. of target";
                else
                    prompt = "INVALID INPUT! Choose (a)ttack/(sp)ecial/(su)pport/(q)uit + no. of target";

                drawText(fixedLength(prompt, "", 80), 31, 1);
            }
            else
            {
                //This is after the player's turn, and allows the user to see the enemy turn
                //Decide where to draw the following prompt:
                String prompt = "Press [ENTER] to see opponent's turn";
                drawText(fixedLength(prompt, "", 80), 31, 1);
                partyTurn = false;
                whichOpponent = 0;
            }
            //done with one party member
        }
        else
        {
            Adventurer thisOppo = enemies.get(whichOpponent);
            //not the party turn!

            // Randomly chosen move
            int choicebound = 2;
            if(thisOppo.getSpecial() >= thisOppo.getSpecialMax()) // only tries special if it has enough resource
                choicebound = 3;

            //int choice = (int)(Math.random() * choicebound);
            int choice = 0;
            if(choice == 1)
            {}
            else if(choice == 2)
            {}
            else
            {
                // attack
                int targetchoice = (int)(Math.random() * party.size());
                Adventurer target = party.get(targetchoice);
                String todraw = thisOppo.attack(target);
                drawScreen(); // update healths
                drawText(fixedLength(todraw, "[Enter]", 80), 31, 1);
                //userInput(in); // lets user press enter
            }

            //Decide where to draw the following prompt:
            //String prompt = "press enter to see next turn";

            whichOpponent++;

        } //end of one enemy.

        //modify this if statement.
        if(!partyTurn && whichOpponent >= enemies.size())
        {
            //THIS BLOCK IS TO END THE ENEMY TURN
            //It only triggers after the last enemy goes.
            whichPlayer = 0;
            turn++;
            partyTurn=true;
            userInput(in);
            //display this prompt before player's turn
            String prompt = party.get(whichPlayer) + "'s turn: choose (a)ttack/(sp)ecial/(su)pport/(q)uit + no. of target";
            drawText(fixedLength(prompt, "", 80), 31, 1);
            
        }

        //display the updated screen after input has been processed.
        //drawScreen();


    }//end of main game loop


    //After quit reset things:
    quit();
    }

    public static void checkDead()
    {
        for(int e = enemies.size()-1; e >= 0; e--)
        {
            if(enemies.get(e).getHP() <= 0)
                enemies.remove(e);
        }
        for(int p = party.size()-1; p >= 0; p--)
        {
            if(party.get(p).getHP() <= 0)
                party.remove(p);
        }
    }
}
