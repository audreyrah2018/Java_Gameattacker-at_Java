

import java.util.*;// iport important libraries
import java.util.Date;
import java.util.Random;

public class Battle_Game {
    public static void main(String[] args) {
    
//page 4 question 1,2 part  I, of assignment 4   
        System.out.print("[------------------------------------------------]\n"); //welcome message
        System.out.print("[ Welcome to the Battle Game ]\n"); 
        System.out.println("[------------------------------------------------]  ");
        
        Scanner input = new Scanner (System.in);
        int creNum;// the Creature number 
         
        int i=1; 
        do {
            i=1;
            System.out.print("\"How many creatures would you like to have (minimum 2, maximum 8)? ");
            creNum = input.nextInt();
        
                if( creNum<2 || creNum >8 ){//This helps to maake sure number between 2 and 8 inclusive, correpond to Figure 1- Prompt for number of players/creatures
                    i=0;
                    System.out.println();
                    System.out.println("*** Illegal number of creatures requested ***");}
                    System.out.println();
         
         }while (i!=1);
               
//page 4 question 1,2 part  II, of assignment 4  
        Scanner get = new Scanner (System.in);
        String name;
        Creature[] Cres =new Creature[creNum]; //an array containing the requested number/corresponds Figure 2- Create the creatures
        for (int j=0 ; j < Cres.length; j++){
            
            System.out.print("What is the name of creature "+ (j+1) +" ? ");
            name = get.nextLine();
            
            Cres[j]= new Creature(name);
            System.out.println();
            Cres[j].printshow();
            System.out.println();
        }
    
        Random random = new Random(); //corresponds to page 4 part 3. Play the game: Randomly decide which creature starts by generating a random number
        int R;
        R = random.nextInt(creNum);   
        Creature aliveNum = new Creature();//to have an object to show the number of creature are alive
        String NewName=" ";
        
        int option;
        Scanner getChoice = new Scanner (System.in);
        int AttackedPlayer=0;
    do {
       AttackedPlayer=0;
       System.out.println("Creature #"+(R+1)+" : "+ Cres[R].getName() + ", what do you want to do?" );  // This part corresponds to page 5/Figure 3 and after
       Cres[R].PossibleChoices();
       option = input.nextInt();
     
           switch (option){
               case 1: //Figure 4 - Screen capture of Option 1
                   System.out.println("      Number of creatures alive "+aliveNum.getNumStillAlive() );
                   break;
               case 2: //Fig ure 5- Screen capture of Option 2
                   Cres[R].printshow();// to print status of the player
                   break;
               case 3: //Fig ure 6 - Screen capture of Option 3
                   for(int f=0; f<creNum; f++){//A loop for print all players status
                   Cres[f].printshow();}// to print status of all player
                   break;
               case 4://Figure 7- Screen capture of Option 4
                   System.out.println("      Your name is currently "+"“"+Cres[R].getName()+"”" );
                   System.out.print("      What is the new name: ");
                   NewName = get.nextLine();// to get a new name 
                   Cres[R].setName(NewName);
                   
                   break;
               case 5://Figure 8- Screen capture of Option 5
                   System.out.println("Your status before working for food: "+Cres[R].getFoodUnits()+" food units,"+Cres[R].getHealthUnits()+
                           " health units,"+Cres[R].getFirePowerUnits()+" fire power");
                   System.out.println("units ... You earned "+ Cres[R].earnFood() +" food units." );//to earn extra food units
                   System.out.println();
                   System.out.println("Your status after working for food: "+ Cres[R].showStatus());
                   
                   R++;
                   break;
               case 6:  // This part corresponds to page 8: Figure 9, 10 and 11 -  for attacking  
                   int wrongchoice=1;// Variable in the case of wrong choice 
                   boolean Isdead=false;// to control the dead situation of creatures
                            do{
                               
                                 System.out.print("Who do you want to attack? (enter a number from 1 to "+creNum+" other than yourself("+(R+1)+"): ");
                                 AttackedPlayer= getChoice.nextInt();
                               
                                    if (AttackedPlayer<1 || AttackedPlayer>creNum){// in the case of choosing the wrong number 
                                              System.out.println("That creature does not exist. Try again ...");
                                              System.out.println();
                                              wrongchoice=0;// to remain in the loop
                                    }
                                    else if(AttackedPlayer>=1 && AttackedPlayer<=creNum){//in the case of choosing the right number
                                              wrongchoice=1;// to be able to get out of the loop
                                              Isdead= Cres[(AttackedPlayer-1)].healthFoodUnitsZero();// to have variable to check if the creature is dead

                                                        if (AttackedPlayer==(R+1)){// this part corresponds to page 8 Figure 9- Screen capture of Option 6 (example 1) – Case of a succ essful attack
                                                            System.out.println("Can't attack yourself silly! Try again ...");
                                                            System.out.println();
                                                            wrongchoice=0;// to remain in the loop
                                                             }

                                                        if (Isdead==true){
                                                             System.out.print("That creature IS Dead. Try again for another creature ...");
                                                             wrongchoice=0;
                                                             }

                                    }
                           }while(wrongchoice==0);
                            
                            
                        System.out.println();
                        System.out.println( "You are attacking "+Cres[(AttackedPlayer-1)].getName()+"!"); // this part corresponds to page 8, Figure 10- screen capture of Option 6 

                           if(Cres[R].getFirePowerUnits()<2){//to check if the attacker has not enough firepowerunit 
                              System.out.println("That was not a good idea ... you only had "+ Cres[R].getFirePowerUnits()+" Fire Power units!!!");
                              System.out.println("....... Oh No!!! You are being attacked by "+ Cres[(AttackedPlayer-1)].getName()+"!");
                               System.out.println("Your status before attacking: "+ Cres[R].showStatus());
                                    if (Cres[(AttackedPlayer-1)].getFirePowerUnits()<2){
                                       System.out.println("“Lucky you, the odds were that the other player attacks you, but" +
                                                             Cres[(AttackedPlayer-1)].getName()+" doesn't have enough fire power to" +
                                                             "attack you! So is status quo!!”. "); 
                                       R++; 
                                       break;
                                    }
                               Cres[(AttackedPlayer-1)].attacking(Cres[R]);// doing the attack back
                               System.out.println("Your status after attacking: "+ Cres[R].showStatus());
                           }
                           else{
                                int AttackBack= random.nextInt(3);//randomly the creature at play has 1 in 3 chances of being attacked instead of attacking
                                  if(AttackBack==0){// in the case of random =0 , attacking back happens
                                    System.out.println("....... Oh No!!! You are being attacked by "+ Cres[(AttackedPlayer-1)].getName()+"!");
                                    System.out.println("Your status before attacking: "+ Cres[R].showStatus());
                                        if (Cres[(AttackedPlayer-1)].getFirePowerUnits()<2){//If the creature you choose also does not have 2 firePower
                                            System.out.println("“Lucky you, the odds were that the other player attacks you, but" +
                                                                Cres[(AttackedPlayer-1)].getName()+" doesn't have enough fire power to" +
                                                                "attack you! So is status quo!!”. "); 
                                            R++;
                                            break;
                                         }
                                     Cres[(AttackedPlayer-1)].attacking(Cres[R]);//doing the attack back
                                     System.out.println("Your status after attacking: "+ Cres[R].showStatus());}// his part corresponds to page9,Figure 11-Screen capture of Option 6 (example 3), after a successful attack, Creature #2 Sparki is dead. Since there is only one
//alive creature, the game is over. Display the final status of all creatures. Sparki’s data is based on Figure 10.

                                  else{
                                     System.out.println("Your status before attacking: "+ Cres[R].showStatus());
                                     Cres[R].attacking(Cres[(AttackedPlayer-1)]);//Attacking the chosen creature
                                     System.out.println("Your status after attacking: "+ Cres[R].showStatus());}
                           
                                  if (Cres[(AttackedPlayer-1)].isAlive()==false){// To show if the chosen creature is dead
                                      System.out.println();
                                      System.out.println("---->"+ Cres[(AttackedPlayer-1)].getName()+" is dead " );}
                                  if (Cres[R].isAlive()==false){// To show if the chosen creature is dead
                                      System.out.println();
                                      System.out.println("---->"+ Cres[R].getName()+" is dead " );}
                           }
                   R++;
                   break;
               default:
                   break;
            }
    
    
      if(R!=creNum){// Check if the next creature player was dead , it should jump to the next creature
        if(Cres[R].isAlive()==false){
           R++;}}
      if(R>=creNum){
         R=0;
         if(Cres[R].isAlive()==false){
            R++;}
        }
   
    }while( aliveNum.getNumStillAlive() != 1);// the condition of the End of the Battle Game 
    
    System.out.println();
    System.out.println("GAME OVER!!!!!");
    System.out.println();
    for(int f=0; f<creNum; f++){
        Cres[f].printshow();}
    

}
}
  

