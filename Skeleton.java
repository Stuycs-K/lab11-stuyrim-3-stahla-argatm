public class Skeleton extends Adventurer {
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

    public String getSpecialName() {return "";}
    //accessor methods
    private int bones; // idk? thieves need to be brave to steal, i guess
    public int getSpecial() {return bones;}
    public void setSpecial(int n) {bones = n;}
    public int getSpecialMax() {return 10;}

    //hurt or hinder the target adventurer
    public String attack(Adventurer other)
    {
        other.applyDamage(3);
        return getName() + " attacked " + other.getName() + "!";
    }
    
    //heal or buff the target adventurer
    public String support(Adventurer other)
    {
        other.setHP(Math.min(other.getHP(), other.getmaxHP()));
        return getName() + " healed other by 2!";
    }

    //heal or buff self
    public String support()
    {
        setHP(Math.min(getHP() + 3, getmaxHP()));
        return getName() + " healed by 3!";
    }

    //hurt or hinder the target adventurer, consume some special resource
    public String specialAttack(Adventurer other)
    {
        if(getSpecial() >= getSpecialMax())
        {
            other.setHP(Math.min(other.getHP()+10, other.getmaxHP()));
            return getName() + " healed " + other.getName() + " by 10";
        }
        else
        {
            return getName() + " does not have enough bones!";
        }
    }
}
