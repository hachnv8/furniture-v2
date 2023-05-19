package com.hacheery.backend.payload.request;

import com.hacheery.backend.utils.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by HachNV on 26/04/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {
    private String sortBy;
    private String sortDirection;
    private int page = AppConstants.DEFAULT_PAGE_NUMBER;
    private int size = AppConstants.DEFAULT_PAGE_SIZE;
}
