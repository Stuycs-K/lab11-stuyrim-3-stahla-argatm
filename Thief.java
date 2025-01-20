import java.util.ArrayList;
public class Thief extends Adventurer
{
public String[] icon = {
                "   ///\\    ",
                "  /o  o\\   ",
                " |   ^   |  ",
                " | \\_-_/ |  ",
                " |==| |==|  ",
                "  \\_|_|_/   ",
                "   /   \\    ",
                "  / / \\ \\   ",
                " /_/   \\_\\  ",
                "  L     L   "
            };                            
                            ;
    public String[] getIcon()
    {return icon;}

    public Thief (String name)
    {
        super(name, 18);
        boldness = 6;
    }
    public Thief (String name, int hp)
    {
        super(name, hp);
        boldness = 6;
    }
    public boolean specialTargeted()
    {
        return true;
    }
    public boolean specialParty()
    {
        return false;
    }
    public String getSpecialName() {return "Stealth";}
    //accessor methods
    private int boldness; // idk? thieves need to be brave to steal, i guess
    public int getSpecial() {return boldness;}
    public void setSpecial(int n) {boldness = n;}
    public int getSpecialMax() {return 10;}

    //hurt or hinder the target adventurer
    public String attack(Adventurer other)
    {
        other.applyDamage(3);
        setSpecial(Math.min(getSpecial() + 2, getSpecialMax()));
        return getName() + " stole " + other.getName() + "'s stuff!";
    }
    
    //heal or buff the target adventurer
    public String support(Adventurer other)
    {
        other.restoreSpecial(4);
        other.setHP(Math.min(other.getHP() + 2, other.getmaxHP()));
        return getName() + " healed " + other.getName() + " and restored their special!";
    }

    //heal or buff self
    public String support()
    {
        setHP(Math.min(getHP() + 2, getmaxHP()));
        setSpecial(Math.min(getSpecial() + 4, getSpecialMax()));
        return getName() + " retreated into the shadows!";
    }

    //hurt or hinder the target adventurer, consume some special resource
    public String specialAttack(ArrayList<Adventurer> other) // this should be an AL with 1 element, the target
    {
        Adventurer target = other.get(0);
        if(getSpecial() >= getSpecialMax())
        {
            // steals target's special and damages target, heals self
            setSpecial(0);
            restoreSpecial(target.getSpecial() + 2); 
            target.setSpecial(0);
            target.applyDamage(3);
            setHP(Math.min(getHP() + 2, getmaxHP()));
            return getName() + " plundered " + target.getName() + "!";
        }
        else
        {
            // attacks instead
            return getName() + " wussed out, instead " + attack(target).substring(getName().length()+1);
        }
    }
}
