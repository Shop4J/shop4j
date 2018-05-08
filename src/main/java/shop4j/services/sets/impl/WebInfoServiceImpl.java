package shop4j.services.sets.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.sets.WebInfoMapper;
import shop4j.enums.WebInfoTypeEnum;
import shop4j.models.sets.WebInfo;
import shop4j.services.sets.WebInfoService;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: weixuedong
 * @Date: 2018/4/17 15:22
 * @Description:站点信息业务
 */
@Service
public class WebInfoServiceImpl implements WebInfoService{
    @Autowired
    private WebInfoMapper webInfoMapper;

    /**
     * 新增站点信息
     * @param webInfo
     * @return
     */
    public int add(WebInfo webInfo){

       return webInfoMapper.insert(webInfo);

    }

    @Override
    public WebInfo getWebRoot() {
        Example example = new Example(WebInfo.class);
        example.createCriteria().andEqualTo("type",WebInfoTypeEnum.Shop.getType());
        return webInfoMapper.selectOneByExample(example);
    }
}
