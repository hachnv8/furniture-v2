package com.hacheery.backend.utils;

import com.hacheery.backend.exception.FurnitureApiException;
import org.springframework.http.HttpStatus;

/**
 * Created by HachNV on 26/04/2023
 */
public class AppUtils {
    public static void validatePageNumberAndPageSize(int pageNumber, int pageSize) {
        if(pageNumber < 0) throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Số trang phải lớn hơn 0");
        if(pageSize < 0) throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Số lượng bản ghi phải lớn hơn 0");
    }
}
