package com.zb.controller;

import com.zb.pojo.Parking_record;
import com.zb.pojo.Region;
import com.zb.service.impl.Parking_recordServiceImpl;
import com.zb.service.impl.RegionServiceImpl;
import com.zb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ControllerRecord {
    @Autowired
    Parking_recordServiceImpl recordService;
    @Autowired
    RegionServiceImpl regionService;
    //异步获取下拉列表
    @RequestMapping(value = "/xiala")
    @ResponseBody
    public List<Region> xiala(){
        List<Region> list = regionService.getAll();
        return list;
    }
    @RequestMapping("/show")
    //查询全部车辆信息的方法
    public String getAll(Integer region_id,
                         @RequestParam(value="pageIndex",required = false,defaultValue = "1") Integer pageIndex
                                       ,Model model){
        Util<Parking_record> util = recordService.getAll(region_id, pageIndex, 2);
        model.addAttribute("page",util);
        return "index";
    }
    @RequestMapping("/add")
    public String add(Parking_record parking_record){
        int count = recordService.add(parking_record);
        if(count>0){
            //新增成功!
            return "redirect:/show";
        }else{
            return "add";
        }
    }
    //修改停车状态
    @RequestMapping("/update")
    @ResponseBody
    public String update(Parking_record parking_record){
        int count = recordService.update(parking_record);
        if(count>0){
            return "ok";
        }else{
            return "no";
        }
    }
    //根据ID查询车辆信息
    @RequestMapping("/toupdate")
    public String getById(Integer id,Model model){
        Parking_record record = recordService.getById(id);
        model.addAttribute("r",record);
        return "update";
    }
    //修改
    @RequestMapping("/updateCar")
    public String upadte(Parking_record parking_record,Model model){
        int count = recordService.updateCar(parking_record);
        if(count>0){
            //修改成功!
            model.addAttribute("msg","修改成功!");
            return "forward:/show";
        }else{
            //修改失败!
            return "forward:/toupdate?id="+parking_record.getId();
        }
    }
    //异步删除
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        int count = recordService.delete(id);
        if(count>0){
            return "y";
        }else{
            return "n";
        }
    }
}
