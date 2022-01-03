/**
 * @author Spencer Bertram
 * a class representing a Huffman code tree node - storing a character value, and two children representing zero and
 * one paths within the code tree
 */
public class HuffmanNode {
    /* member variables */
    private Character character;  // the character stored at the node
    private HuffmanNode zero;  // the zero node that this node points to
    private HuffmanNode one;  // the one node that this node points to

    /**
     * given a HuffmanNode object, return whether the subtree it is the root of is valid
     * @param root a HuffmanNode object
     * @return a boolean: true if the subtree is valid, false if it is not
     */
    private boolean validSubTree(HuffmanNode root) {
       if (root.zero == null && root.one == null) {  // node has no children
           return root.character != null;
       } else if (root.zero != null && root.one != null) {  // node has two children
           if (root.character == null) {  // doesn't store a value
               return validSubTree(root.zero) && validSubTree(root.one);
           } else {
               return false;
           }
       } else {  // node has one child
           return false;
       }
    }

    /**
     * instantiate a HuffmanNode object with no parameters - all member variables are set to null
     */
    public HuffmanNode() {
        character = null;
        zero = null;
        one = null;
    }

    /**
     * instantiate a HuffmanNode object with only a character value - the member variables representing the
     * child nodes of this node are set to null
     * @param character the char value to store in the node
     */
    public HuffmanNode(char character) {
        this.character = character;
        this.zero = null;
        this.one = null;
    }

    /**
     * instantiate a HuffmanNode object with two children values - both can be null
     * @param zero a HuffmanNode object to set as the 'zero' child of this node
     * @param one a HuffmanNode object to set as the 'one' child of this node
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
    }

    /**
     * instantiate a HuffmanNode object with a character value and two children HuffmanNode objects (either
     * of these 'child' nodes may be null)
     * @param character the char value to store in the huffman node
     * @param zero the child node representing the next digit of the path's binary sequence being zero
     * @param one the child node representing the next digit of the path's binary sequence being one
     */
    public HuffmanNode(char character, HuffmanNode zero, HuffmanNode one) {
        this.character = character;
        this.zero = zero;
        this.one = one;
    }

    /**
     * get the child node that represents the next digit in the binary sequence being zero
     * @return a HuffmanNode object representing the 'zero' child of this node
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * set the 'zero' child of this node to a given HuffmanNode object
     * @param zero a HuffmanNode object representing the next digit of the path's binary sequence being zero
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * get the child node that represents the next digit in the binary sequence being one
     * @return a HuffmanNode object representing the 'one' child of this node
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * set the 'one' child of this node to a given HuffmanNode object
     * @param one a HuffmanNode object representing the next digit of the path's binary sequence being one
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * get the char value stored in this node
     * @return a Character object
     */
    public Character getData() {
        return character;
    }

    /**
     * set the char value stored in this node
     * @param character a char value
     */
    public void setData(char character) {
        this.character = character;
    }

    /**
     * check if this node is a leaf node
     * @return a boolean: true if this node is a leaf node, false if it is not
     */
    public boolean isLeaf() {
        return character != null;  // a HuffmanNode either stores a character value, or is a leaf node
    }

    /**
     * check if this node and all it's descendants are valid nodes. A valid huffman node fits into one of the two
     * categories: 1) its stored char value is null and it has two children nodes, or 2) it stores a char value and
     * both of its children nodes are null
     * @return a boolean: true if the node and all it's descendants are valid, otherwise false
     */
    public boolean isValid() {
        return validSubTree(this);
    }
}
