package shop4j.vo.product;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: weixuedong
 * @Date: 2018/5/26 11:07
 * @Description: 关于钱得搜索
 */
@Data
public class MoneySearchVO implements Serializable{
    private int begin;

    private int end;
}
