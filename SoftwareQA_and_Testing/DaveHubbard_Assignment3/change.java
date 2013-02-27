import java.util.Scanner;
public class change
{

  static int tDollars, tQuarters, tDimes, tNickels, tPennies, tChange, tChange2;
  
  public static void main(String[] args)
  {
   Scanner scan = new Scanner(System.in);
   double cost, paidAmount;
   System.out.println("Please enter the cost:");
   cost = scan.nextDouble();
   int dollars=0, quarters=0, dimes=0, nickles=0, pennies=0;

   System.out.println("Please enter the paid amount:");
   paidAmount = scan.nextDouble();

   int change = (int)((paidAmount - cost)*100.0);

   if(change > 0)
   {
	   dollars=change/100;

	   change = change%100;

	   quarters = change/25;

	   change = change%25;

	   dimes = change/10;

	   change = change%10;

	   nickles = change/5;
	   
	   change = change%5;

	   pennies = change%5;
	   
	   change = change%pennies;

  }

   System.out.println("Dollars =" + dollars);
   System.out.println("Quarters=" + quarters);
   System.out.println("Dimes =" + dimes);
   System.out.println("Nickles =" + nickles);
   System.out.println("Pennies =" + pennies);


  }
  
  public static boolean testMain(double costTest, double paidTest){
	  double cost, paidAmount;
	  cost = costTest;
	  tDollars=0; tQuarters=0; tDimes=0; tNickels=0; tPennies=0;
	  paidAmount = paidTest;
	  tChange = (int)((paidAmount - cost)*100.0);

	   if(tChange > 0)
	   {
		   tDollars= tChange/100;

		   tChange = tChange%100;

		   tQuarters = tChange/25;

		   tChange = tChange%25;

		   tDimes = tChange/10;

		   tChange = tChange%10;

		   tNickels = tChange/5;
		   
		   tChange = tChange%5;

		   tPennies = tChange%5;
		   
		   tChange = tChange%tPennies;

	  }
	   return true;
  }
  
  public static int getChange(){
	  return tChange;
  }
  
  public static int getChange2(){
	  return tChange2;
  }
 
  public static int getDollars(){
	  return tDollars;
  }
  public static int getQuarters(){
	  return tQuarters;
  }
  public static int getDimes(){
	  return tDimes;
  }
  public static int getNickels(){
	  return tNickels;
  }
  public static int getPennies(){
	  return tPennies;
  }
}