/**
 * @Author Daniel Kluver
 * This isn't the most efficient way to store a huffman codebook
 * But it's efficient enough. Even accounting for the size of
 * this file, our ebooks are MUCH shorter.
 */
public class ProvidedHuffmanCodeBook {
    /**
     * A huffman codebook that covers all the characters found in the 13 ebooks Kluver prepared.
     * This is based on an the optimal encoding scheme generation algorithm. Following that protocol, more common letters
     * are given shorter codes, and less common letters are given longer codes.
     *
     * you're free/encouraged to experiment with other code trees for other types of data, but this should cover the ebooks provided.
     */
    public static HuffmanCodeBook getEbookHuffmanCodebook() {
        HuffmanCodeBook book = new HuffmanCodeBook();
        book.addSequence(' ', new BinarySequence("110"));
        book.addSequence('e', new BinarySequence("000"));
        book.addSequence('a', new BinarySequence("1000"));
        book.addSequence('h', new BinarySequence("0100"));
        book.addSequence('i', new BinarySequence("0010"));
        book.addSequence('n', new BinarySequence("0101"));
        book.addSequence('o', new BinarySequence("0111"));
        book.addSequence('s', new BinarySequence("0011"));
        book.addSequence('t', new BinarySequence("1010"));
        book.addSequence('d', new BinarySequence("10110"));
        book.addSequence('l', new BinarySequence("10010"));
        book.addSequence('r', new BinarySequence("11110"));
        book.addSequence('\n', new BinarySequence("111010"));
        book.addSequence(',', new BinarySequence("011001"));
        book.addSequence('c', new BinarySequence("111000"));
        book.addSequence('f', new BinarySequence("101111"));
        book.addSequence('g', new BinarySequence("100110"));
        book.addSequence('m', new BinarySequence("111001"));
        book.addSequence('u', new BinarySequence("111110"));
        book.addSequence('w', new BinarySequence("101110"));
        book.addSequence('y', new BinarySequence("011010"));
        book.addSequence('.', new BinarySequence("1001110"));
        book.addSequence('b', new BinarySequence("1110111"));
        book.addSequence('p', new BinarySequence("1111111"));
        book.addSequence('v', new BinarySequence("0110110"));
        book.addSequence('I', new BinarySequence("10011111"));
        book.addSequence('k', new BinarySequence("11111101"));
        book.addSequence(';', new BinarySequence("011011100"));
        book.addSequence('A', new BinarySequence("011011110"));
        book.addSequence('H', new BinarySequence("011000110"));
        book.addSequence('S', new BinarySequence("011000111"));
        book.addSequence('T', new BinarySequence("111111001"));
        book.addSequence('’', new BinarySequence("011011111"));
        book.addSequence('“', new BinarySequence("011000001"));
        book.addSequence('-', new BinarySequence("1110110110"));
        book.addSequence(':', new BinarySequence("0110000100"));
        book.addSequence('B', new BinarySequence("1001111000"));
        book.addSequence('C', new BinarySequence("0110111011"));
        book.addSequence('D', new BinarySequence("1110110000"));
        book.addSequence('E', new BinarySequence("1110110001"));
        book.addSequence('G', new BinarySequence("0110000101"));
        book.addSequence('L', new BinarySequence("0110001001"));
        book.addSequence('M', new BinarySequence("1110110011"));
        book.addSequence('N', new BinarySequence("0110001000"));
        book.addSequence('O', new BinarySequence("1001111001"));
        book.addSequence('P', new BinarySequence("0110001010"));
        book.addSequence('W', new BinarySequence("1110110010"));
        book.addSequence('_', new BinarySequence("0110000110"));
        book.addSequence('j', new BinarySequence("0110001011"));
        book.addSequence('q', new BinarySequence("0110000000"));
        book.addSequence('x', new BinarySequence("1110110100"));
        book.addSequence('”', new BinarySequence("1111110001"));
        book.addSequence('!', new BinarySequence("11101101110"));
        book.addSequence('\"', new BinarySequence("10011110101"));
        book.addSequence('(', new BinarySequence("01100000010"));
        book.addSequence(')', new BinarySequence("01100000011"));
        book.addSequence('?', new BinarySequence("11111100000"));
        book.addSequence('F', new BinarySequence("11101101011"));
        book.addSequence('R', new BinarySequence("11111100001"));
        book.addSequence('Y', new BinarySequence("01100001110"));
        book.addSequence('z', new BinarySequence("01101110100"));
        book.addSequence('—', new BinarySequence("10011110110"));
        book.addSequence('\'', new BinarySequence("100111101000"));
        book.addSequence('1', new BinarySequence("100111101001"));
        book.addSequence('J', new BinarySequence("111011011111"));
        book.addSequence('K', new BinarySequence("011000011110"));
        book.addSequence('Q', new BinarySequence("111011010101"));
        book.addSequence('U', new BinarySequence("100111101111"));
        book.addSequence('V', new BinarySequence("011011101010"));
        book.addSequence('*', new BinarySequence("0110000111111"));
        book.addSequence('0', new BinarySequence("0110111010111"));
        book.addSequence('2', new BinarySequence("1001111011101"));
        book.addSequence('‘', new BinarySequence("0110111010110"));
        book.addSequence('3', new BinarySequence("11101101111011"));
        book.addSequence('4', new BinarySequence("11101101111001"));
        book.addSequence('5', new BinarySequence("11101101010001"));
        book.addSequence('6', new BinarySequence("10011110111001"));
        book.addSequence('7', new BinarySequence("01100001111101"));
        book.addSequence('8', new BinarySequence("11101101111000"));
        book.addSequence('9', new BinarySequence("10011110111000"));
        book.addSequence('X', new BinarySequence("11101101111010"));
        book.addSequence('ñ', new BinarySequence("11101101010011"));
        book.addSequence('Z', new BinarySequence("111011010100001"));
        book.addSequence('/', new BinarySequence("1110110101001010"));
        book.addSequence('[', new BinarySequence("1110110101001000"));
        book.addSequence(']', new BinarySequence("1110110101001001"));
        book.addSequence('Æ', new BinarySequence("1110110101000001"));
        book.addSequence('é', new BinarySequence("0110000111110001"));
        book.addSequence('&', new BinarySequence("01100001111100100"));
        book.addSequence('£', new BinarySequence("11101101010000000"));
        book.addSequence('æ', new BinarySequence("01100001111100111"));
        book.addSequence('…', new BinarySequence("01100001111100101"));
        book.addSequence('$', new BinarySequence("011000011111000011"));
        book.addSequence('á', new BinarySequence("011000011111000000"));
        book.addSequence('è', new BinarySequence("111011010100000010"));
        book.addSequence('#', new BinarySequence("0110000111110000010"));
        book.addSequence('%', new BinarySequence("1110110101000000110"));
        book.addSequence('Á', new BinarySequence("1110110101001011010"));
        book.addSequence('à', new BinarySequence("1110110101001011100"));
        book.addSequence('ê', new BinarySequence("0110000111110000101"));
        book.addSequence('í', new BinarySequence("0110000111110011000"));
        book.addSequence('ā', new BinarySequence("1110110101001011000"));
        book.addSequence('œ', new BinarySequence("1110110101001011001"));
        book.addSequence('﻿', new BinarySequence("0110000111110000011"));
        book.addSequence('\t', new BinarySequence("11101101010000001111"));
        book.addSequence('=', new BinarySequence("11101101010000001110"));
        book.addSequence('>', new BinarySequence("11101101010010110110"));
        book.addSequence('{', new BinarySequence("11101101010010111111"));
        book.addSequence('}', new BinarySequence("11101101010010111100"));
        book.addSequence('Ñ', new BinarySequence("01100001111100110011"));
        book.addSequence('Ú', new BinarySequence("01100001111100001000"));
        book.addSequence('ó', new BinarySequence("01100001111100001001"));
        book.addSequence('ô', new BinarySequence("01100001111100110010"));
        book.addSequence('ö', new BinarySequence("11101101010010111101"));
        book.addSequence('ſ', new BinarySequence("11101101010010110111"));
        book.addSequence(' ', new BinarySequence("11101101010010111110"));
        book.addSequence('ë', new BinarySequence("111011010100101110110"));
        book.addSequence('ù', new BinarySequence("111011010100101110111"));
        book.addSequence('ü', new BinarySequence("111011010100101110100"));
        book.addSequence('°', new BinarySequence("0110000111110011011111"));
        book.addSequence('È', new BinarySequence("0110000111110011010111"));
        book.addSequence('Ü', new BinarySequence("0110000111110011011110"));
        book.addSequence('â', new BinarySequence("1110110101001011101010"));
        book.addSequence('ç', new BinarySequence("1110110101001011101011"));
        book.addSequence('î', new BinarySequence("0110000111110011010001"));
        book.addSequence('ï', new BinarySequence("0110000111110011011101"));
        book.addSequence('ò', new BinarySequence("0110000111110011010000"));
        book.addSequence('û', new BinarySequence("0110000111110011011001"));
        book.addSequence('Œ', new BinarySequence("0110000111110011011100"));
        book.addSequence('̸', new BinarySequence("0110000111110011011011"));
        book.addSequence('–', new BinarySequence("0110000111110011010101"));
        book.addSequence('†', new BinarySequence("0110000111110011010110"));
        book.addSequence('+', new BinarySequence("01100001111100110100100"));
        book.addSequence('@', new BinarySequence("01100001111100110101001"));
        book.addSequence('½', new BinarySequence("01100001111100110100101"));
        book.addSequence('À', new BinarySequence("01100001111100110100110"));
        book.addSequence('Ç', new BinarySequence("01100001111100110100111"));
        book.addSequence('É', new BinarySequence("01100001111100110110000"));
        book.addSequence('ä', new BinarySequence("01100001111100110110001"));
        book.addSequence('ú', new BinarySequence("01100001111100110101000"));
        book.addSequence('•', new BinarySequence("01100001111100110110101"));
        book.addSequence('✠', new BinarySequence("01100001111100110110100"));
        return book;
    }
}
