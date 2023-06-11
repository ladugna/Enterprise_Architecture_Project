package retail.controller;

import lombok.Data;
import retail.service.adapter.OrderDTO;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class Orders {
    Collection<OrderDTO> orderDTOS=new ArrayList<>();

}
