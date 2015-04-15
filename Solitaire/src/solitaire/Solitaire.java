package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		System.out.println("Joker A Start");
		printList(deckRear);
		if (deckRear.next.cardValue == 27) {
			CardNode prev = deckRear;
			CardNode j = deckRear.next;
			CardNode c1 = j.next;
			CardNode n = c1.next;
			prev.next = c1;
			c1.next = j;
			j.next = n;
			//printList(deckRear);
			return;
		}
		CardNode s = deckRear.next;
		while(s.next.cardValue != 27) s = s.next;
		if(s.next == deckRear) {
			CardNode prev = s;
			CardNode j = s.next;
			CardNode c1 = s.next.next;
			CardNode n = c1.next;
			prev.next = c1;
			c1.next = j;
			j.next = n;
			deckRear = c1;
			return;
		}
		if (s.next.next == deckRear) {
			CardNode prev = s;
			CardNode j = s.next;
			CardNode c1 = s.next.next;
			CardNode n = s.next.next.next;
			prev.next = c1;
			c1.next = j;
			j.next = n;
			deckRear = j;
			return;
		}
		CardNode prev = s;
		CardNode one = s.next;
		CardNode swap = s.next.next;
		CardNode next = swap.next;
		prev.next = swap;
		swap.next = one;
		one.next = next;
		return;

	}

	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
		System.out.println("Joker B Start");
		printList(deckRear);
	    CardNode check = deckRear;
	    if (check.next.cardValue == 28) {
	    	CardNode j = check.next;
	    	CardNode c1 = j.next;
	    	CardNode c2 = c1.next;
	    	CardNode n = c2.next;
	    	check.next = c1;
	    	c2.next = j;
	    	j.next = n;
	    	return;
	    }
	    for(CardNode s = deckRear.next; s != deckRear; s = s.next) {
	    	if(s.next.cardValue == 28) {
	    		CardNode j = s.next;
	    		CardNode c1 = j.next;
	    		CardNode c2 = c1.next;
	    		CardNode n = c2.next;
	    		if (s.next.next.next == deckRear) deckRear = j;
	    		else if (s.next.next == deckRear) deckRear = c2;
	    		else if (s.next == deckRear) deckRear = c1;
	    		s.next = c1;
	    		j.next = n;
	    		c2.next = j;
	    		return;
	    	}
	    }
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		System.out.println("Triple cut Start");
		printList(deckRear);
		/*
		CardNode f1 = deckRear.next;
		CardNode s = f1;
		if(f1.cardValue == 27 || f1.cardValue == 28) {
			s = s.next;
			while (s.cardValue != 27 && s.cardValue != 28) s = s.next;
			deckRear = s;
			return;
		}
		if(deckRear.cardValue == 27 || deckRear.cardValue == 28) {
			s = deckRear.next;
			while(s.next.cardValue != 27 && s.next.cardValue != 28) s = s.next;
			deckRear = s;
			return;
		}
		while (s.next.cardValue != 27 && s.next.cardValue != 28) s = s.next;
		CardNode l1 = s;
		CardNode j1 = s.next;
		s = s.next;
		while (s.next.cardValue != 27 && s.next.cardValue != 28) s = s.next;
		CardNode j2 = s.next;
		CardNode f2 = j2.next;
		s = s.next;
		while(s.next != deckRear.next) s = s.next;
		CardNode l2 = s;
		l2.next = j1;
		j2.next = f1;
		l1.next = f2;
		deckRear = l1;
		*/
		if(deckRear.cardValue == 27 || deckRear.cardValue == 28) {
			if (deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28) return;
			CardNode s = deckRear.next;
			while(s.next.cardValue != 27 && s.next.cardValue != 28) s = s.next;
			deckRear = s;
			return;
		}
		if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28) {
			CardNode s = deckRear.next;
			s = s.next;
			while (s.cardValue != 27 && s.cardValue != 28) s = s.next;
			deckRear = s;
			return;
		}
		CardNode f1 = deckRear.next;
		CardNode s = f1;
		while (s.next.cardValue != 27 && s.next.cardValue != 28) s = s.next;
		CardNode l1 = s; 
		CardNode j1 = s.next;
		s = s.next.next;
		while (s.cardValue != 27 && s.cardValue != 28) s = s.next;
		CardNode j2 = s;
		CardNode f2 = s.next;
		CardNode l2 = deckRear;
		l2.next = j1;
		j2.next = f1;
		l1.next = f2;
		deckRear = l1;
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {	
		System.out.println("Count Cut Start");
		printList(deckRear);
		int count = deckRear.cardValue;
		if (count == 28 || count == 27) return;
		CardNode front = deckRear.next;
		CardNode last = front;
		for (int i = 1; i < count; i++) last = last.next;
		CardNode newFront = last.next;
		CardNode rearPrev = newFront;
		while (rearPrev.next != deckRear) rearPrev = rearPrev.next;
		last.next = deckRear;
		rearPrev.next = front;
		deckRear.next = newFront;
		//printList(deckRear);
		//System.out.println("Count cut end");
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		int key = 28;
		while (key > 26) {
			jokerA();
			jokerB();
			tripleCut();
			countCut();
			int count = deckRear.next.cardValue;
			CardNode check = deckRear.next;
			if (count == 28) count = 27;
			for (int i = 1; i < count; i++) check = check.next;
			key = check.next.cardValue;
			//System.out.println("keyloop" + key);
		}
	    return key;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		String encrypted = "";
		for (int i = 0; i < message.length(); i++) {
			if (Character.isUpperCase(message.charAt(i))) {
				int key = message.charAt(i) - 'A' + 1 + getKey();
				if (key > 26) key = key - 26;
				char keyLetter = (char) (key - 1 + 'A');
				encrypted += keyLetter;
			}
		}
	    return encrypted;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		String encrypted = "";
		for (int i = 0; i < message.length(); i++) {
			if (Character.isUpperCase(message.charAt(i))) {
				int key = message.charAt(i) - 'A' + 1 - getKey();
				if (key < 1) key = key + 26;
				char keyLetter = (char) (key - 1 + 'A');
				encrypted += keyLetter;
			}
		}
	    return encrypted;
	}
}
