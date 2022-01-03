/**
 * @author Spencer Bertram
 * class representing a huffman code tree that can be used to decode bit strings into text strings
 */
public class HuffmanCodeTree {
    /* member variables */
    HuffmanNode root;  // the root node of the tree

    /**
     * instantiate a HuffmanCodeTree object with a root HuffmanNode object
     * @param root a HuffmanNode object representing the root of a Huffman code tree
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * instantiate a HuffmanCodeTree object from a given HuffmanCodeBook object
     * @param codeBook a HuffmanCodeBook object
     */
    public HuffmanCodeTree(HuffmanCodeBook codeBook) {
        root = new HuffmanNode();
        for (Character character : codeBook) {
            put(codeBook.getSequence(character), character);
        }
    }

    /**
     * check if the HuffmanCodeTree is valid in its current state
     * @return
     */
    public boolean isValid() {
        return root.isValid();
    }

    /**
     * store a character in the code tree by traversing the tree based on a given binary sequence
     * @param sequence a BinarySequence object that determines the character's location in the tree
     * @param character a char value to store according to the sequence value
     */
    public void put(BinarySequence sequence, char character) {
        if (root == null) {  // if the tree is empty
            root = new HuffmanNode();
        }
        HuffmanNode currNode = root;
        for (boolean bool : sequence) {  // for each bit in the binary sequence
            if (bool) {  // current bit is 1
                if (currNode.getOne() == null) {  // no 'one' child
                    currNode.setOne(new HuffmanNode());
                }
                currNode = currNode.getOne();
            } else {  // current bit is 0
                if (currNode.getZero() == null) {  // no 'zero' child
                    currNode.setZero(new HuffmanNode());
                }
                currNode = currNode.getZero();
            }
        }
        // at this point, the current node is what needs to store the given character value
        currNode.setData(character);
    }

    /**
     * given a binary sequence decode that sequence into the requisite characters via the huffman code tree
     * @param sequence a BinarySequence object to decode
     * @return a String: the decoded binary sequence
     */
    public String decode(BinarySequence sequence) {
        StringBuilder retString = new StringBuilder();
        HuffmanNode currNode = root;
        for (boolean bool : sequence) {
            if (bool) {  // current bit is a 1
                currNode = currNode.getOne();
            } else {  // current bit is a 0
                currNode = currNode.getZero();
            }
            if (currNode.isLeaf()) {  // check if there is data at the current node, decode accordingly
                retString.append(currNode.getData());
                currNode = root;
            }
        }
        return retString.toString();
    }
    public static void main(String[] args) {
        HuffmanCodeTree codeTree = new HuffmanCodeTree(ProvidedHuffmanCodeBook.getEbookHuffmanCodebook());
        BinarySequence sequence = new BinarySequence(FileIOAssistance.readFile("23-0.txt"));
        String decodedText = codeTree.decode(sequence);
        FileIOAssistance.writeFile("23-0-decoded", decodedText);
    }
}
