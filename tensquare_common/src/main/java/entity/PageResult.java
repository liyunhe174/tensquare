package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/18 20:11
 * @Description:  分页返回结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private  Long  total;
    private List<T> rows;

}
