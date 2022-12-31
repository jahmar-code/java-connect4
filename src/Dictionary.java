/**
 * this class implements a dictionary using a hash table in which collisions are resolved using separate chaining
 * the hash table will store objects of the class Record
 * @author Jawaad Ahmar 
 */

public class Dictionary implements DictionaryADT {

    // instance variables for the class Dictionary
    private Node[] hashTable;
    private int numRecords;


    // constructor initializes hash table and places objects of class Node in each element of the array
    public Dictionary (int size) {

        this.hashTable = new Node[size];

        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new Node();
        }
    }

    // put method inserts objects of class Record into hashtable
    public int put (Record rec) throws DuplicatedKeyException {
        
        int key  = polynomialHashFunction(rec.getKey());
        Node newNode = new Node(rec);
        Node curr = hashTable[key];

        if (curr.getNext() != null) {
            while (curr.getNext() != null) {
                curr = curr.getNext();
                    if (newNode.getElement().getKey().equals(curr.getElement().getKey())) {
                        throw new DuplicatedKeyException("duplicate record found");
                    }
            }
            curr.setNext(newNode);
            numRecords++;
            return 1;
        } else {
            hashTable[key].setNext(newNode);
            numRecords++;
            return 0;
        }
    }

    // remove method removes objects of class Record from hash table when given a key
    public void remove (String key) throws InexistentKeyException {

        int hash = polynomialHashFunction(key);
        Node curr = hashTable[hash];

        if (curr.getNext() == null) {
            throw new InexistentKeyException("record does not exist");
        }

        Node prev = curr;
        curr = curr.getNext();
        if (curr.getNext() == null) {
            prev.setNext(null);
        }

        while (curr.getNext() != null) {
            if (curr.getElement().getKey().equals(key)) {
                prev.setNext(curr.getNext());
            }
            prev = curr;
            curr = curr.getNext();
        }
    }

    // accessor method returns an object of class Record from the hash table when given a key
    public Record get(String key) {

        int hash = polynomialHashFunction(key);

        Node curr = hashTable[hash];
        while (curr.getNext() != null) {
            curr = curr.getNext();
            if (curr.getElement().getKey().equals(key)) {
                return curr.getElement();
            }
        }
        if (curr.getNext() == null) {
            return null;
        } else {
            if (curr.getElement().getKey().equals(key)) {
                return curr.getElement();
            } else {
                return null;
            }
        }
    }

    // accessor method returns the number of Record objects in the hash table
    public int numRecords() {
        return numRecords;
    }

    // method generates the hash function to minimize collisions in the hash table
    private int polynomialHashFunction (String key) {
        if (key.length() == 0) {
            return 0;
        }
        int M = 8999;
        int hash = (int)key.charAt(0);

        for (int i = 1; i < key.length(); i++) {
            hash += (hash * 10 + (int)(key.charAt(i))) % M;
        }

        hash = hash % this.hashTable.length;
        
        return hash;
    }
}
