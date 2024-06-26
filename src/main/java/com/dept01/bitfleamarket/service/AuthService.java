package com.dept01.bitfleamarket.service;

import com.dept01.bitfleamarket.json.AuthInfoReturn;

public interface AuthService {
    AuthInfoReturn login(String BitId, String password);
    AuthInfoReturn register(String BitId, String password, String code);
    AuthInfoReturn modifyPassword(String BitId, String password, String code);
    boolean verify(String BitId, String code);
}
