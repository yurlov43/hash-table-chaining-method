import java.util.ArrayList;

public class HashTable {

    public int size;
    public LinkedList2<String>[] slots;

    public HashTable(int sz) {
        size = sz;
        slots = new LinkedList2[size];
    }

    public int hashFun(String value) {
        return value.getBytes().length % size;
    }

    public int put(String value) {
        int slotIndex = hashFun(value);

        if (slots[slotIndex] == null) {
            slots[slotIndex] = new LinkedList2<>();
        }

        slots[slotIndex].addInTail(new Node<>(value));
        return slotIndex;
    }

    public int find(String value) {
        int slotIndex = hashFun(value);

        if (slots[slotIndex] != null && slots[slotIndex].find(value) != null) {
            return slotIndex;
        }
        return -1;
    }
}

class LinkedList2<T> {

    public Node<T> head;
    public Node<T> tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void deleteNode(Node<T> node) {
        if (node == this.head) {
            if (node.next == null) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = node.next;
                this.head.prev = null;
            }
        } else {
            if (node.next == null) {
                this.tail = node.prev;
                this.tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
    }

    public void addInTail(Node<T> item) {
        if (head == null) {
            this.head = item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = item;
            item.prev = tail;
        }
        this.tail = item;
    }

    public Node<T> find(T value) {
        Node<T> node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node<T>> findAll(T value) {
        ArrayList<Node<T>> nodes = new ArrayList<Node<T>>();
        Node<T> node = this.head;
        while (node != null) {
            if (node.value == value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(T value) {
        Node<T> node = this.head;

        while (node != null) {
            if (node.value == value) {
                deleteNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void removeAll(T value) {
        while (remove(value)) {

        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public int count() {
        Node<T> node = this.head;
        int length = 0;

        while (node != null) {
            length = length + 1;
            node = node.next;
        }
        return length;
    }

    public void insertAfter(Node<T> nodeAfter, Node<T> nodeToInsert) {
        Node<T> node = this.head;

        if (node == null) {
            this.head = nodeToInsert;
            this.tail = nodeToInsert;
        }

        while (node != null) {
            if (node == nodeAfter) {
                nodeToInsert.next = node.next;
                if (node.next == null) {
                    this.tail = nodeToInsert;
                } else {
                    node.next.prev = nodeToInsert;
                }
                node.next = nodeToInsert;
                nodeToInsert.prev = node;
            }
            if (nodeAfter == null) {
                nodeToInsert.next = node;
                node.prev = nodeToInsert;
                this.head = nodeToInsert;
                break;
            }
            node = node.next;
        }
    }
}

class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}