/**
 * this class represents a node in a linked list
 * @author Jawaad Ahmar
 */

public class Node {

    // instance variables for class Node
    private Node next;
    private Record element;
    
    // constructor initializes instance variables
    public Node() {
        next = null;
        element = null;
    }
    
    // constructor initializes instance variables when given an input
    public Node (Record elem) {
        next = null;
        element = elem;
    }
    
    // accessor method returns the next node
    public Node getNext() {
        return next;
    }
    
    // mutator method sets the next node 
    public void setNext (Node node) {
        next = node;
    }

    // accessor method gets the element within the node
    public Record getElement() {
        return element;
    }
    
    // mutator method sets the element within the node
    public void setElement (Record elem) {
        element = elem;
    }
}

