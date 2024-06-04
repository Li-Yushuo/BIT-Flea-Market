package com.dept01.bitfleamarket.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String bit_id;
    private String password;
    private String verification_code;
}
