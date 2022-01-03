import java.util.Iterator;

/**
 * @author Spencer Bertram
 * a class representing a Huffman code book, which can be used to encode strings into binary sequences
 */
public class HuffmanCodeBook implements Iterable<Character> {
    /* member variables */
    LookupNode root;  // the root node of the internal binary search tree structure

    /**
     * recursively check whether a given character is in the internal binary tree structure
     * @param node the root node of the current sub tree being checked
     * @param character the character being searched for in the tree
     * @return a boolean: true if the character is in the tree, otherwise false
     */
    private boolean inTree(LookupNode node, char character) {
        if (node == null) {  // node is null, character isn't in
            return false;
        } else if (node.getCharacter() == character) {  // current node stores the desired character
            return true;
        } else if (character < node.getCharacter()) {  // character is less than character at current node
            return inTree(node.getLeft(), character);
        } else {  // character is greater than (or equal to) character at current node
            return inTree(node.getRight(), character);
        }
    }

    /**
     * instantiate an empty HuffmanCodeBook object
     */
    public HuffmanCodeBook() {
        root = null;
    }

    /**
     * add a character-sequence pair to the code book
     * @param character a char value to store
     * @param sequence a BinarySequence object representing the associated sequence to the character
     */
    public void addSequence(char character, BinarySequence sequence) {
        if (root == null) {  // tree is empty, insert as root node
            root = new LookupNode(character, sequence);
        } else {  // tree isn't empty
            LookupNode currNode = root;
            while (currNode != null) {  // currNode will be set to null after inserting new node
                if (character < currNode.getCharacter()) {  // needs to be in the left sub tree
                    if (currNode.getLeft() == null) {  // left child is null
                        currNode.setLeft(new LookupNode(character, sequence));
                        currNode = null;
                    } else {  // left child isn't null, traverse more
                        currNode = currNode.getLeft();
                    }
                } else {  // needs to be in the right sub tree
                    if (currNode.getRight() == null) {  // right child is null
                        currNode.setRight(new LookupNode(character, sequence));
                        currNode = null;
                    } else {
                        currNode = currNode.getRight();
                    }
                }
            }
        }
    }

    /**
     * check whether the given character exists as a character-sequence entry in the code book
     * @param character a char value
     * @return a boolean: true if the character is in the book, false if it isn't
     */
    public boolean contains(char character) {
        return inTree(root, character);
    }

    /**
     * check if the code book contains all the characters in a given string
     * @param letters a String object
     * @return a boolean: true if all characters in the string are in the code book, otherwise false
     */
    public boolean containsAll(String letters) {
        for (int i = 0; i < letters.length(); i++) {  // for every character in the string
            char currChar = letters.charAt(i);
            if (!contains(currChar)) {
                return false;  // the current character isn't in the code book, return false
            }
        }
        return true;  // every character is in the code book, return true
    }

    /**
     * get the binary sequence associated with a given character in the code book. If the character isn't in the code
     * book, null is returned
     * @param character a char value to find in the code book
     * @return a BinarySequence object representing the associated binary sequence with the given character
     */
    public BinarySequence getSequence(char character) {
        if (root == null) {  // tree is empty
            return null;
        } else {  // tree isn't empty
            LookupNode currNode = root;
            while (true) {  // internal return statements will terminate the loop
                if (character == currNode.getCharacter()) {  // current node stores the desired character
                    return currNode.getSequence().clone();  // return deep copy of sequence
                } else if (character < currNode.getCharacter()) {  // character would be in the left sub tree
                    if (currNode.getLeft() == null) {  // no left child
                        return null;  // character isn't in tree
                    } else {
                        currNode = currNode.getLeft();
                    }
                } else {  // character would be in the right sub tree
                    if (currNode.getRight() == null) {  // no right child
                        return null;  // character isn't in tree
                    } else {
                        currNode = currNode.getRight();
                    }
                }
            }
        }
    }

    /**
     * get a binary sequence by converting a given string via the code book
     * @param letters a String object to be parsed and converted to a binary sequence
     * @return a BinarySequence object representing the converted string
     */
    public BinarySequence encode(String letters) {
        BinarySequence retSequence = new BinarySequence();
        for (int i = 0; i < letters.length(); i++) {  // loop over every character
            char currChar = letters.charAt(i);  // note: charAt() runs in O(1)
            retSequence.append(getSequence(currChar));  // get the associated sequence and append to the end of retSeq
        }
        return retSequence;
    }

    /**
     * Returns an iterator over elements of type Character.
     * @return an Iterator.
     */
    @Override
    public Iterator<Character> iterator() {
        return new HuffmanCodeBookIterator();
    }

    private class HuffmanCodeBookIterator implements Iterator<Character> {

        private NodeArrayList characterList;
        private int currIndex;

        /**
         * recursively populate nodeList with the nodes in the internal tree inorder
         * @param node the current LookupNode object being visited in the tree
         */
        private void createList(LookupNode node) {
            if (node == null) {  // the base case - past the leaves of the tree
                return;
            }

            createList(node.getLeft());  // first append all nodes in the left subtree
            characterList.append(node);  // append the current node
            createList(node.getRight());  // append all nodes in the right subtree
        }

        /**
         * initialize an object of type HuffmanCodeBookIterator
         */
        public HuffmanCodeBookIterator() {
            characterList = new NodeArrayList();
            createList(root);  // turn the binary tree's elements into an inorder list
            currIndex = 0;  // current visiting index is initialized to 0
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return (currIndex < characterList.size());
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public Character next() {
            Character retVal = characterList.at(currIndex);
            currIndex++;
            return retVal;
        }

        /**
         * a list ADT with internal array representation to store the tree's character in order
         */
        private class NodeArrayList {
            /* member variables */
            private Character[] characters;  // array of characters
            private int numVals;

            /**
             * double the size of the internal arrays storage
             */
            private void doubleArraySizes() {
                Character[] tempCharArray = new Character[characters.length * 2];
                for (int i = 0; i < characters.length; i++) {
                    tempCharArray[i] = characters[i];
                }
                characters = tempCharArray;
            }

            /**
             * instantiate a new ArrayList object
             */
            public NodeArrayList() {
                characters = new Character[4];
                numVals = 0;
            }

            /**
             * append a node to the end of the array list
             * @param node a LookupNode object to append to the end of the list
             */
            public void append(LookupNode node) {
                if (numVals >= characters.length) {
                    doubleArraySizes();
                }
                characters[numVals] = node.getCharacter();
                numVals++;
            }

            /**
             * get the size of the list
             * @return an int representing the number of characters in the list
             */
            public int size() {
                return numVals;
            }

            /**
             * get the Character object at a given index
             * @param index an int representing the index to get an element
             * @return a Character object
             */
            public Character at(int index) {
                return characters[index];
            }
        }
    }

    /**
     * a class representing a node of the binary search tree used to store code values. These nodes store a character
     * and its associated binary sequence
     */
    private class LookupNode {
        /* member variables */
        char character;  // the character stored at the node
        BinarySequence sequence;  // the sequence associated with the given character
        LookupNode left;  // the left child node
        LookupNode right;  // the right child node

        /**
         * instantiate a LookupNode object with a character and binary sequence. Both the left and right child nodes are
         * set as null
         * @param character a char value to set as the character value at the node
         * @param sequence a BinarySequence object to set as the binary sequence value at the node
         */
        public LookupNode(char character, BinarySequence sequence) {
            this.character = character;
            this.sequence = sequence;
            this.right = null;
            this.left = null;
        }

        /**
         * instantiate a LookupNode object with a character, binary sequence, and left and right child nodes
         * @param character a char value to set as the character value at the node
         * @param sequence the BinarySequence object to set as the binary sequence value at the node
         * @param left a LookupNode object to set as the left child of this node
         * @param right a LookupNode objet to set as the right child of this node
         */
        public LookupNode(char character, BinarySequence sequence, LookupNode left, LookupNode right) {
            this.character = character;
            this.sequence = sequence;
            this.left = left;
            this.right = right;
        }

        /**
         * get the character stored at the node
         * @return a char
         */
        public char getCharacter() {
            return character;
        }

        /**
         * set the character at the node
         * @param character the char value to store at the node
         */
        public void setCharacter(char character) {
            this.character = character;
        }

        /**
         * get the binary sequence stored at the node
         * @return the BinarySequence object stored at the node
         */
        public BinarySequence getSequence() {
            return sequence;
        }

        /**
         * set the binary sequence stored at the node
         * @param sequence the BinarySequence object to store at the node
         */
        public void setSequence(BinarySequence sequence) {
            this.sequence = sequence;
        }

        /**
         * get the right child node of this node
         * @return the LookupNode object that is the right child of this node
         */
        public LookupNode getRight() {
            return right;
        }

        /**
         * set the right child node of this node
         * @param node a LookupNode object to set as the right child of this node
         */
        public void setRight(LookupNode node) {
            right = node;
        }

        /**
         * get the left child node of this node
         * @return the LookupNode object that is the left child of this node
         */
        public LookupNode getLeft() {
            return left;
        }

        /**
         * set the left child node of this node
         * @param node a LookupNode object to set as the left child of this node
         */
        public void setLeft(LookupNode node) {
            left = node;
        }

        /**
         * get a deepcopy of the current node
         * @return a new LookupNode object with the same values as the old node
         */
        public LookupNode deepcopy() {
            return new LookupNode(character, sequence, left, right);
        }

        /**
         * get the node in a string format
         * @return a String object of the form: <character>:<sequence>
         */
        public String toString() {
            return character + ":" + sequence.toString();
        }
    }

    public static void main(String[] args) {
        HuffmanCodeBook testBook = new HuffmanCodeBook();
        testBook.addSequence('b', new BinarySequence("001"));
        testBook.addSequence('a', new BinarySequence("01"));
        testBook.addSequence('c', new BinarySequence("1"));

        for (Character character : testBook) {
            System.out.println(character);
        }
    }
}
