package code;

public class NUM extends Token
{
    public final int value;
    public NUM(int v)
    {
        super(Tag.NUM);
        value=v;
    }
    public String toString()
    {
        return "("+Integer.toString(tag)+",“"+Integer.toString(value)+"”)";
    }
}
