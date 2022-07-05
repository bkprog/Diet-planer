package dietplaner.example.dietplaner.recipe.repository;

import dietplaner.example.dietplaner.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    Optional<Recipe> findRecipeByName(String name);
}
