package sonnguyen.findpathwithlowercost.CommonUtils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 * This class contains common utility functions necessary for the project
 */

public class Utils {

    /**
     *  This method gets value in EditText
     * @param editText
     * @return
     */
    public static int getIntegerNumberFromEditText( EditText editText){
        int number = 0;
        if(editText!=null && editText.getText().toString().length()>0){
            try {
              number = Integer.parseInt( editText.getText().toString() );
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return number;
    }

    /**
     * This method formats the path
     * @param path
     * @return
     */
    public static String formatPath(Path path) {
        StringBuilder builder = new StringBuilder();
        List<Integer> rows = path.getRowsTraversed();

        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }

        return builder.toString();
    }

    /**
     * This function converts Desiti to Pixel
     * @param context
     * @param dp
     * @return
     */
    public static int dpToPx(Context context, int dp) {
        int px = Math.round(dp * getPixelScaleFactor(context));
        return px;
    }

    /**
     * This function gets pixel scale factor based on screen resolution
     * @param context
     * @return
     */
    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
