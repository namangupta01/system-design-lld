package cache.storage;

import cache.exceptions.InvalidKeyException;
import cache.exceptions.StorageFullException;

public interface Storage<Key, Value> {

    public Value get(Key key) throws InvalidKeyException;
    public void put(Key key, Value value) throws StorageFullException;
    public Value remove(Key key) throws InvalidKeyException;
    public boolean hasKey(Key key);
}
