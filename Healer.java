public class Healer extends Adventurer {
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

    public String getSpecialName() {return "Heal";}
    //accessor methods
    private int mana; // idk? thieves need to be brave to steal, i guess
    public int getSpecial() {return mana;}
    public void setSpecial(int n) {mana = n;}
    public int getSpecialMax() {return 10;}

    //hurt or hinder the target adventurer
    public String attack(Adventurer other)
    {
        other.applyDamage(1);
        return getName() + " attacked " + other.getName() + "!";
    }
    
    //heal or buff the target adventurer
    public String support(Adventurer other)
    {
        other.setHP(Math.min(other.getHP()+3, other.getmaxHP()));
        return getName() + " healed " + other.getName() + " by 3";
    }

    //heal or buff self
    public String support()
    {
        setHP(Math.min(getHP() + 3, getmaxHP()));
        setSpecial(Math.min(getSpecial() + 7, getSpecialMax()));
        return getName() + " healed by 1 and became more daring!";
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
            return getName() + " does not have enough mana!";
        }
    }
}
