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
    public String specialAttack(Adventurer other)
    {
        if(getSpecial() >= getSpecialMax())
        {
            // steals other's special and damages other, heals self
            setSpecial(0);
            restoreSpecial(other.getSpecial() + 2); 
            other.setSpecial(0);
            other.applyDamage(3);
            setHP(Math.min(getHP() + 2, getmaxHP()));
            return getName() + " plundered " + other.getName() + "!";
        }
        else
        {
            // attacks instead
            return getName() + " wussed out, instead " + attack(other);
        }
    }
}

