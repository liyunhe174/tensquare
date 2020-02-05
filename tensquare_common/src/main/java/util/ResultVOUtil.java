package util;

import entity.ResultVO;
import entity.StatusCode;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/19 11:33
 * @Description:
 */
public class ResultVOUtil {
    /**
     * @Description 返回前台的成功不携带数据的方法
     * @param message:   返回前台的提示信息
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 11:35
     */
    public static ResultVO success(String message) {
        return new ResultVO(true, StatusCode.OK, message);
    }

    /**
     * @Description 返回前台的成功携带数据的方法
     * @param message: 提示信息
     * @param data:  携带的数据
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 11:38
     */
    public static ResultVO success(String message, Object data) {
        return new ResultVO(true, StatusCode.OK, message,data);
    }

    /**
     * @Description 返回前台的失败消息的方法
     * @param message:  提示信息
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 11:40
     */
    public static ResultVO error(String message) {
        return new ResultVO(false, StatusCode.ERROR, message);
    }
}
