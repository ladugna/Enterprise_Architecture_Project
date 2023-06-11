package retail.service.adapter;

import retail.domain.Category;

public class CategoryAdapter {

    public static CategoryDTO getCategoryDTOFromCategory(Category category){
        return new CategoryDTO(category.getName());
    }
    public static Category getCategoryFromCategoryDTO(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getName());
    }
}
