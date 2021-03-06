package fr.iutvalence.java.mp.urtado;

/**
 * @urtadob The class that show the process of a game
 */

public class Game
{

    /**
     * pointEarned is the number of point a word is worth
     */
    private final static int POINT_EARNED = 10;

    /**
     * WINNING_SCORE is the score that the player have to reach to win the game
     */
    private final static int WINNING_SCORE = 60;

    /**
     * NORMAL_WORD_LENGTH is the length an answer give by the player should be
     */
    private final static int NORMAL_WORD_LENGTH = 6;

    /**
     * Constants of the number of try.
     */
    private final static int NUMBER_TRY_START = 6;

    /**
     * Number of word the player have at the start of the game
     */
    private final static int NUMBER_WORD_START = 10;
    
    /**
     * number of word the player have before he loose
     */
    private int numberOfWord;
    /**
     * number of tries left
     */
    private int numberOfTriesLeft;

    // TODO (fix) this field suggest that you have only one player
    /**
     * player
     */
    private Player player;

    // TODO (fix) this field suggest that you have one or more than one player
    /**
     * number of player in the game (1 or 2)
     */
    private int playerNumber;

    /**
     * word that the player have to find
     */
    private String word;

    /**
     * the number of words already played
     */
    private int wordsAlreadyPlayed;

    /**
     * score at the end of the game
     */
    public int finalScore;

    /**
     * Game constructor with the player in parameter
     * 
     * @param player
     *            is player of the game
     */

    public Game(Player player)
    {
        this.finalScore = 0;
        this.wordsAlreadyPlayed = 0;
        this.numberOfWord = NUMBER_WORD_START;
        this.playerNumber = 1;
        this.player = player;
        this.numberOfTriesLeft = NUMBER_TRY_START;
    }


    /**
     * compare the answer with the word to find.
     * 
     * @param inputWord
     *            the result of the function getWord
     * @param word
     *            the word the player have to find
     * @return null says if the result is correct or not
     */
    private Result compareWord(String inputWord)
    {
        int[] placement = new int[] {Result.WRONG_LETTER, Result.WRONG_LETTER, Result.WRONG_LETTER, Result.WRONG_LETTER, Result.WRONG_LETTER, Result.WRONG_LETTER};
        char[] inputArray = inputWord.toCharArray();
        char[] wordArray = this.word.toCharArray();
        boolean mask[] = { true, true, true, true, true, true };
        
        // test if the length of the word is correct

        
        
        for (int letterIndex = 0; letterIndex < NORMAL_WORD_LENGTH; letterIndex++)
        {
            if ((inputArray[letterIndex] == wordArray[letterIndex]) && mask[letterIndex])
            {
                placement[letterIndex] = Result.GOOD_LETTER;
                mask[letterIndex] = false;
            }
        }

        for (int inputLetterIndex = 0; inputLetterIndex < NORMAL_WORD_LENGTH; inputLetterIndex++)
        {
            for (int wordLetterIndex = 0; wordLetterIndex < NORMAL_WORD_LENGTH; wordLetterIndex++)
            {
                if (inputLetterIndex == wordLetterIndex) continue;
                
                if ((inputArray[inputLetterIndex] == wordArray[wordLetterIndex]) && mask[wordLetterIndex])
                {
                    placement[inputLetterIndex] = Result.MISPLACED_LETTER;
                    mask[wordLetterIndex] = false;
                    break;
                }
            }

        }

        return new Result(inputWord, placement);
    }

    /**
     * Algorithm of the game
     */
    public void play()
    {
        SystemOutput output = null;
        output = new SystemOutput();
        Dictionnary dico = null;
        dico = new Dictionnary();
        output.newGameStatement();
        while (this.finalScore != WINNING_SCORE)
        {
            this.word = dico.getWord();
            output.getNewWord(this.numberOfWord);
            this.numberOfWord = this.numberOfWord -1;
            this.wordsAlreadyPlayed = this.wordsAlreadyPlayed + 1;
            this.numberOfTriesLeft = NUMBER_TRY_START;
            while (this.numberOfTriesLeft > 0)
            {
                char[] wordArray = this.word.toCharArray();
                //output.cheatcode(this.word);
                output.enterProposition();
                String w = this.player.getWord();
                if (w.length() != NORMAL_WORD_LENGTH)
                {
                    output.wrongSize();
                    this.numberOfTriesLeft = this.numberOfTriesLeft - 1;
                    continue;
                }
                Result r = compareWord(w);
                output.showWord(r);
                this.numberOfTriesLeft = this.numberOfTriesLeft - 1;
                if (r.goodWord() == true)
                {
                    this.finalScore = this.finalScore + POINT_EARNED;
                    this.numberOfTriesLeft = 0;
                    output.wordFind();
                }

            }
            output.wordNotFind();
        }
        output.endGame(this.wordsAlreadyPlayed);
    }

}
