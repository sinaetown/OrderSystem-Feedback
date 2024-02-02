package com.encore.ordering.order.controller;

import com.encore.ordering.common.CommonResponse;
import com.encore.ordering.item.domain.Item;
import com.encore.ordering.order.domain.Ordering;
import com.encore.ordering.order.dto.OrderReqDto;
import com.encore.ordering.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/create")
    public ResponseEntity<CommonResponse> orderCreate(@RequestBody OrderReqDto orderReqDto) {
        Ordering ordering = orderService.create(orderReqDto);
        return new ResponseEntity<>(new CommonResponse(HttpStatus.CREATED,
                "Item successfully created!", ordering.getId()), HttpStatus.CREATED);
    }
}
