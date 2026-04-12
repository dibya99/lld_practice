package lrucache.entities;

public class DoublyLinkedList <K,V>{
    private Node<K,V> head;
    private Node<K,V> tail;

    public DoublyLinkedList()
    {
        head = new Node<>(null,null);
        tail = new Node<>(null,null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public Node<K, V> getHead() {
        return head;
    }

    public void setHead(Node<K, V> head) {
        this.head = head;
    }

    public Node<K, V> getTail() {
        return tail;
    }

    public void setTail(Node<K, V> tail) {
        this.tail = tail;
    }

    public void addFirst(Node<K,V> node)
    {
        node.setNext(head.getNext());
        node.setPrev(head);
        head.setNext(node);
        node.getNext().setPrev(node);
    }

    public void remove(Node <K,V> node)
    {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node.setPrev(null);
        node.setNext(null);
    }

    public void moveToFront(Node <K,V> node)
    {
        remove(node);
        addFirst(node);
    }

    @Override
    public String toString() {
        String dll="";
        Node<K,V> temp = head;
        while(temp!=null)
        {
            dll += temp.toString()+" ";
            temp=temp.getNext();
        }
        return dll;

    }

    public Node<K,V> removeLast()
    {
        if(tail.getPrev() == head)
            return null;

        Node<K,V> removedNode = tail.getPrev();
        removedNode.getNext().setPrev(removedNode.getPrev());
        removedNode.getPrev().setNext(removedNode.getNext());

        removedNode.setNext(null);
        removedNode.setPrev(null);

        return removedNode;
    }
}
