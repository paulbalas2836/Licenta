package licenta.project.Services;

import licenta.project.Dto.FoodDto.AddUpdateFoodDto;
import licenta.project.Dto.HistoryDto.DailyNutrientsDto;
import licenta.project.Dto.HistoryDto.FoodHistoryDto;
import licenta.project.Exceptions.AppException;
import licenta.project.Models.Food;
import licenta.project.Records.FoodRecord;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface FoodService {
    Optional<Food> getFood(String label);

    Page<FoodRecord> getAllFoods(Integer page, String search) throws AppException;

    void addFood(AddUpdateFoodDto addUpdateFoodDto);

    void updateFood(Long foodId, AddUpdateFoodDto addUpdateFoodDto) throws AppException;

    void deleteFood(Long foodId) throws AppException;

    FoodHistoryDto calculateNutritionalValues(Food food, FoodHistoryDto foodHistoryDto);

    void getDailyNutrients(DailyNutrientsDto dailyNutrientsDto, FoodHistoryDto foodHistoryDto);
}
