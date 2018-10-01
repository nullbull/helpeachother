package com.heo.common.Serializer;

import java.io.*;

/**
 * @Auth justinniu
 * @Date 2018/9/4
 * @Desc
 */
public class BeanUtil {
    private BeanUtil() {}

    public static byte[] bean2Byte(Object object) {
        byte[] temp = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            temp = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static Object byte2Object(byte[] bytes) {
        Object temp = null;
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            temp = objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }


}
