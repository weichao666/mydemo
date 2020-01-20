package servicecomb.springmvcserverc.java.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

//（1）Externalizable继承自Serializable接口
//（2）需要我们重写writeExternal()与readExternal()方法
//（3）实现Externalizable接口的类必须要提供一个public的无参的构造器
public class User1 implements Externalizable {
    private int age;
    private String name;

    public User1() {}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
    }
}
