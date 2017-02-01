package sonnguyen.findpathwithlowercost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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
        mainActivity.editRow.setText("5");
        mainActivity.editColumn.setText("6");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.row,equalTo(5));
        assertThat(mainActivity.column,equalTo(6));

    }
    @Test
    public void validateInput(){
        mainActivity.editRow.setText("0");
        mainActivity.editColumn.setText("5");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(false));

        mainActivity.editRow.setText("100");
        mainActivity.editColumn.setText("100000");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(false));

        mainActivity.editRow.setText("");
        mainActivity.editColumn.setText("");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(false));

        mainActivity.editRow.setText("1");
        mainActivity.editColumn.setText("5");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.invalidateInput(),equalTo(true));


    }

    @Test
    public void checkResultWithSampleData1() {
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_example1));
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_result));
        assertThat(mainActivity.tvCost.getText().toString(), equalTo("16"));
        assertThat(mainActivity.tvHavePath.getText().toString(), equalTo("Yes"));
        assertThat(mainActivity.tvPath.getText().toString(), equalTo("1\t2\t3\t4\t4\t5"));

    }
    @Test
    public void checkResultWithSampleData2() {
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_example2));
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_result));
        assertThat(mainActivity.tvCost.getText().toString(),equalTo("11"));
        assertThat(mainActivity.tvHavePath.getText().toString(),equalTo("Yes"));
        assertThat(mainActivity.tvPath.getText().toString(),equalTo("1\t2\t1\t5\t4\t5"));
    }
    @Test
    public void checkResultWithSampleData3() {
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_example3));
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_result));
        assertThat(mainActivity.tvCost.getText().toString(), equalTo("48"));
        assertThat(mainActivity.tvHavePath.getText().toString(), equalTo("No"));
        assertThat(mainActivity.tvPath.getText().toString(), equalTo("1\t1\t1"));
    }




}
