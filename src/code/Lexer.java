package code;
import java.util.Hashtable;
import java.util.Queue;

public class Lexer {
    char peek=' ';//存放读取字符
    Hashtable reserveWords = new Hashtable();
    void reserve(Word w)
    {
        reserveWords.put(w.lexeme,w);
    }
    public Lexer()
    {
        //保留关键字
        reserve(new Word("if",Tag.RWORD));
        reserve(new Word("for",Tag.RWORD));
        reserve(new Word("else",Tag.RWORD));
        reserve(new Word("continue",Tag.RWORD));
        reserve(new Word("break",Tag.RWORD));
        reserve(new Word("while",Tag.RWORD));
        reserve(new Word("do",Tag.RWORD));
        reserve(new Word("main",Tag.RWORD));
        //重要的标识符
        reserve(new Word("int",Tag.CLASS));
        reserve(new Word("char",Tag.CLASS));
        reserve(new Word("cin",Tag.Func_S));
        reserve(new Word("cout",Tag.Func_S));
        reserve(new Word("return",Tag.Func_S));
    }
    void readch(char c)//读入字符
    {
        peek=c;
    }
    boolean readch_if(char c1,char c2)//判断读入字符
    {
        readch(c1);
        if(peek!= c2)
        {
            peek=' ';
            return false;
        }
        peek=' ';
        return true;
    }
    public Token scan(Queue<Character> flute)
    {
        for(;;readch(flute.poll()))
        {
            if(peek==' '||peek=='\n'||peek=='\r')continue;
            else break;
        }
        switch(peek) {
            //多字符符号处理
            case '=':
                if(readch_if(flute.element(),'='))
                {
                    flute.poll();
                    return Word.eq;
                }
                else return new Symbol('=',Tag.OPERA_S);
            case '!':
                if(readch_if(flute.element(),'='))
                {
                    flute.poll();
                    return Word.ne;
                }
                else return new Token('!');
            case '<':
                if(readch_if(flute.element(),'='))
                {
                    flute.poll();
                    return Word.le;
                }
                else if(readch_if(flute.element(),'<'))
                {
                    flute.poll();
                    return Word.lemov;
                }
                else return new Symbol('<',Tag.OPERA_S);
            case '>':
                if(readch_if(flute.element(),'='))
                {
                    flute.poll();
                    return Word.ge;
                }
                else if(readch_if(flute.element(),'>'))
                {
                    flute.poll();
                    return Word.rimov;
                }
                else return new Symbol('>',Tag.OPERA_S);
                //符号处理
            case '+':
                peek=' ';
                return Symbol.plus;
            case '-':
                peek=' ';
                return Symbol.minus;
            case '*':
                peek=' ';
                return Symbol.times;
            case '/':
                peek=' ';
                return Symbol.divided;
            case ',':
                peek=' ';
                return Symbol.com;
            case ';':
                peek=' ';
                return Symbol.se;
            case '(':
                peek=' ';
                return Symbol.lep;
            case ')':
                peek=' ';
                return Symbol.rip;
            case '{':
                peek=' ';
                return Symbol.lecb;
            case '}':
                peek=' ';
                return Symbol.ricb;
        }
        if(Character.isDigit(peek))//数字
        {
            int v=0;
            do{
                v=10*v+Character.digit(peek,10);
                readch(flute.poll());
            }while(Character.isDigit(peek));
            return new NUM(v);
        }
        if(Character.isLetter(peek)||peek=='_')//字母或下划线开头的变量标识符
        {
            StringBuffer b = new StringBuffer();
            do{
                b.append(peek);
                readch(flute.poll());
            }while(Character.isLetter(peek)||peek=='_');
            String s = b.toString();
            Word w =(Word)reserveWords.get(s);
            if(w!=null) return w;
            w = new Word(s,Tag.ID);
            reserveWords.put(s,w);//变量标识符加入保留哈希表
            return w;
        }
        Token tok = new Token(peek);peek=' ';//未定义的标识符
        return tok;
    }
}