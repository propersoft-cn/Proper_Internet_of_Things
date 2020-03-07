package cn.propersoft.IoT.demo.controller;

import cn.propersoft.IoT.demo.entity.DemoEntity;
import cn.propersoft.IoT.demo.service.DemoService;
import cn.propersoft.IoT.auth.annotation.UserLoginToken;
import cn.propersoft.IoT.demo.vo.DemoVO;
import cn.propersoft.IoT.response.ResultBody;
import cn.propersoft.IoT.websocket.server.WebSocketServer;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Api(tags = "无用测试接口")
@RequestMapping("/demo")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @UserLoginToken
    @ApiOperation(value = "Hello SpringBoot")
    @GetMapping("/demo1")
    public ResultBody demo1() {
        return ResultBody.success("hello springBoot");
    }

    @ResponseBody
    @UserLoginToken
    @ApiOperation(value = "查询所有Demo数据")
    @GetMapping("/findAll")
    public ResultBody findAll() {
        return ResultBody.success(demoService.findAll());
    }

    @ResponseBody
    @UserLoginToken
    @PostMapping("/add")
    @ApiOperation(value = "新增DemoVo")
    public ResultBody add(DemoVO demoVO) {
        demoService.add(demoVO);
        return ResultBody.success();
    }

    @ResponseBody
    @UserLoginToken
    @PostMapping("/addRedisDemo")
    @ApiOperation(value = "新增Redis数据 ")
    public void addRedisDemo(@ApiParam(value = "Redis的key", required = true) @RequestParam String key,
                             @ApiParam(value = "Redis的value", required = true) @RequestParam String value) {
        demoService.addRedisDemo(key, value);
    }

    @ResponseBody
    @UserLoginToken
    @GetMapping("/addRedisDemo")
    @ApiOperation(value = "根据key获取Redis的值 ")
    public String getRedisDemo(@ApiParam(value = "Redis的value", required = true) @RequestParam String key) {
        return demoService.getRedisDemo(key);
    }

    @GetMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        Boolean isStop = true;
        while (isStop) {
            DemoEntity entity = demoService.findOneByOrderById();
            WebSocketServer.sendInfo(entity.getName(), toUserId);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("MSG SEND SUCCESS");
        }

        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

//    @ResponseBody
//    @GetMapping("/quartzTest1")
//    @ApiOperation(value = "定时任务调度测试")
//    public ResultBody quartzTest1(String f) {
//
//        //TODO 根据元数据id获取元数据信息
//        //TODO 根据目标数据id获取目标数据信息
//        //TODO 获取数据源信息
//        //TODO 启动定时任务 记录定时任务id
//        Scheduler scheduler = CronUtil.getScheduler();
//        if (!scheduler.isStarted()) {
//            CronUtil.start();
//        }
//        CronUtil.setMatchSecond(true);
//        String id = CronUtil.schedule("*/3 * * * * *", new Runnable() {
//            @Override
//            public void run() {
//                Console.log("task running : 3s");
//            }
//        });
//        //TODO 保存定时任务详情表中（taskid，元id，目id，备注）
//        //TODO 建立连接获取数据,校验数据合法性
//        //TODO 根据id获取映射配置
//        //TODO 根据映射配置处理转存数据
//        //TODO 记录数据抽取结果
//
//
//        return ResultBody.success();
//
//    }
//
//    @ResponseBody
//    @GetMapping("/quartzTest2")
//    @ApiOperation(value = "定时任务调度测试")
//    public ResultBody quartzTest2(String id) {
//
//        //TODO 根据元数据id获取元数据信息
//        //TODO 根据目标数据id获取目标数据信息
//        //TODO 获取数据源信息
//        //TODO 启动定时任务 记录定时任务id
//        //TODO 保存定时任务详情表中（taskid，元id，目id，备注）
//        //TODO 建立连接获取数据,校验数据合法性
//        //TODO 根据id获取映射配置
//        //TODO 根据映射配置处理转存数据
//        //TODO 记录数据抽取结果
//        CronUtil.remove(id);
//
//        return ResultBody.success(id);
//
//    }
//
//
//    @ResponseBody
//    @GetMapping("/connDB")
//    @ApiOperation(value = "连接数据源 ")
//    public ResultBody connDB(String url, String username, String password) {
//
//        DataSource ds = new SimpleDataSource(url, username, password);
//
//        Connection conn = null;
//        try {
//            conn = ds.getConnection();
//            // 执行非查询语句，返回影响的行数
//            String sql = "CREATE TABLE `yali` (\n" +
//                    "  `id` varchar(64) NOT NULL,\n" +
//                    "  `add_time` datetime(6) NOT NULL,\n" +
//                    "  `equipment_name` varchar(32) NOT NULL,\n" +
//                    "  `yali_num` double DEFAULT NULL,\n" +
//                    "  PRIMARY KEY (`id`)\n" +
//                    ")";
//            SqlExecutor.execute(conn, sql);
////            int count = SqlExecutor.execute(conn, "UPDATE " + TABLE_NAME + " set field1 = ? where id = ?", 0, 0);
////            LOGGER.info("影响行数：{}", count);
//            // 执行非查询语句，返回自增的键，如果有多个自增键，只返回第一个
////            Long generatedKey = SqlExecutor.executeForGeneratedKey(conn, "UPDATE " + TABLE_NAME + " set field1 = ? where id = ?", 0, 0);
////            LOGGER.info("主键：{}", generatedKey);
//
//            /* 执行查询语句，返回实体列表，一个Entity对象表示一行的数据，Entity对象是一个继承自HashMap的对象，存储的key为字段名，value为字段值 */
////            List<Entity> entityList = SqlExecutor.query(conn, "select * from " + TABLE_NAME + " where param1 = ?", new EntityListHandler(), "值");
////            LOGGER.info("{}", entityList);
//        } catch (SQLException e) {
////            LOGGER.error(LOGGER, e, "SQL error!");
//        } finally {
//            DbUtil.close(conn);
//        }
//
//        return null;
//    }


}
