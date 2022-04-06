package com.zb.mapper;

import com.zb.pojo.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RegionMapper {
    //查询全部车库分区
    public List<Region> getAll();
}
