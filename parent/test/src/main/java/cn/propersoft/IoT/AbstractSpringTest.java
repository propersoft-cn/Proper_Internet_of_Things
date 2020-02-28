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

//    @Autowired
//    protected MockHttpServletRequest mockRequest;

//    @Autowired
//    protected WebApplicationContext wac;

//    @Autowired
//    @Qualifier("taskExecutor")
//    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

//    protected MockMvc mockMvc;

//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }

//    protected void waitExecutorDone() throws InterruptedException {
//        BlockingQueue<Runnable> queue = threadPoolTaskExecutor.getThreadPoolExecutor().getQueue();
//        while (queue != null && !queue.isEmpty()) {
//            System.out.println("sleep 5 milliseconds to wait, current blocking queue is ${queue.size()}");
//            sleep(5);
//        }
//        while (threadPoolTaskExecutor.getActiveCount() > 0) {
//            System.out.println("sleep 5 milliseconds to wait, current active count is ${threadPoolTaskExecutor.activeCount}");
//            sleep(5);
//        }
//    }

}
