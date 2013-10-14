package fr.iutvalence.java.mp.urtado;

import java.lang.reflect.Array;

/**
 * @author urtadob
 * The object that the users get after he did a try.
 */
public class Result
{
    /**
     * WRONG_LETTER tells the player if the letter is not in the word
     */
    public static final int WRONG_LETTER = 0;

    /**
     * GOOD_LETTER tells the player if the letter is well-placed in the word
     */
    public static final int GOOD_LETTER = 1;    
    /**
     * wrongPlaceLetter tells the player if the letter is in the word but a the wrong place
     */
    public static final int WRONG_PLACE_LETTER = 2;
    /**
     * result is the word that is send back to the player
     */
    private final String word; 
    /**
     * resultArray is the array which tells if the letter are well placed, wrong, or in the word
     */
    private final int[] lettersPlacement;
    
    
    /**
     * Constructor of result
     * @param placement proposition of the player
     * @param w word
     */
    public Result(String w, int[] placement )
    {
        this.word = w;
        this.lettersPlacement = placement;
    }


    /**
     * getWord allows the see the word
     * @return the word in the Result
     */
    public String getWord()
    {
        return this.word;
    }


    /**
     * getLettersPlacement allows to see the array
     * @return the placement of the letters
     */
    public int[] getLettersPlacement()
    {
        return this.lettersPlacement;
    }
 
    
}

