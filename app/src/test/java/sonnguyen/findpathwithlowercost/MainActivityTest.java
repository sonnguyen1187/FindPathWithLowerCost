package sonnguyen.findpathwithlowercost;

import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import sonnguyen.findpathwithlowercost.UI.MainActivity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
/**
 * Created by sonnguyen on 2/1/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception
    {
        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void activityShouldNotBeNull() throws Exception
    {
        assertNotNull(mainActivity);
    }

    @Test
    public void checkInput(){
        EditText edtRow =(EditText) mainActivity.findViewById(R.id.edit_row);
        EditText editColumn =(EditText) mainActivity.findViewById(R.id.edit_column);
        edtRow.setText("5");
        editColumn.setText("6");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.row,equalTo(5));
        assertThat(mainActivity.column,equalTo(6));

    }
    @Test
    public void validateInput(){
        EditText edtRow =(EditText) mainActivity.findViewById(R.id.edit_row);
        EditText editColumn =(EditText) mainActivity.findViewById(R.id.edit_column);
        edtRow.setText("0");
        editColumn.setText("5");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(false));


        edtRow.setText("100");
        editColumn.setText("100000");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(false));


        edtRow.setText("");
        editColumn.setText("");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(false));

        edtRow.setText("1");
        editColumn.setText("5");

        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(true));


    }

    @Test
    public void checkResultWithSampleData1() {
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_example1));
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_result));

        TextView tvCost =(TextView) mainActivity.findViewById(R.id.tv_cost);
        TextView tvHavePath =(TextView) mainActivity.findViewById(R.id.tv_havePath);
        TextView tvPath =(TextView) mainActivity.findViewById(R.id.tv_path);
        assertThat(tvCost.getText().toString(), equalTo("16"));
        assertThat(tvHavePath.getText().toString(), equalTo("Yes"));
        assertThat(tvPath.getText().toString(), equalTo("1\t2\t3\t4\t4\t5"));

    }
    @Test
    public void checkResultWithSampleData2() {
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_example2));
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_result));

        TextView tvCost =(TextView) mainActivity.findViewById(R.id.tv_cost);
        TextView tvHavePath =(TextView) mainActivity.findViewById(R.id.tv_havePath);
        TextView tvPath =(TextView) mainActivity.findViewById(R.id.tv_path);
        assertThat(tvCost.getText().toString(),equalTo("11"));
        assertThat(tvHavePath.getText().toString(),equalTo("Yes"));
        assertThat(tvPath.getText().toString(),equalTo("1\t2\t1\t5\t4\t5"));
    }
    @Test
    public void checkResultWithSampleData3() {
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_example3));
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_result));
        TextView tvCost =(TextView) mainActivity.findViewById(R.id.tv_cost);
        TextView tvHavePath =(TextView) mainActivity.findViewById(R.id.tv_havePath);
        TextView tvPath =(TextView) mainActivity.findViewById(R.id.tv_path);
        assertThat(tvCost.getText().toString(), equalTo("48"));
        assertThat(tvHavePath.getText().toString(), equalTo("No"));
        assertThat(tvPath.getText().toString(), equalTo("1\t1\t1"));
    }




}
