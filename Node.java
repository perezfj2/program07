// Student Name
// Date
// CSCI 3302 Section 001
//
// Files:
//
// Description: Node class to store a key of type String and
// a value that is also of type String.

public class Node {

  // Attributes
  private String key; // holds a reference to the node key
  private String value; // holds a reference to the node value

  /**
   * Constructor creates and returns a reference to a Node.
   * @param newKey  - reference to String for Node key
   * @param newValue - reference to the Stringvalue for the Node
   */
  public Node(String newKey, String newValue) {
    this.key = newKey;
    this.value = newValue;
  }

  /**
   * Simple getter to return the Node key.
   * @return - a reference to the Node key (String) is
   * returned
   */
  public String getKey() {
    return this.key;
  }

  /**
   * Simple getter to return the value held
   * by the node.
   * @return - a reference to the Node value (String)
   * is returned
   */
  public String getValue() {
    return this.value;
  }
}
