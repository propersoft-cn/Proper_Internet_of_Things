package cn.propersoft.IoT.demo.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.demo.vo.DemoVO;
import cn.propersoft.IoT.AbstractSpringTest;
import cn.propersoft.IoT.test.utils.AddressUtils;
import cn.propersoft.IoT.user.vo.UserVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoControllerTest extends AbstractSpringTest {

    @Autowired
    private AddressUtils addressUtils;

    private Map<String, String> header = new HashMap<>();

    @Before
    public void getToken() {

        UserVO userVO = new UserVO();
        userVO.setName("test");
        userVO.setUsername("123456");
        userVO.setPassword("123456");
        HttpUtil.post(addressUtils.getUrl() + "/user/create", BeanUtil.beanToMap(userVO));

        Map<String, Object> param = new HashMap<>();
        param.put("username", "123456");
        param.put("password", "123456");
        String post = HttpUtil.post(addressUtils.getUrl() + "/auth/getToken", param);
        JSONObject jsonObject = JSONUtil.parseObj(post);
        String result = Convert.toStr(jsonObject.get("result"));
        Assert.isTrue(StrUtil.isNotBlank(result));
        header.put("token", result);
    }

    @Test
    public void demo1Test() {
        HttpResponse response = HttpUtil.createGet(addressUtils.getUrl() + "/demo/demo1").addHeaders(header).execute();
        String body = response.body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String result = Convert.toStr(jsonObject.get("result"));
        Assert.isTrue(StrUtil.isNotBlank(result));
    }

    @Test
    public void findAllTest() {
        String body = HttpUtil.createGet(addressUtils.getUrl() + "/demo/demo1").addHeaders(header).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        List result = Convert.toList(jsonObject.get("result"));
        Assert.isTrue(result != null);
    }

    @Test
    public void addTest() {
        DemoVO demoVO = new DemoVO();
        demoVO.setName("test");
        demoVO.setName2("testtest");
        Map<String, Object> body = BeanUtil.beanToMap(demoVO);
        String post = HttpUtil.createPost(addressUtils.getUrl() + "/demo/add")
                .form(body).execute().body();

        JSONObject jsonObject = JSONUtil.parseObj(post);
        String code = Convert.toStr(jsonObject.get("code"));
        Assert.isTrue(StrUtil.equals(code, "200"));
    }

}
