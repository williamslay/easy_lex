package code;

public class Symbol extends Token{
    public char lexeme=' ';
    public Symbol(char c,int tag) {
        super(tag);
        lexeme=c;
    }
    public String toString()
    {
        String s = String.valueOf(lexeme);
        return "("+Integer.toString(tag)+",“"+s+"”)";
    }
    public static final Symbol
           lep =new Symbol('(',Tag.BOUNOD_S),rip = new Symbol(')',Tag.BOUNOD_S),lecb =new Symbol('{',Tag.BOUNOD_S),ricb =new Symbol('}',Tag.BOUNOD_S),
            se =new Symbol(';',Tag.BOUNOD_S), com=new Symbol(',',Tag.BOUNOD_S),plus =new Symbol('+',Tag.OPERA_S),minus =new Symbol('-',Tag.OPERA_S),
            times=new Symbol('*',Tag.OPERA_S),divided=new Symbol('/',Tag.OPERA_S);
}
