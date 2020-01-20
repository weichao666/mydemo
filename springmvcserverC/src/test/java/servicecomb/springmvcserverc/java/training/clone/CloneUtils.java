package servicecomb.springmvcserverc.java.training.clone;

import java.io.*;

public class CloneUtils {
    public static <T> T clone(T obj) {
        T cloneObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            //ObjectOutputStream——用来向字节流中写入对象
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();

            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            //返回生成的新对象
            cloneObj = (T) objectInputStream.readObject();
            objectInputStream.close();
            // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
            // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
