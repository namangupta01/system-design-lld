package algorithms;

import algorithms.exceptions.InvalidNodeException;

public class DoublyLinkedList<E> {

    DoublyLinkedListNode<E> head;
    DoublyLinkedListNode<E> tail;

    public DoublyLinkedList() {
        this.head = new DoublyLinkedListNode<>(null);
        this.tail = new DoublyLinkedListNode<>(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);
    }

    public DoublyLinkedListNode<E> addElementAtTheLast(E data) {
        DoublyLinkedListNode<E> newNode = new DoublyLinkedListNode<>(data);
        newNode.setPrev(tail.getPrev());
        tail.getPrev().setNext(newNode);
        newNode.setNext(tail);
        return newNode;
    }

    public void addNodeAtTheEnd(DoublyLinkedListNode<E> node) throws InvalidNodeException {
        if(node == null) {
            throw new InvalidNodeException();
        }
        this.tail.getPrev().setNext(node);
        node.setPrev(tail.getPrev());
        node.setNext(tail);
        tail.setPrev(node);
    }

    public DoublyLinkedListNode<E> getFirstNode() {
        if(head.getNext() == tail) {
            return null;
        }
        return head.getNext();
    }

    public DoublyLinkedListNode<E> getLastNode() {
        if(head.getPrev() == head) {
            return null;
        }
        return tail.getPrev();
    }

    public void detachNode(DoublyLinkedListNode<E> node) throws InvalidNodeException {
        if(node == null) {
            throw new InvalidNodeException();
        }
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }


}
