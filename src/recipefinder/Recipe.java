
package recipefinder;


public class Recipe {
    public int idMeal;
    public String strMeal;
    public String strCategory;
    public String strArea;
    public String strInstructions;

    public Recipe() {
        idMeal = 0;
        strMeal = "";
        strCategory = "";
        strArea = "";
        strInstructions = "";
    }

    @Override
    public String toString() {
        return " ID of the meal: " + idMeal + ",\n Name: " + strMeal + ",\n Category: " + strCategory + ", Country: " + strArea + ",\n Instruction: " + strInstructions;
    }
    
   
}
