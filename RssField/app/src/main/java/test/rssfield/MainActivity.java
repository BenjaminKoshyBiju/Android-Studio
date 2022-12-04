package test.rssfield;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    String ur;
    String f,t,h,c;
    public volatile boolean parsingComplete = true;
    private XmlPullParserFactory xmlFactoryObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onShow(View v)
    {
        Log.d("Here", "First");
        WebView w1=(WebView)findViewById(R.id.webView);
        e1 = (EditText)findViewById(R.id.etURL);
        e2 = (EditText)findViewById(R.id.etT);
        e3 = (EditText)findViewById(R.id.etL);
        e4 = (EditText)findViewById(R.id.etD);
        e5 = (EditText)findViewById(R.id.etC);
        ur = e1.getText().toString();
        w1.loadUrl(ur);
        Log.d("Here1", "Second");
        Thread thread = new Thread(new Runnable(){
            public void run() {

                try {
                    URL url = new URL(ur);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();
                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);
                    Log.d("Count", "" + stream.available());
                    parseXMLAndExtract(myparser);

                    stream.close();
                }
                catch (Exception e) {
                    Log.d("Error",e.toString());
                }
            }
        });
        Log.d("Here2", "Third");
        thread.start();
        Log.d("Here3", "Fourth");
        while(this.parsingComplete)
            ;
        Log.d("Here4", "Fifth");
        e2.setText(t);
        e3.setText(f);
        e4.setText(h);
        e5.setText(c);
    }
    public void parseXMLAndExtract(XmlPullParser myParser)
    {
        int event;
        String text=null;
        String test = "";
        try
        {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT)
            {
                String name=myParser.getName();
                test = test + name + "\n";
                Log.d("TEST", test,null);
                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        test = test + text + "\n";
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("to"))
                        {
                            t = text;

                        }
                        else if(name.equals("from"))
                        {
                            f = text;

                        }
                        else if(name.equals("heading"))
                        {
                            h = text;

                        }
                        else if(name.equals("body"))
                        {
                            c = text;

                        }
                        else
                        {
                        }
                        break;
                }
                event = myParser.next();
            }
            this.parsingComplete = false;
            //Toast.makeText(this, test, 3).show();
            System.out.println("DONEDONE");

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}


