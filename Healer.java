import java.util.ArrayList;
public class Healer extends Adventurer {

    public String[] icon = {"X  Healer   X",
                            "            ",
                            "            ",
                            "            ",
                            "            ",
                            "            ",
                            "            ",
                            "            ",
                            "            ",
                            "X          X"};
    
    public Healer (String name)
    {
        super(name, 18);
        mana = 5;
    }
    public Healer (String name, int hp)
    {
        super(name, hp);
        mana = 5;
    }

    public boolean specialTargeted()
    {
        return false;
    }
    public boolean specialParty()
    {
        return true;
    }
    public String getSpecialName() {return "Mana";}
    //accessor methods
    private int mana; // idk? thieves need to be brave to steal, i guess
    public int getSpecial() {return mana;}
    public void setSpecial(int n) {mana = n;}
    public int getSpecialMax() {return 10;}

    //hurt or hinder the target adventurer
    public String attack(Adventurer other)
    {
        other.applyDamage(2);
        return getName() + " hit " + other.getName() + " with a staff!";
    }
    
    //heal or buff the target adventurer
    public String support(Adventurer other)
    {
        other.setHP(Math.min(other.getHP()+4, other.getmaxHP()));
        return getName() + " healed " + other.getName() + " by 4!";
    }

    //heal or buff self
    public String support()
    {
        setHP(Math.min(getHP() + 3, getmaxHP()));
        setSpecial(Math.min(getSpecial() + 3, getSpecialMax()));
        return getName() + " healed by 3 and gained mana!";
    }

    //hurt or hinder the target adventurer, consume some special resource
    public String specialAttack(ArrayList<Adventurer> other)
    {
        if(getSpecial() >= getSpecialMax())
        {
            setSpecial(0);
            for(int i = 0; i < other.size(); i++)
            {
                Adventurer toheal = other.get(i);
                toheal.setHP(Math.min(toheal.getHP()+5, toheal.getmaxHP()));
            }
            return getName() + " healed all party members by 5!";
        }
        else
        {
            return getName() + " doesn't have mana, instead " + support(other.get(0)).substring(getName().length()+1);
        }
    }
}
