package hw1;

public class Box<T> {

    private T object;

    public Box(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Box{" +
                "obj=" + object +
                "; objType=" + object.getClass().getName() +
                '}';
    }
}
