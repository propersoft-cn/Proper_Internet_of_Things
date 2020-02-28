package cn.propersoft.IoT;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestStarter.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractSpringTest {

}
