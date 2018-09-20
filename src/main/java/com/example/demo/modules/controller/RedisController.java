package com.example.demo.modules.controller;

import com.example.demo.common.R;
import com.example.demo.common.RedisUtils;
import com.example.demo.modules.entity.SystemConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis")
@Api("redis管理")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SystemConfig systemConfig;

    @Value("${systemconfig.redis-open:false}")
    private boolean redisOpen;

    @PostMapping("/SQString")
    public R setAdnqueryString(@RequestParam String key,@RequestParam String object){
        redisUtils.setString(key,object,5);
        String result = redisUtils.getString(key,5);
        return R.ok().put("result",result);
    }

    @PostMapping("/SQList")
    public R setAdnqueryList(@RequestParam String key,@RequestParam String object){
        redisUtils.setList(key,object,5);
        redisUtils.setList(key,object,5);
        redisUtils.setList(key,object,5);
        List<Object> list = redisUtils.getList(key,5);
        return R.ok().put("list",list);
    }

    @PostMapping("/SQMap")
    public R setAdnqueryMap(@RequestParam String key,@RequestParam String hkey,@RequestParam String object){
        redisUtils.setMap(key,hkey,object,5);
        redisUtils.setMap(key,hkey,object,5);
        redisUtils.setMap(key,hkey,object,5);

        Map<String,Object> map = redisUtils.getMap(key,5);
        return R.ok().put("map",map);
    }

    @GetMapping("/QSconfig")
    public R querySysConfig(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("sysconfig",systemConfig.toString());

        Map<String,String> map  = new HashMap<String,String>();
        map.put("redis-addr",systemConfig.getRedisAddr());
        map.put("redis-open",String.valueOf(systemConfig.isRedisOpen()));
        map.put("zookeeper-addr",systemConfig.getZookeeperAddr());
        map.put("aliypay-addr",systemConfig.getAliypayAddr());
        map.put("redisOpen",String.valueOf(redisOpen));
        return R.ok().put("map",map);
    }

    @GetMapping("/queryRS")
    public R queryRedisSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sysconfig = (String) session.getAttribute("sysconfig");
        return R.ok().put("sysconfig",sysconfig);
    }
}
