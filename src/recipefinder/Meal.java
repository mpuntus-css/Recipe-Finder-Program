
package recipefinder;


public class Meal {
    public String strMeal;
    public int idMeal;
    
    
    public Meal() {
        strMeal = "";
        idMeal = 0;
    }
    
   @Override
   public String toString() {
       return strMeal + idMeal;
   }
}
