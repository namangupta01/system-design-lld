package cache.policies;

import algorithms.DoublyLinkedList;
import algorithms.DoublyLinkedListNode;
import algorithms.exceptions.InvalidNodeException;
import cache.exceptions.InvalidKeyException;
import cache.exceptions.KeyAlreadyExistException;

import java.util.HashMap;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    DoublyLinkedList<Key> doublyLinkedList;
    HashMap<Key, DoublyLinkedListNode<Key>> keyNodesMap;

    public LRUEvictionPolicy() {
        doublyLinkedList = new DoublyLinkedList<>();
        keyNodesMap = new HashMap<>();
    }


    @Override
    public void accessed(Key key) throws InvalidKeyException {
        if(!keyNodesMap.containsKey(key)) {
            throw new InvalidKeyException();
        }
        try {
            DoublyLinkedListNode<Key> node = keyNodesMap.get(key);
            doublyLinkedList.detachNode(node);
            doublyLinkedList.addNodeAtTheEnd(node);
        } catch (InvalidNodeException e) {
                throw new InvalidKeyException();
        }
    }

    @Override
    public void addKey(Key key) throws KeyAlreadyExistException {
        if(keyNodesMap.containsKey(key)) {
            throw new KeyAlreadyExistException();
        }
        DoublyLinkedListNode<Key> node = doublyLinkedList.addElementAtTheLast(key);
        keyNodesMap.put(key, node);
    }

    @Override
    public Key evict() {
        DoublyLinkedListNode<Key> firstNode = doublyLinkedList.getFirstNode();
        if (firstNode != null) {
            try {
                doublyLinkedList.detachNode(firstNode);
                keyNodesMap.remove(firstNode.getElement());
                return firstNode.getElement();
            }
            catch (InvalidNodeException e) {
                //
            }
        }
        return null;
    }

    public void removeKey(Key key) throws InvalidKeyException {
        if(!keyNodesMap.containsKey(key)) {
            throw new InvalidKeyException();
        }
        try {
            DoublyLinkedListNode<Key> node = keyNodesMap.get(key);
            doublyLinkedList.detachNode(node);
            keyNodesMap.remove(key);
        }catch (InvalidNodeException ex) {
            throw new InvalidKeyException();
        }
    }
}
