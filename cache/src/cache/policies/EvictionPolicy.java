package cache.policies;

import algorithms.exceptions.InvalidNodeException;
import cache.exceptions.InvalidKeyException;
import cache.exceptions.KeyAlreadyExistException;

public interface EvictionPolicy<Key> {

    public void accessed(Key key) throws InvalidKeyException, InvalidNodeException;

    public void addKey(Key key) throws KeyAlreadyExistException;

    public Key evict();

    public void removeKey(Key key) throws InvalidKeyException;

}
