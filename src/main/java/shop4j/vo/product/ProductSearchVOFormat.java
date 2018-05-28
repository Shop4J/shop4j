package shop4j.vo.product;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/26 11:02
 * @Description: 转换成service好处理得vo
 */
@Data
public class ProductSearchVOFormat implements Serializable{

    private String productName;

    private List<String> ages;

    private List<MoneySearchVO> moneySearchVOS;

    private List<Long> productKidTypes;

    private List<String> colors;


}
