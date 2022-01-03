import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.Iterator;

/**
 * @Author Mark Nie, with minor edits by Daniel Kluver
 * This class represents a series of boolean values (true/false)
 *
 * In several places in this project we're going to need a way to represent series of bits (0/1)
 * This class is intended to represent this idea, where 1 = true and 0 = false.
 * As with all classes you're given and asked to use, DO NOT get lost in the private code for the
 * class, focus on method names and javadocs.
 *
 * This class is a bit simplified over a full features LIST behavior -- you can add bits to it,
 * but you can't really modify the bits after-the-fact. That should be find for our purposes.
 *
 * Internally, this class is mostly a specialization over java BitSet class, which also
 * represents a series of bits, but is kind of awful to use. Full credit to Mark Nie for making
 * it work.
 */
public class BinarySequence implements Iterable<Boolean>, Cloneable{
    private BitSet bits;
    private int count;

    /**
     * create a new empty binary sequence
     */
    public BinarySequence() {
        this.bits = new BitSet();
        count = 0;
    }

    /**
     * Create a binary sequence from the inefficient/not-compact "string" representation.
     * (I.E. "0100100011010" represented as a string))
     */
    public BinarySequence(String bitstring) {
        this.bits = new BitSet();
        for (int i = 0; i < bitstring.length(); i++) {
            append('1'==bitstring.charAt(i));
        }
    }

    /**
     * get the size (number of booleans) stored in this sequence.
     */
    public int size() {
        return count;
    }

    /**
     * add one boolean to the end of this sequence.
     */
    public void append(boolean bit) {
        // Did set(counter+1) here so that the length of bitset is counted correctly. Otherwise it will only count to the last '1' bit
        bits.set(count + 1);
        if (bit) {
            bits.set(count);
        } else {
            bits.clear(count);
        }
        this.count += 1;
    }

    /**
     * helper function to add one/true to the end of this sequence
     */
    public void appendOne() {
        append(true);
    }

    /**
     * helper function to add zero/false to the end of this sequence
     */
    public void appendZero() {
        append(false);
    }

    /**
     * add many booleans to the end of this sequence (as determined by the input binary sequence sequence)
     */
    public void append(BinarySequence other) {
        for (int i = 0; i < other.size(); i++) {
            append(other.get(i));
        }
    }

    /**
     * get the boolean ad a given index into the boolean sequence.
     */
    public Boolean get(int index) {
        return bits.get(index);
    }


    /////////////////////////////////////////////////////
    // FILE IO FUNCTIONS
    /////////////////////////////////////////////////////


    /**
     * Write this binary sequence to the given file.
     * I recommend naming files for binary sequences names ending in ".enc" to help you
     * keep track of how a file is formatted.
     * @param filePath -- the filePath can just be the name of a text file, or it can be the full path of the file.
     */
    public void writeToFile(String filePath) {
        Path path = Paths.get(filePath);
        byte[] bytes = bits.toByteArray();

        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Read a binary-sequence formatted file into a binary sequence object.
     * I recommend naming files for binary sequences names ending in ".enc" to help you
     * keep track of how a file is formatted.
     * @param filePath -- the filePath can just be the name of a text file, or it can be the full path of the file.
     * @return the contents of the file as a BinarySequence
     */
    public static BinarySequence readFromFile(String filePath) {
        BinarySequence sequence = new BinarySequence();
        Path path = Paths.get(filePath);
        if (!path.toFile().exists()) {
            System.err.println("!!! The file you've asked me to read doesn't even exist!");
            System.err.println("You asked for the file: "+path.toAbsolutePath());
            throw new RuntimeException("The file you asked for doesn't exist!");
        }

        try {
            byte[] bytes = Files.readAllBytes(path);
            sequence.bits = BitSet.valueOf(bytes);
            sequence.count = sequence.bits.length() - 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sequence;
    }

    /////////////////////////////////////////////////////
    // ITERATOR, so you can iterate over the booleans:
    // for (boolean b: someBinarySequence) {
    //     ...
    // }
    /////////////////////////////////////////////////////
    @Override
    public Iterator<Boolean> iterator() {
        return new BinarySequenceIterator();
    }

    private class BinarySequenceIterator implements Iterator<Boolean> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < count;
        }

        @Override
        public Boolean next() {
            return bits.get(currentIndex++);
        }
    }


    /////////////////////////////////////////////////////
    // BASIC OBJECT METHODS
    /////////////////////////////////////////////////////

    /**
     * create a uncompressed and inefficient string representation
     * This is mainly useful when debugging.
     */
    @Override
    public String toString() {
        char[] result = new char[count];
        for (int i = 0; i < count; ++i) {
            if (bits.get(i)) {
                result[i] = '1';
            } else {
                result[i] = '0';
            }
        }
        return new String(result);
    }

    /**
     * Mainly we delegate equality to the binary sequence.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o instanceof BinarySequence) {
            BinarySequence bo = (BinarySequence) o;
            return this.bits.equals(bo.bits);
        } else {
            return false;
        }
    }

    /**
     * We probably haven't talk about this class,
     * but it kinda goes along with equals. in well written code
     * and is used with hashmaps.
     */
    @Override
    public int hashCode() {
        return this.bits.hashCode();
    }

    @Override
    /**
     * Make a deep copy of the BinarySequence.
     * Use this if, for any reason, you need to copy the binary seuqence.
     */
    public BinarySequence clone() {
        BinarySequence clone = null;
        try {
            clone = (BinarySequence) super.clone();
            clone.bits = (BitSet) bits.clone();
            clone.count = count;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    public static void main(String[] args) {
        BinarySequence bs1 = new BinarySequence("001");
        bs1.append(true);
        System.out.println(bs1);
    }
}
