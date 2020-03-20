import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void hashFun() {
        int size = 115;

        HashTable hashTable = new HashTable(size);
        Assert.assertEquals(hashTable.hashFun("java"), 4);
    }

    @Test
    public void put() {
        int size = 38;

        HashTable hashTable = new HashTable(size);
        for (int i = 0; i < size; i += 1) {
            hashTable.put("java");
        }
        Assert.assertEquals(hashTable.slots[hashTable.hashFun("java")].count(), size);
    }

    @Test
    public void find() {
        int size = 55;

        HashTable hashTable = new HashTable(size);
        hashTable.put("Java");
        hashTable.put("Haskell");
        hashTable.put("Python");
        hashTable.put("Go");
        Assert.assertEquals(hashTable.find("Java"), hashTable.hashFun("Java"));
        Assert.assertEquals(hashTable.find("Haskell"), hashTable.hashFun("Haskell"));
        Assert.assertEquals(hashTable.find("Python"), hashTable.hashFun("Python"));
        Assert.assertEquals(hashTable.find("Go"), hashTable.hashFun("Go"));
        Assert.assertEquals(hashTable.find("C++"), -1);
    }
}