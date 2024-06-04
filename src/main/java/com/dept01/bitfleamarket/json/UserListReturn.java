package com.dept01.bitfleamarket.json;

import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.pojo.user.UserBriefInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListReturn {
    private int num;
    private List<UserBriefInfo> users;
}

