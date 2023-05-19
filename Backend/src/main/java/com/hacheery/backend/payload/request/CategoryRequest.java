package com.hacheery.backend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by HachNV on 26/04/2023
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest extends BaseRequest {
    private String name;
    private String description;
    private Long parentId;
}
