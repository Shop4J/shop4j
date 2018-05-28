package shop4j.services.products.impl;

import base.util.collections.CollectionUtil;
import base.util.collections.parser.CollectionsParserUtil;
import base.util.string.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import shop4j.dao.products.ProductMapper;
import shop4j.models.products.*;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.*;
import shop4j.vo.product.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 19:13
 * @Description:产品业务
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductKidService productKidService;

    @Autowired
    private ProductParamValueService productParamValueService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SearchMoneyService searchMoneyService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private YearOldService yearOldService;
    @Override
    public PageInfo<Product> findBySearchVO(SearchProductVO searchProductVO) {
        ProductSearchVOFormat productSearchVOFormat = formatSearchProductVO(searchProductVO);
        PageHelper.startPage(searchProductVO.getPage(),searchProductVO.getSize());
        List<Product> productsIds = productMapper.findBySearchVO(productSearchVOFormat);
        if(CollectionUtil.isNotEmpty(productsIds)) {
            List<Product> products = getByIds(CollectionsParserUtil.collectFieldToList(productsIds, Product::getId));
            PageInfo<Product> productsPageInfo = new PageInfo<>(productsIds);
            productsPageInfo.setList(products);
            return productsPageInfo;
        }
        return new PageInfo<>(productsIds);
    }
    private ProductSearchVOFormat formatSearchProductVO(SearchProductVO searchProductVO){
        ProductSearchVOFormat productSearchVOFormat = new ProductSearchVOFormat();
        List<Long> colorIds = searchProductVO.getColors();
        if(CollectionUtil.isNotEmpty(colorIds)){
            List<Color> colors = colorService.getByIds(colorIds);
            List<String> colorNames = CollectionsParserUtil.collectFieldToList(colors, Color::getName);
            productSearchVOFormat.setColors(colorNames);
        }
        List<Long> olds = searchProductVO.getYearOlds();
        if(CollectionUtil.isNotEmpty(olds)){
            List<YearOld> yearOlds = yearOldService.getByIds(olds);
            List<String> oldNames = CollectionsParserUtil.collectFieldToList(yearOlds, YearOld::getName);
            productSearchVOFormat.setAges(oldNames);
        }
        Long productKidType = searchProductVO.getProductKidType();
        if(!Objects.isNull(productKidType)){
            productSearchVOFormat.setProductKidTypes(Arrays.asList(productKidType));
        }
        List<Long> productTypes = searchProductVO.getProductTypes();
        if(CollectionUtil.isNotEmpty(productTypes)){
            List<ProductType> productKidTypes = productTypeService.findByParentIds(productTypes);
            productSearchVOFormat.setProductKidTypes(CollectionsParserUtil.collectFieldToList(productKidTypes,ProductType::getId));
        }
        String productName = searchProductVO.getSearchText();
        if(StringUtil.isNotEmpty(productName)){
            productSearchVOFormat.setProductName(productName);
        }
        List<Long> moneyIds = searchProductVO.getMoneys();
        if(CollectionUtil.isNotEmpty(moneyIds)){
            List<SearchMoney> moneys = searchMoneyService.getByIds(moneyIds);
            List<MoneySearchVO> moneySearchVOS = new ArrayList<>();
            moneys.forEach(searchMoney -> {
                MoneySearchVO moneySearchVO = new MoneySearchVO();
                moneySearchVO.setBegin(searchMoney.getBegin());
                moneySearchVO.setEnd(searchMoney.getEnd());
                moneySearchVOS.add(moneySearchVO);
            });
            productSearchVOFormat.setMoneySearchVOS(moneySearchVOS);
        }
        Integer moneyBegin = searchProductVO.getMoneyBegin();
        Integer moneyEnd = searchProductVO.getMoneyEnd();
        if(!Objects.isNull(moneyBegin) || !Objects.isNull(moneyEnd)){
            List<MoneySearchVO> moneySearchVOS = new ArrayList<>();
            MoneySearchVO moneySearchVO = new MoneySearchVO();
            if(moneyBegin!=0){
                moneySearchVO.setBegin(moneyBegin);
            }
           if(moneyEnd!=0) {
               moneySearchVO.setEnd(moneyEnd);
           }
            moneySearchVOS.add(moneySearchVO);
            List<MoneySearchVO> oldMoneySearchVOS = productSearchVOFormat.getMoneySearchVOS();
            if(CollectionUtil.isEmpty(oldMoneySearchVOS)){
                productSearchVOFormat.setMoneySearchVOS(moneySearchVOS);
            }else{
                oldMoneySearchVOS.addAll(moneySearchVOS);
                productSearchVOFormat.setMoneySearchVOS(oldMoneySearchVOS);
            }
        }
        return productSearchVOFormat;
    }
    @Override
    public  List<Product> findByTypesIndexSuggest(List<Long> typeIds) {
        List<Product> products = productMapper.findByTypes(typeIds,10);
        return products;
    }

    @Override
    public void removeTypeIndexSuggestCache() {

    }

    @Transactional
    @Override
    public void addProduct(ProductVO productVO,long operator) {
        if(!Objects.isNull(productVO)){

            Product product = new Product();
            product.setName(productVO.getName());
            product.setShowPrice(productVO.getShowPrice());
            product.setType(productVO.getProductType());
            product.setDetail(productVO.getDetail());
            product.setAddOperator(operator);
            long spuId = addReturnKey(product);

            List<ProductImage> productImages = new ArrayList<>();
            List<ProductParamValue> paramValues = new ArrayList<>();
            List<SKUVO> skuVOS = productVO.getSKUVOList();
            if(CollectionUtil.isNotEmpty(skuVOS)) {
                skuVOS.forEach(skuVO -> {
                    ProductKid productKid = new ProductKid();
                    productKid.setNum(skuVO.getNum());
                    productKid.setIsMain(skuVO.getIsMain());
                    productKid.setPrice(skuVO.getPrice());
                    productKid.setSpuId(spuId);
                    productKid.setAddOperator(operator);
                    long skuId = productKidService.addReturnKey(productKid);
                    List<ProductParamVO> params = skuVO.getProductParamBeanList();
                    if(CollectionUtil.isNotEmpty(params)) {
                        params.forEach(productParamVO -> {
                            ProductParamValue productParamValue = new ProductParamValue();
                            productParamValue.setParamId(productParamVO.getParamId());
                            productParamValue.setValue(productParamVO.getValue());
                            productParamValue.setProductId(skuId);
                            productParamValue.setAddOperator(operator);
                            paramValues.add(productParamValue);
                        });
                    }

                    List<ProductImageVO> imageBeans = skuVO.getProductImageBeans();
                    if(CollectionUtil.isNotEmpty(imageBeans)){
                        imageBeans.forEach(imageBean->{
                            ProductImage productImage = new ProductImage();
                            productImage.setAddOperator(operator);
                            productImage.setSort(imageBean.getSort());
                            productImage.setUrl(imageBean.getUrl());
                            productImage.setProductId(skuId);
                            productImage.setType(imageBean.getType());
                            productImages.add(productImage);
                        });
                    }
                });
                productParamValueService.addList(paramValues);
                productImageService.addList(productImages);
            }
        }
    }

}
