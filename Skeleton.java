import java.util.ArrayList;
public class Skeleton extends Adventurer {
    public String[] icon = {
    "    _______   ",
    "   /       \\  ",
    "  |  o   o  | ",
    "  |    ^    | ",
    "  |   '-'   | ",
    "   \\_______/  ",
    "     | |      ",
    "   /  |  \\    ",
    "  /   |   \\   ",
    "  |   | |   |  "
};
    public String[] getIcon() {
        return icon;
    }
    public String type = "Skeleton";
    public String getType() {
        return type;
    }

    public Skeleton (String name)
    {
        super(name, 10);
        bones = 5;
    }
    public Skeleton (String name, int hp)
    {
        super(name, hp);
        bones = 5;
    }

    public String getSpecialName() {return "Calcium";}
    //accessor methods
    private int bones; // idk? thieves need to be brave to steal, i guess
    public int getSpecial() {return bones;}
    public void setSpecial(int n) {bones = n;}
    public int getSpecialMax() {return 10;}

    //hurt or hinder the target adventurer
    public String attack(Adventurer other)
    {
        other.applyDamage(3);
        return getName() + " rattled " + other.getName() + "'s bones!";
    }
    
    //heal or buff the target adventurer
    public String support(Adventurer other)
    {
        if(other == this)
            return support();
        other.setHP(Math.min(other.getHP()+2, other.getmaxHP()));
        return getName() + " made " + other.getName() +  "'s bones stronger!";
    }

    //heal or buff self
    public String support()
    {
        setHP(Math.min(getHP() + 3, getmaxHP()));
        return getName() + " reinforced their bones!";
    }

    public boolean specialParty()
    {
        return true;
    }
    public boolean specialTargeted()
    {
        return false;
    }
    //hurt or hinder the target adventurer, consume some special resource
    public String specialAttack(ArrayList<Adventurer> other) // not used
    {
        if(getSpecial() >= getSpecialMax())
        {
            setHP(10);
            return getName() + " repaired themselves!";
        }
        else
        {
            return support();
        }
    }
}
