import java.util.ArrayList;
public class Knight extends Adventurer
{
    public Knight (String name)
    {
        super(name, 18);
        chivalry = 5;
    }
    public Knight (String name, int hp)
    {
        super(name, hp);
        chivalry = 5;
    }

    public boolean specialTargeted()
    {
        return false;
    }
    public boolean specialParty()
    {
        return false;
    }
    public String getSpecialName() {return "Chivalry";}
    //accessor methods
    private int chivalry;
    public int getSpecial() {return chivalry;}
    public void setSpecial(int n) {chivalry = n;}
    public int getSpecialMax() {return 10;}

    //hurt or hinder the target adventurer
    public String attack(Adventurer other)
    {
        other.applyDamage(2);
        setSpecial(Math.min(getSpecial() + 1, getSpecialMax()));
        return getName() + " attacked " + other.getName() + "!";
    }
    
    //heal or buff the target adventurer
    public String support(Adventurer other)
    {
        other.restoreSpecial(2);
        other.setHP(Math.min(other.getHP() + 1, other.getmaxHP()));
        return getName() + " healed " + other.getName() + " and restored their special!";
    }

    //heal or buff self
    public String support()
    {
        setHP(Math.min(getHP() + 1, getmaxHP()));
        setSpecial(Math.min(getSpecial() + 1, getSpecialMax()));
        return getName() + " healed by 1 and became more daring!";
    }

    //hurt or hinder the target adventurer, consume some special resource
    public String specialAttack(ArrayList<Adventurer> other)
    {
        if(getSpecial() >= getSpecialMax())
        {
            // attacks all enemies
            setSpecial(0);
            for(int i = 0; i < other.size(); i++)
            {
                Adventurer tohit = other.get(i);
                attack(tohit);
            }
            return getName() + " attacked all enemies!";
        }
        else
        {
            // attacks instead
            return getName() + " was too weak, instead " + attack(other.get(0)).substring(getName().length()+1);
        }
    }
}

