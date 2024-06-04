package com.dept01.bitfleamarket.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfoReturn {
    private int user_id;
    private String name;
    private String avatar_url;
    private String token;
    private String info;
}
