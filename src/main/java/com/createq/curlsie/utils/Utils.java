package com.createq.curlsie.utils;

import org.springframework.data.domain.Sort;

public class Utils {

    public static Sort parseSort(String sort) {
        if ("asc".equalsIgnoreCase(sort)) {
            return Sort.by(Sort.Direction.ASC, "price");
        } else if ("desc".equalsIgnoreCase(sort)) {
            return Sort.by(Sort.Direction.DESC, "price");
        }
        return Sort.unsorted();
    }
}
