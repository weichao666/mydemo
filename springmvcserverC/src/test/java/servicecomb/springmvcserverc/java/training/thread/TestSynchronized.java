package servicecomb.springmvcserverc.java.training.thread;

import org.apache.commons.lang.StringUtils;

public class TestSynchronized {

    public static void main(String[] args) throws Exception {
        TestSynchronized testSynchronized1 = new TestSynchronized();
        TestSynchronized testSynchronized2 = new TestSynchronized();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testSynchronized1.test("a");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testSynchronized2.test("b");
            }
        }).start();
    }

    //普通变量，test如果要使用，test方法不能是static，testSynchronized1和testSynchronized2是不同的对象，各自锁各自的对象，
    // 所以结果是：i am: a; num = 100，i am: b; num = 200，
//    private int num;

    //变量为static，共享了静态变量num，但是synchronized分别持有testSynchronized1和testSynchronized2这两个对象的锁，
    // 如果下面的test方法不加static，则结果为i am: a; num = 100，i am: b; num = 100
    // 或i am: a; num = 200，i am: b; num = 200，发现num并没有被锁，因为是静态变量，还是被两个线程共享了
    private static int num;

    //方法为static时，锁是加在类上，这个类的所有对象竞争一把锁，所以结果是i am: a; num = 100，i am: b; num = 200
    private synchronized static void test(String param){
        if (StringUtils.equals(param, "a")) {
            num = 100;
            System.out.println("set " + param + " num over");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
            System.out.println("set " + param + " num over");
        }
        System.out.println("i am: " + param + "; num = " + num);
    }
    //结论：synchronized修饰不加static的方法，锁是加在单个对象上，不同的对象没有竞争关系；
    //修饰加了static的方法，锁是加在类上，这个类所有的对象竞争一把锁。
}
