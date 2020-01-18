package servicecomb.springmvcserverc.java.training.thread;

public class TestThreadlocal {
    //通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };
    //获取下一个序列值
    public int getNextNum() {
        threadLocal.set(threadLocal.get() + 1);
        return threadLocal.get();
    }

    public static void main(String[] args) throws Exception {
        TestThreadlocal testThreadlocal = new TestThreadlocal();
        //三个线程共享testThreadlocal,各自产生序列号
        TestClient testClient1 = new TestClient(testThreadlocal);
        TestClient testClient2 = new TestClient(testThreadlocal);
        TestClient testClient3 = new TestClient(testThreadlocal);
        testClient1.start();
        testClient2.start();
        testClient3.start();
    }
    private static class TestClient extends Thread {

        private TestThreadlocal testThreadlocal;

        public TestClient(TestThreadlocal testThreadlocal) {
            this.testThreadlocal = testThreadlocal;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                //每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                + testThreadlocal.getNextNum() + "]");
            }
        }
    }
}
