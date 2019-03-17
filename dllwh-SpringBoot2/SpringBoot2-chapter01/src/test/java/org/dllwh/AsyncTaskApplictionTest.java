package org.dllwh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.dllwh.core.task.AsyncTask;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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