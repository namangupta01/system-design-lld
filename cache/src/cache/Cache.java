package cache;

import algorithms.exceptions.InvalidNodeException;
import cache.exceptions.InvalidKeyException;
import cache.exceptions.InvalidOperationException;
import cache.exceptions.KeyAlreadyExistException;
import cache.exceptions.StorageFullException;
import cache.policies.EvictionPolicy;
import cache.storage.Storage;

public class Cache<Key, Value> {

    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public Value get(Key key) {
        try {
            return storage.get(key);
        } catch (InvalidKeyException e) {
            return null;
        }
    }

    public void put(Key key, Value value) throws InvalidOperationException {
        if (this.storage.hasKey(key)) {
            try {
                this.storage.put(key, value);
                this.evictionPolicy.accessed(key);
                return;
            } catch (StorageFullException | InvalidNodeException | InvalidKeyException e) {
                throw new RuntimeException();
            }
        }

        try {
            this.storage.put(key, value);
            this.evictionPolicy.addKey(key);
        } catch (StorageFullException e) {
            Key evictedKey = this.evictionPolicy.evict();
            if (evictedKey == null) {
               throw new InvalidOperationException();
            }
            try {
                storage.remove(evictedKey);
                this.storage.put(key, value);
                this.evictionPolicy.addKey(key);
            } catch (InvalidKeyException | StorageFullException | KeyAlreadyExistException ex ) {
                throw new RuntimeException(ex);
            }
        } catch (KeyAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Key key) throws InvalidKeyException {
        if (!this.storage.hasKey(key)) {
            throw new InvalidKeyException();
        }
        this.storage.remove(key);
        this.evictionPolicy.removeKey(key);
    }
}
