package shop4j.enums;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:13
 * @Description:商品图片类型
 */
public enum ProductImageTypeEnum {
    SPU(1,"SPU图"),
    SKU(2,"SKU图"),
    Detail(3,"详情页图")
    ;
    private int type;

    private String name;

    ProductImageTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
