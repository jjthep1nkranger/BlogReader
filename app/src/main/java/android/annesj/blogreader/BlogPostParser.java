package android.annesj.blogreader;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.LoggingMXBean;

public class BlogPostParser {
    private static BlogPostParser parser;
    private BlogPostParser(){

    }

    public static BlogPostParser get(){
        if(parser == null){
            parser = new BlogPostParser();
        }
        return parser;
    }

    public JSONObject parse(InputStream inputStream){
        BufferedReader reader =new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        JSONObject jsonObject = null;

        String line;
        try{
            while ((line = reader.readLine()) !=null){
                builder.append(line);
            }
            JSONTokener jsonTokener = new JSONTokener(builder.toString());
            jsonObject = new JSONObject(jsonTokener);
        }

        catch(IOException error){
            Log.e("BlogPostParser", "IOException: " + error);
        }
        catch(JSONException error){
            Log.e("BLogPostParser", "JSON Exception:" + error);
        }
        return jsonObject;
    }
}
