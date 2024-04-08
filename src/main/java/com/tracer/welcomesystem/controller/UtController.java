package com.tracer.welcomesystem.controller;


import com.tracer.welcomesystem.models.UserTasks;
import com.tracer.welcomesystem.utils.RespBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tracer.welcomesystem.services.UtService;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ut")
public class UtController {

    final UtService utService;

    public UtController(UtService utService) {
        this.utService = utService;
    }

    @GetMapping("/list")
    public RespBean getAllUTs(){
        HashMap<Object,Object> map= new HashMap<>();
        map.put("total", 0);
        List<UserTasks> uts = utService.getAllUTs();
        map.put("items", uts);
        return RespBean.ok("UT list", map);
    }



    @GetMapping("/delete")
    public RespBean deleteUT(Long id){
        utService.deleteUT(id);
        return RespBean.ok("UT deleted");
    }

}
