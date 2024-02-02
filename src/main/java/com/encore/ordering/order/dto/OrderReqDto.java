package com.encore.ordering.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderReqDto {
//    #방법 1
//    private List<Long> itemIds;
//    private List<Long> counts;
//    private Long memberId;

//    #방법 2
private List<OrderReqItemDto> orderReqItemDtos;

    @Data
    private static class OrderReqItemDto {
        private Long itemId;
        private int count;
    }

}

//방법 1 예시 데이터
/*
 * {
 * "itemIds" : [1,2],
 * "counts" : [10,20]
 * }
 * */

//방법 2 예시데이터
//{
//
//    "OrderReqItemDtos": [
//        {"itemId": 1, "count": 10},
//        {"itemId": 2, "count": 20}
//    ]
//}
