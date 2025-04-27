package cache.storage;

import cache.exceptions.InvalidKeyException;
import cache.exceptions.InvalidStorageCapacity;
import cache.exceptions.StorageFullException;

import java.util.HashMap;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {
    private final HashMap<Key, Value> map;
    private final Integer capacity;

    public HashMapStorage(Integer capacity) throws InvalidStorageCapacity {
        this.map = new HashMap<>();
        if(capacity < 1) throw new InvalidStorageCapacity();
        this.capacity = capacity;
    }

    public void put(Key key, Value value) throws StorageFullException {
        if(map.size() >= capacity && !map.containsKey(key)) {
            throw new StorageFullException();
        }
        map.put(key, value);
    }

    public Value get(Key key) throws InvalidKeyException {
        if(!map.containsKey(key)) {
            throw new InvalidKeyException();
        }
        return map.get(key);
    }

    public Value remove(Key key) throws InvalidKeyException {
        if(!map.containsKey(key)) {
            throw new InvalidKeyException();
        }
        return map.remove(key);
    }

    public boolean hasKey(Key key) {
         return this.map.containsKey(key);
    }
}
