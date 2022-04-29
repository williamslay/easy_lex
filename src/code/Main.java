package code;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Queue<Character> flute = new LinkedList<>();//文件读入
        try{
            FileReader fr = new FileReader("src/code.txt");
            int ch = 0;
            while((ch=fr.read())!=-1){
                flute.offer((char)ch);
            }
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        while(!flute.isEmpty())
        {
            Token token = lexer.scan(flute);
            System.out.println(token);
        }
    }
}
