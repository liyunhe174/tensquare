package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/18 19:43
 * @Description: 返回前台数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResultVO {

    private  boolean flag;  //是否成功
    private  Integer code;  //返回码
    private  String message; //返回信息
    private  Object data;

    public ResultVO(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
