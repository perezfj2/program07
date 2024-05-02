

// Student Name Fernando Perez
// Date 04/26/2023
// CSCI 3302 Section 001
//
// Files: HashTable.java, DictionaryException.java
//
// Description: This is a Dictionary ADT using an array-based hash table. It uses a (key, value) pair
//              to store items into the hash table.

public class HashTable implements IDictionary {

    private final int NUM_BINS = 101;
    private Node[][] table;
    
    public HashTable(){
        this.table = new Node[NUM_BINS][];
    }

    /**
     * This method returns the boolean value of isEmpty(). It checks if the hash table is empty by looping through it and making sure
     * there is no (key, value) stored
     */
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < table.length; i++) {
            Node[] bucket = table[i];
            if (bucket != null) {
                for (int j = 0; j < bucket.length; j++) {
                    Node node = bucket[j];
                    if (node != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @param - key - This is unique String type
     * @param - value - Non unique String 
     * 
     * This method adds a new (key, value) pair to the hash table. If the key in the pair is unique it will add were it fits.
     * If the key in the pair is not unique, then the method will remove the pair and insert the new pair in its place
     * It will also resize the hash table when the bucket is full, and add all the pairs into the new bucket array
     */
    @Override
    public void add(String key, String value) {
       int hash = Math.abs(key.hashCode()) % NUM_BINS;
       Node[] bucket = table[hash];

       if(bucket == null){
            bucket = new Node[2];
            bucket[0] = new Node(key, value);
            table[hash] = bucket;
            return;
       }

       for(int i = 0; i < bucket.length; i++) {
            if(bucket[i] == null){
                bucket[i] = new Node(key, value);
                return;
            } else if(bucket[i].getKey().equals(key)){
                bucket[i] = new Node(key, value);
                return;
            }
       }

       Node[] newBucket = new Node[bucket.length * 2];
       for(int i = 0; i < bucket.length; i++) {
            newBucket[i] = bucket[i];
       }

       newBucket[bucket.length] = new Node(key, value);
       table[hash] = newBucket;

    }
        

    /**
     * @param - key - Unique value of type String
     * 
     * This remove method removes a key, value pair at the designated key.
     * If the array is less than a 1/4 full then the remove method will resize the array that is half the size of the original array
     * It also throws a DictionaryException if the key is not in the hash table
     */
    @Override
    public void remove(String key) throws DictionaryException {
        int hash = Math.abs(key.hashCode()) % NUM_BINS;
        Node[] bucket = table[hash];

        if(bucket != null){
            for(int i = 0; i < bucket.length; i++){
                Node node = bucket[i];
                if(node != null && node.getKey().equals(key)){
                    bucket[i] = null;

                    if(bucket.length > 2 && bucket.length / 4 >= i){
                        int newLength = bucket.length / 2;
                        Node[] newBucket = new Node[newLength];
                        for (int j = 0; j < i; j++){
                            newBucket[j] = bucket[j];
                        }
                        for(int j = i + 1; j < bucket.length; j++){
                            newBucket[j - 1] = bucket[j];
                        }
                        table[hash] = newBucket;
                    }
                    return;
                }
            }
        }

        throw new DictionaryException("Key not found: " + key);
    }

    /**
     * @param - key - Unique value of type String
     * 
     * This method retrieves the value of a given key
     * If the key provided does not exist then a exception is thrown
     */
    @Override
    public String retrieve(String key) throws DictionaryException {
        int hash = Math.abs(key.hashCode()) % NUM_BINS;
        Node[] bucket = table[hash];

        if(bucket != null){
            for(Node node: bucket){
                if(node != null && node.getKey().equals(key)){
                    return node.getValue();
                }
            }
        }

        throw new DictionaryException("Key not found: " + key);
    }

    /**
     * This method clears the dictionary by setting all index to null
     */
    @Override
    public void clearDictionary() {
        for(int i = 0; i < table.length; i++){
            table[i] = null;
        }
    }

    
} // end class
