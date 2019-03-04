import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Currency {

    public static String getCurrency(String message, Api api) throws IOException {
        URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=16b7885aeffc45998bc5ac35fbf9e0d4");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while(in.hasNext()){
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);

        String date =
                DateFormat.getDateInstance(SimpleDateFormat.LONG, new Locale("ru")).format(new Date()) + " ";
        JSONObject rates = object.getJSONObject("rates");
        api.setKZT(rates.getDouble("KZT"));

        api.setDate(date);
        return "Курс на " + api.getDate() + ":" + "\n" +
                "Доллар США: " + api.getKZT() +" тенге";

    }
}
