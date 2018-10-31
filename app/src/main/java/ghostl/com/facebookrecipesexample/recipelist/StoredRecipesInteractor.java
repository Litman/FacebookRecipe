package ghostl.com.facebookrecipesexample.recipelist;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public interface StoredRecipesInteractor {
    void executeUpdate(Recipe recipe);
    void executeDelete(Recipe recipe);
}
