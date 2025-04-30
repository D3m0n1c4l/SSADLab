abstract class RecipeTemplate {
    protected String[] ingredients;
    public void listIngredients() {
        System.out.println("[+] Ingredients: ");
        for (String ingredient : ingredients) System.out.println("- " + ingredient + ";");
    }
    public abstract void prepareRecipe();
    public void serve() {
        System.out.println("[+] Serving...");
    }
    public final void makeRecipe() {
        listIngredients();
        prepareRecipe();
        serve();
    }
    public RecipeTemplate(String[] ingredients) { this.ingredients = ingredients; }
}

class CakeRecipe extends RecipeTemplate {
    @Override
    public void prepareRecipe() {
        System.out.println("[+] Cooking a cake...");
    }
    public CakeRecipe(String[] ingredients) {
        super(ingredients);
    }
}

class SaladRecipe extends RecipeTemplate {
    @Override
    public void prepareRecipe() {
        System.out.println("[+] Cooking a salad...");
    }
    public SaladRecipe(String[] ingredients) {
        super(ingredients);
    }
}

public class Main {
    public static void main(String[] args) {
        String[] cakeIngredients = "flour sugar yolks butter vanilla cinnamon salt".split(" ");
        String[] saladIngredients = "tomatoes cucumbers onions peppers celery".split(" ");

        CakeRecipe cakeRecipe = new CakeRecipe(cakeIngredients);;
        SaladRecipe saladRecipe = new SaladRecipe(saladIngredients);;
        
        cakeRecipe.makeRecipe();
        saladRecipe.makeRecipe();
    }
}
