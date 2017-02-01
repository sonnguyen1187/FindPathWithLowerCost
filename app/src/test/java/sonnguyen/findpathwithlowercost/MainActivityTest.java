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
        mainActivity.editRow.setText("1");
        mainActivity.editColumn.setText("5");
        mainActivity.onClick(mainActivity.findViewById(R.id.btn_input));
        assertThat(mainActivity.row,equalTo(1));
        assertThat(mainActivity.column,equalTo(5));

    }
}
