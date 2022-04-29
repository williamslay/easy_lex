package code;

public class Word extends Token {
    public String lexeme=" ";
    public Word(String s,int tag) {
        super(tag);
        lexeme=s;
    }
    public String toString()
    {
        return "("+Integer.toString(tag)+",“"+lexeme+"”)";
    }
    public static final Word
    eq =new Word("==",Tag.OPERA_S),  ne =new Word("!=",Tag.OPERA_S),le =new Word("<=",Tag.OPERA_S), ge =new Word(">=",Tag.OPERA_S),
            lemov=new Word("<<",Tag.OPERA_S),rimov = new Word(">>",Tag.OPERA_S);
}
