package com.knife.ant.common.util.auth;

import com.knife.ant.common.entity.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    // 用于获得当前账户的信息。
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}