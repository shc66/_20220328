package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.RegionMapper;
import com.zb.pojo.Region;
import com.zb.service.RegionService;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Resource
    RegionMapper mapper;
    @Resource
    RedisUtil redisUtil;
    @Override
    public List<Region> getAll() {
        List<Region> list=new ArrayList<>();
        if(redisUtil.exists("regions")) {
            System.out.println("从redis读");
            // 有,数据从redis读取
            String json = redisUtil.get("regions").toString();
            list= JSON.parseArray(json,Region.class);
        }else {
            System.out.println("从db读");
            //没有 数据从数据库读取，把数据放到redis中
            list= mapper.getAll();
            redisUtil.set("regions", JSON.toJSONString(list),25);
        }
        return list;
    }
}
