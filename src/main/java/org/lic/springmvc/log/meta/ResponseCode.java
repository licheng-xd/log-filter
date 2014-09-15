package org.lic.springmvc.log.meta;

/**
 * Created by lc on 14-7-17.
 */
public interface ResponseCode {
        // common
        public static final int RES_ERR_PARAM = 100; // 参数错误

        public static final int RES_NOT_MODIFY = 101; // 没有变化

        public static final int RES_NOT_EXIST = 102; // 不存在

        public static final int RES_ERR_UNKNOW = 103; // 未知

        public static final int RES_ERR_FORBBIDEN = 104; // 禁止操作

        public static final int RES_OK = 200;

        // user code
        public static final int RES_USER_CODE_START = 300;

        public static final int RES_NOT_RESGITER = 300; // 用户未注册

        public static final int RES_ERR_PASS = 301; // 密码错误

        public static final int RES_NO_FRIEND = 302; // 没有好友

        public static final int RES_USER_NOEXIST = 303; // 用户不存在

        // team code
        public static final int RES_TEAM_CODE_START = 400;

        public static final int RES_TEAM_NOEXIST = 400; // 群不存在

        public static final int RES_TEAM_NOAUTH = 401; // 没有权限

        public static final int RES_NO_TEAMLIST = 402; // 没有群

        public static final int RES_TUSER_LIMIT = 403; // 群成员数量限制

        public static final int RES_TEAM_LIMIT = 404; // 群数量限制

}
