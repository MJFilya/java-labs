public class Storage<T> {

    private final T[] items;
    private int size;

    public Storage(T[] buffer) {
        if (buffer == null || buffer.length == 0) {
            throw new IllegalArgumentException("Массив должен быть длиной больше нуля");
        }
        items = buffer;
    }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (size == items.length) {
            throw new IllegalStateException("Хранилище заполнено, вместимость " + items.length);
        }
        items[size] = value;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    public void set(int index, T value) {
        checkIndex(index);
        if (value == null) {
            throw new IllegalArgumentException("Нельзя записать null");
        }
        items[index] = value;
    }

    public T remove(int index) {
        checkIndex(index);
        T removed = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        items[size] = null;
        return removed;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("[");
        for (int i = 0; i < items.length; i++) {
            if (i > 0) {
                text.append(", ");
            }
            text.append(items[i]);
        }
        return text.append(']').toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс " + index + ", элементов в хранилище: " + size);
        }
    }
}
