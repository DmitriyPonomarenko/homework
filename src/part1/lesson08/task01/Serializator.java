package part1.lesson08.task01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * класс, реализующий сериализацию и десериализацию "плоских" объектов (все поля объекта - примитивы, или String).
 */
public class Serializator {

    /**
     * сериализация объекта в файл
     *
     * @param object входной объект
     * @param file   имя файла
     * @throws IOException выбрасывается при отсутствии возможности завершить работу
     */
    public static void serialize(Object object, String file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            Class c = object.getClass();
            oos.writeUTF(c.getName());
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    switch (field.getType().getName()) {
                        case "byte":
                            oos.writeByte(field.getByte(object));
                            break;
                        case "short":
                            oos.writeShort(field.getShort(object));
                            break;
                        case "int":
                            oos.writeInt(field.getInt(object));
                            break;
                        case "long":
                            oos.writeLong(field.getLong(object));
                            break;
                        case "float":
                            oos.writeFloat(field.getFloat(object));
                            break;
                        case "double":
                            oos.writeDouble(field.getDouble(object));
                            break;
                        case "char":
                            oos.writeChar(field.getChar(object));
                            break;
                        case "boolean":
                            oos.writeBoolean(field.getBoolean(object));
                            break;
                        case "java.lang.String":
                            oos.writeObject(field.get(object));
                            break;
                        default:
                            throw new IOException(field.getType().getName() + ": тип не поддерживается");
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * десериализация объекта из файла
     *
     * @param file имя файла
     * @return десереализованный объект
     * @throws IOException выбрасывается при отсутствии возможности завершить работу
     */
    public static Object deSerialize(String file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Class c = Class.forName(ois.readUTF());
            Constructor constr = c.getDeclaredConstructors()[0];
            constr.setAccessible(true);
            List<Object> params = new ArrayList<>();
            for (Class<?> pType : constr.getParameterTypes()) {
                switch (pType.getName()) {
                    case "byte":
                        params.add((byte) 0);
                        break;
                    case "short":
                        params.add((short) 0);
                        break;
                    case "int":
                        params.add(0);
                        break;
                    case "long":
                        params.add((long) 0);
                        break;
                    case "float":
                        params.add((float) 0);
                        break;
                    case "double":
                        params.add((double) 0);
                        break;
                    case "char":
                        params.add((char) 0);
                        break;
                    case "boolean":
                        params.add(false);
                        break;
                    default:
                        params.add(null);
                }
            }
            Object obj = constr.newInstance(params.toArray());
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().getName()) {
                    case "byte":
                        field.setByte(obj, ois.readByte());
                        break;
                    case "short":
                        field.setShort(obj, ois.readShort());
                        break;
                    case "int":
                        field.setInt(obj, ois.readInt());
                        break;
                    case "long":
                        field.setLong(obj, ois.readLong());
                        break;
                    case "float":
                        field.setFloat(obj, ois.readFloat());
                        break;
                    case "double":
                        field.setDouble(obj, ois.readDouble());
                        break;
                    case "char":
                        field.setChar(obj, ois.readChar());
                        break;
                    case "boolean":
                        field.setBoolean(obj, ois.readBoolean());
                        break;
                    case "java.lang.String":
                        field.set(obj, ois.readObject());
                        break;
                    default:
                        throw new IOException(field.getType().getName() + ": тип не поддерживается");
                }
            }
            return obj;
        } catch (Exception e) {
            IOException exc = new IOException("Ошибка десериализации");
            exc.addSuppressed(e);
            throw exc;
        }
    }
}
