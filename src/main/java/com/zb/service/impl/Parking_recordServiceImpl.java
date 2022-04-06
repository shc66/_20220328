package com.zb.service.impl;

import com.zb.mapper.Parking_recordMapper;
import com.zb.pojo.Parking_record;
import com.zb.service.Parking_recordService;
import com.zb.util.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Parking_recordServiceImpl implements Parking_recordService {
    @Resource
    Parking_recordMapper mapper;
    @Override
    public Util<Parking_record> getAll(Integer region_id, Integer pageIndex, Integer pageSize) {
        Util<Parking_record> page=new Util<>();
        page.setPageSize(pageSize);
        //调用数据访问层
        int count = mapper.getCount(region_id);
        page.setTotalCount(count);
        page.setPageIndex(pageIndex);
        //调用数据访问层
        List<Parking_record> list = mapper.getAll(region_id, (pageIndex - 1) * pageSize, pageSize);
        page.setList(list);
        return page;
    }

    @Override
    public int add(Parking_record p) {
        return mapper.add(p);
    }

    @Override
    public int update(Parking_record p) {
        return mapper.update(p);
    }

    @Override
    public int updateCar(Parking_record p) {
        return mapper.updateCar(p);
    }

    @Override
    public int delete(Integer id) {
        return mapper.delete(id);
    }

    @Override
    public Parking_record getById(Integer id) {
        return mapper.getById(id);
    }
}
