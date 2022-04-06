package com.zb.service;

import com.zb.pojo.Parking_record;
import com.zb.util.Util;
import org.apache.ibatis.annotations.Param;

public interface Parking_recordService {
    //查询全部车辆信息
    public Util<Parking_record> getAll(@Param("region_id") Integer region_id,
                                       @Param("pageIndex") Integer pageIndex,
                                       @Param("pageSize") Integer pageSize);
    //新增车辆信息
    public int add(Parking_record p);
    //将汽车状态改为已驶离
    public int update(Parking_record p);
    //修改车辆信息
    public int updateCar(Parking_record p);
    //删除
    public int delete(@Param("id") Integer id);
    //根据ID查询信息
    public Parking_record getById(@Param("id") Integer id);
}
