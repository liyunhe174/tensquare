package enums;

import lombok.Getter;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/18 20:25
 * @Description:  状态枚举
 */
@Getter
public enum  StatusEnum {

    OK(20000, "成功"),
    ERROR(20001, "失败"),
    LOGINERROR(20002, "用户名或密码错误"),
    ACCESSERROR(20003, "权限不足 "),
    REMOTEERROR(20004, "远程调用失败"),
    REPERROR(20005, "重复操作"),
    ;

    private int code ;
    private String msg ;

    private StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
