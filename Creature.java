
import java.util.*;
import java.util.Date;
//This part includes Data Members and Method Members
public class Creature {  
    private static final int FOOD2HEALTH = 6;//static constant
    private static final int HEALTH2POWER = 4;//static constant
    private static final Random random = new Random();
    private static int numStillAlive = 0;
    private String name;
    private int foodUnits;
    private int healthUnits;
    private int firePowerUnits;
    private Date dateCreated;
    private Date dateDied;

    public Creature(String name) { 
        this.name=name;
        
        numStillAlive += 1;
        foodUnits = random.nextInt(12) + 1;
        healthUnits = random.nextInt(7) + 1;
        firePowerUnits = random.nextInt(11);
        dateCreated = new Date();
        dateDied = null;
        this.normalizeUnits();
    }
    public Creature() {// for using constructor without adding the array of creatures
        
    }
   
       
    public void setName(String name) {
        this.name = name;
    }

    public void setHealthUnits(int healthUnits) {
        this.healthUnits = healthUnits;
    }

    public void setFoodUnits(int foodUnits) {
        this.foodUnits = foodUnits;
    }

    public void reduceFirePowerUnits(int num) {
        firePowerUnits -= num;
    }

    public int getNumStillAlive() {
        return numStillAlive;
    }

    public String getName() {
        return name;
    }

    public int getFoodUnits() {
        return foodUnits;
    }

    public int getHealthUnits() {
        return healthUnits;
    }

    public int getFirePowerUnits() {
        return firePowerUnits;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateDied() {
        return dateDied;
    }

    public boolean isAlive() {
        if(dateDied==null){
            return true;}
        else{
            return false;}  
    }

    public int earnFood() {
        int AddFood= random.nextInt(16);
        foodUnits += AddFood;
        normalizeUnits();
        return AddFood;
    }

    public void attacking(Creature player) {
        int foodUnitsVal = player.foodUnits / 2;
        int healthUnitsVal = player.healthUnits / 2;
        this.foodUnits += foodUnitsVal;
        this.healthUnits += healthUnitsVal;
        player.foodUnits -= foodUnitsVal;
        player.healthUnits -= healthUnitsVal;
        this.firePowerUnits -= 2;
        this.normalizeUnits();
        player.normalizeUnits();
        
    }

    public boolean healthFoodUnitsZero() {
        if (healthUnits <= 0 && foodUnits <= 0) {
                    if (dateDied == null) {
                        died();           }  
            return true;
            }
        else
        return false;
    }

    private void died() {
        dateDied = new Date();
        numStillAlive-=1;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", foodUnits=" + foodUnits +
                ", healthUnits=" + healthUnits +
                ", firePowerUnits=" + firePowerUnits +
                ", dateCreated=" + dateCreated +
                ", dateDied=" + dateDied +
                '}';
    }

    public void printshow() {// to use a method for print the current status
         System.out.println("Food units     Health units      Fire power units       Name" );
         System.out.println("----------     ------------      ----------------       ----" );
         System.out.println(this.foodUnits      + "              " +
                            this.healthUnits    + "                 " +
                            this.firePowerUnits + "                      " + this.name) ;
         System.out.println("Date Create: "+ this.dateCreated);
         if (this.dateDied == null) {
             System.out.println("Date died: is still alive");}
         else{
             System.out.println("Date died: "+ this.dateDied);}
         
    
    }
    
    
    public void PossibleChoices() {//to use a method for printing the switch choices
         System.out.println("       1. How many creatures are alive?" );
         System.out.println("       2. See my status " );
         System.out.println("       3. See status of all players " );
         System.out.println("       4. Change my name" );
         System.out.println("       5. Work for some food " );
         System.out.println("       6. Attack another creature (Warning! may turn against you)" );
         System.out.print("Your Choice please > " );
    }
    
    public String showStatus() {
        return String.format("%d food units, %d health units, %d fire power units", foodUnits, healthUnits, firePowerUnits);
    }

    private void normalizeUnits() {
        if (foodUnits<2 && healthUnits <4 ){//the condition to kill creature
            healthUnits=0;
            foodUnits=0;
            healthFoodUnitsZero();}
        
        else{
        healthUnits += foodUnits / FOOD2HEALTH;//6
        foodUnits %= FOOD2HEALTH;

            if (healthUnits >= 4) {
                // 4 healthUnits = 1 firePowerUnit
                if (healthUnits % 4 == 0) {
                    firePowerUnits += (healthUnits - HEALTH2POWER) / 4;//4
                    healthUnits = 4;
                } else {
                    firePowerUnits += (healthUnits - HEALTH2POWER) / 4;//4
                    healthUnits = 4 + healthUnits%4;
                }

            }
        }
    }

          
}

