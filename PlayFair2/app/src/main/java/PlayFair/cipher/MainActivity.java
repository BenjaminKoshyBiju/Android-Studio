package PlayFair.cipher;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    static String removeDuplicate(String s)
    {
        int j, index = 0, len = s.length();
        char c[] = s.toCharArray();
        for (int i = 0; i < len; i++) {
            for (j = 0; j < i; j++) {
                if (c[i] == c[j])
                    break;
            }
            if (i == j)
                c[index++] = c[i];
        }
        s = new String((Arrays.copyOf(c, index)));
        return s;
    }
    static String removeWhiteSpace(char[] ch, String key)
    {
        char[] c = key.toCharArray();
        // removing character which are input by the user
        // from string st

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < ch.length; j++) {
                if (c[i] == ch[j])
                    c[i] = ' ';
            }
        }

        key = new String(c);
        key = key.replaceAll(" ", "");
        return key;
    }
    static String makePair(String pt)
    {
        String s = "";
        char c = 'a';
        for (int i = 0; i < pt.length(); i++) {
            if (pt.charAt(i) == ' ')
                continue;
            else {
                c = pt.charAt(i);
                s += pt.charAt(i);
            }

            if (i < pt.length() - 1)
                if (pt.charAt(i) == pt.charAt(i + 1))
                    s += "x";
        }

        // If plain text length is odd then
        // adding x to make length even.
        if (s.length() % 2 != 0)
            s += "x";
        System.out.println(s);
        return s;
    }

    // Method 3
    // To find the position of row and column in matrix
    // for encryption of the pair.
    static int[] matrix(char a, char b, char x[][])
    {
        int[] y = new int[4];
        if (a == 'j')
            a = 'i';
        else if (b == 'j')
            b = 'i';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (x[i][j] == a) {
                    y[0] = i;
                    y[1] = j;
                }

                else if (x[i][j] == b) {
                    y[2] = i;
                    y[3] = j;
                }
            }
        }

        if (y[0] == y[2]) {
            y[1] += 1;
            y[3] += 1;
        }

        else if (y[1] == y[3]) {
            y[0] += 1;
            y[2] += 1;
        }

        for (int i = 0; i < 4; i++)
            y[i] %= 5;
        return y;
    }

    // Method 4
    // To encrypt the plaintext
    static String encrypt(String pt, char x[][])
    {

        char ch[] = pt.toCharArray();
        int a[] = new int[4];
        for (int i = 0; i < pt.length(); i += 2) {
            if (i < pt.length() - 1) {
                a = matrix(pt.charAt(i), pt.charAt(i + 1),
                        x);
                if (a[0] == a[2]) {
                    ch[i] = x[a[0]][a[1]];
                    ch[i + 1] = x[a[0]][a[3]];
                }

                else if (a[1] == a[3]) {
                    ch[i] = x[a[0]][a[1]];
                    ch[i + 1] = x[a[2]][a[1]];
                }

                else {
                    ch[i] = x[a[0]][a[3]];
                    ch[i + 1] = x[a[2]][a[1]];
                }
            }
        }

        pt = new String(ch);
        return pt;
    }

    public void Encrypt(View v) {
        EditText k;
        EditText p;
        EditText ec;
        EditText ed=(EditText) findViewById(R.id.et4);
        k = (EditText) findViewById(R.id.etKey);
        p = (EditText) findViewById(R.id.etPlain);
        ec=(EditText) findViewById(R.id.et3);
        String a = (k.getText().toString());
        String b = (p.getText().toString());
        //String pl = (p.getText().toString());
        a = removeDuplicate(a);

        char[] ch = a.toCharArray();
        String st = "abcdefghiklmnopqrstuvwxyz";

        st = removeWhiteSpace(ch, st);

        char[] c = st.toCharArray();
        char[][] x = new char[5][5];
        int indexOfSt = 0, indexOfKey = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (indexOfKey < a.length())
                    x[i][j] = ch[indexOfKey++];
                else
                    x[i][j] = c[indexOfSt++];
            }
        }
        // For getting encrypted output

        // Calling makePair() method over object created in
        // main()
        b = makePair(b);
        // Calling makePair() method over object created in
        // main()
       String e = encrypt(b, x);
        ec.setText(""+e.toUpperCase(Locale.ROOT));
        ed.setText(""+b);



    }
    void searchAndEncryptOrDecrypt(String doubblyCh,int [][]x) {
        char ch1 = doubblyCh.charAt(0);
        char ch2 = doubblyCh.charAt(1);
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (x[i][j] == ch1) {
                    row1 = i;
                    col1 = j;
                } else if (x[i][j] == ch2) {
                    row2 = i;
                    col2 = j;
                }
            }
        }
    }
    public void Dec(int row1, int col1,int row2, int col2, char [][]x,String b){
        EditText ed=(EditText) findViewById(R.id.et4);
      /*  if (row1==row2){
            col1=col1-1;
            col2=col2-1;
            if (col1<0)
                col1=4;
            if (col2<0)
                col2=4;
            e+=(Character.toString(x[row1][col1])+Character.toString(x[row1][col2]));
        }else if(col1==col2){
            row1=row1-1;
            row2=row2-1;
            if (row1<0)
                row1=4;
            if (row2<0)
                row2=4;
            e+=(Character.toString(x[row1][col1])+Character.toString(x[row2][col1]));
        }else{
            e+=(Character.toString(x[row1][col2])+Character.toString(x[row2][col1]));
        }*/

        ed.setText(""+b);
    }
}
