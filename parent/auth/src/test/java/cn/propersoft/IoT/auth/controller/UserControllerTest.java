package cn.propersoft.IoT.auth.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.AbstractSpringTest;
import cn.propersoft.IoT.auth.vo.UserVO;
import cn.propersoft.IoT.test.utils.AddressUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserControllerTest extends AbstractSpringTest {

    @Autowired
    private AddressUtils addressUtils;
    private static Map<String, String> header = new HashMap<>();
    @Before
    public void getToken() {
        if (header.isEmpty()) {
            UserVO userVo = new UserVO();
            userVo.setName("JZZ");
            userVo.setUsername("JZZ123456");
            userVo.setPassword("123456");
            String post = HttpUtil.post(addressUtils.getUrl() + "/user/create", BeanUtil.beanToMap(userVo));
            JSONObject parseObj = JSONUtil.parseObj(post);
            Assert.isTrue("200".equals(Convert.toStr(parseObj.get("code"))));

            post = HttpUtil.createPost(addressUtils.getUrl() + "/auth/getToken").form(BeanUtil.beanToMap(userVo)).execute().body();
            String token = Convert.toStr(JSONUtil.parseObj(post).get("result"));
            Assert.isTrue(StrUtil.isNotBlank(token));
            header.put("token", token);
        }
    }

    @Test
    public void findAllTest() {


        String body = HttpUtil.createGet(addressUtils.getUrl() + "/user/all").addHeaders(header).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Map map = jsonObject.toBean(Map.class);
        List a = (List) map.get("result");
        UserVO userVO = BeanUtil.toBean(a.get(0), UserVO.class);
        String id = userVO.getId();
        String result = Convert.toStr(jsonObject.get("result"));
        Assert.isTrue(StrUtil.isNotBlank(result));

        //通过id修改密码
        String put = HttpUtil.createRequest(Method.PUT, addressUtils.getUrl() + "/user/update").addHeaders(header).form("id", id)
                .form("oldPassword", "123456")
                .form("newPassword", "111111").execute().body();
        JSONObject jsonO = JSONUtil.parseObj(put);
        String codes = Convert.toStr(jsonO.get("code"));
        Assert.isTrue(StrUtil.equals(codes, "200"));
    }

    @Test
    public void createTest() {
        UserVO userVO = new UserVO();
        userVO.setName("XX");
        userVO.setUsername("XX123");
        userVO.setPassword("123456");
        Map<String, Object> body = BeanUtil.beanToMap(userVO);
        String post = HttpUtil.createPost(addressUtils.getUrl() + "/user/create").form(body).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(post);
        String code = Convert.toStr(jsonObject.get("code"));
        Assert.isTrue(StrUtil.equals(code, "200"));
    }

    @Test
    public void findByNameTest() {
        String username = "JZZ";
        String body = HttpUtil.createGet(addressUtils.getUrl() + "/user/find/" + username).addHeaders(header).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String code = Convert.toStr(jsonObject.get("code"));
        Assert.isTrue(StrUtil.equals(code, "200"));
    }

    @Test
    public void deleteTest() {
        String ids = "1,2";
        String body = HttpUtil.createRequest(Method.DELETE, addressUtils.getUrl() + "/user/delete").form("ids", ids).addHeaders(header).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String code = Convert.toStr(jsonObject.get("code"));
        Assert.isTrue(StrUtil.equals(code, "200"));
    }

}