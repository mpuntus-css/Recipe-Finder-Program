package recipefinder;

import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecipeFinder
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Meal> parseMeals = new ArrayList<>();
        Meal meal;
        Recipe recipe;
        
        // beginning
        System.out.println("");
        
        String ingredient = ingredient(scanner);
        String url = createUrlForRecipesByIngredient(ingredient);
        // json data
        String api = callApi(url);
        parseMeals = parseMeals(api);
        displayMeals(ingredient, parseMeals);
        
        
        int id = getID(scanner);
        meal = parseMeals.get(id - 1); 
                
        url = createUrlForRecipeById(meal.idMeal);
       
        api = callApi(url);
        recipe = parseRecipe(api);
        System.out.println(recipe);
        
        // end
        System.out.println("");
    }
    private static String ingredient(Scanner scanner) {
        System.out.println("Welcome to our reciepe helper");
        System.out.println("This program will help you find recipes by what main igriddient you want to use.");
        System.out.println("What is your main ingridient?");
        
        return (scanner.nextLine()); 
    }
    
    private static void displayMeals(String inIngridient, ArrayList inParseMeals) {
        System.out.println("");
        System.out.println("Here are recipes that include " + inIngridient + ":");
        
        for (int i = 0; i < inParseMeals.size(); i++) {
            System.out.println(i + 1 + ". " + inParseMeals.get(i));
        }
        
    }
    
    private static int getID (Scanner scanner) {
        System.out.println("");
        System.out.println("Enter ID");
        return scanner.nextInt();
    }
    private static ArrayList<Meal> parseMeals(String json)
    {
        // Remove extra json.
        json = json.substring(9, json.length() - 1);
        Gson gson = new Gson();
        
        // Convert json to Meal ArrayList.
        ArrayList<Meal> meals = gson.fromJson(json, new TypeToken<ArrayList<Meal>>(){}.getType());
        
        return meals;
    }
    
    private static Recipe parseRecipe(String json) {
        json = json.substring(10, json.length() - 2);
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(json, new TypeToken<Recipe>(){}.getType());
        
        return recipe;
    }
    
    private static String createUrlForRecipeById(int id)
    {
        return "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id;
    }
    
    private static String createUrlForRecipesByIngredient(String ingredient)
    {
        return "https://www.themealdb.com/api/json/v1/1/filter.php?i=" + ingredient;
    }
    
    public static String callApi(String urlString)
    {
        // Set up variables to call the URL and read the result.
        URL url;
        String jsonResult = "";
        
        try
        {
            // Create the URL with the address to the server.
            url = new URL(urlString);
        } catch (MalformedURLException ex)
        {
            jsonResult = ex.getMessage();
            url = null;
        }

        try (InputStream inputStream = url.openStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader))
        {
            // Get line off the input.
            String line = reader.readLine();
                
            // Keep reading lines while more still exist.
            // Append the result to a String.  Should use a StringBuilder if we
            // are expecting a lot of lines.
            while (line != null) {
                jsonResult += line;
                line = reader.readLine();
            }
        }
        catch (MalformedURLException malformedURLException) {
            // URL was bad.
            jsonResult = malformedURLException.getMessage();
        }
        catch (IOException ioException) {
            // An error occurred during the reading process.
            jsonResult = ioException.getMessage();
        }
        
        return jsonResult;
    }
}



