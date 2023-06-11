package retail.service.adapter;

import lombok.Data;

@Data
public class CategoryDTO {
    private String Name;

    public CategoryDTO(String name) {
        Name = name;
    }
    public CategoryDTO() {
    }
}
