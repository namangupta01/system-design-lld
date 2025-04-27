package cache.factories;

import cache.Cache;
import cache.exceptions.InvalidStorageCapacity;
import cache.policies.LRUEvictionPolicy;
import cache.storage.HashMapStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> getCache() throws RuntimeException {
        try {
            return new Cache<Key, Value>(new HashMapStorage<Key, Value>(2), new LRUEvictionPolicy<Key>());
        } catch (InvalidStorageCapacity e) {
            throw new RuntimeException(e);
        }

    }
}
