package algorithms;

public class DoublyLinkedListNode<E> {
    private DoublyLinkedListNode<E> prev;
    private DoublyLinkedListNode<E> next;
    private E element;

    public DoublyLinkedListNode(E data) {
        this.prev = null;
        this.next = null;
        this.element = data;
    }


    public void setElement(E element) {
        this.element = element;
    }

    public E getElement() {
        return this.element;
    }

    public void setPrev(DoublyLinkedListNode<E> prev) {
        this.prev = prev;
    }

    public DoublyLinkedListNode<E> getPrev() {
        return this.prev;
    }

    public void setNext(DoublyLinkedListNode<E> next) {
        this.next = next;
    }

    public DoublyLinkedListNode<E> getNext() {
        return this.next;
    }
}
