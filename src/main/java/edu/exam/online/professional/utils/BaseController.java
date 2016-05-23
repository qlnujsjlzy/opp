package edu.exam.online.professional.utils;

/**
 * Created by licy13 on 2016/4/30.
 */
public class BaseController {
    /**
     * 校验用户是否登录
     * @return
     * @throws Exception
     */
    public static DataResult checkAuth() throws Exception{
        SessionUser user = ThreadLocalSessionUser.getUser();
        if (user == null) {
            return new DataResult(401, MessageEnum.ERROR_NOT_AUTH.getCommon());
        }
        return new DataResult(user);
    }
}
