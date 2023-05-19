package com.hacheery.backend.payload.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by HachNV on 04/05/2023
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerRequest extends BaseRequest {
    private String name;

    private String address;

    private String phoneNumber;

    private String email;
}
