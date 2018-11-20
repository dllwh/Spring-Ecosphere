package com.cdeledu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdeledu.core.task.AsyncTask;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApplicationWebInit.class)
public class AsyncTaskApplictionTest {
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private AsyncTask asyncTask;
	/** ----------------------------------------------------- Fields end */
	@Test
	public void test() throws Exception {
		asyncTask.doTaskOne();
		asyncTask.doTaskTwo();
		asyncTask.doTaskThree();
	}
}