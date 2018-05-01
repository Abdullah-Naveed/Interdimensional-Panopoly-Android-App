package com.rob.monopoly;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainMenuGameTest extends ActivityInstrumentationTestCase2<MainMenuGame> {

    private MainMenuGame mTestActivity;
    private TextView mTestEmptyText;

    public MainMenuGameTest() {
        super(MainMenuGame.class);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mTestActivity = getActivity();
        mTestEmptyText = (TextView) mTestActivity.findViewById(R.id.textView);
    }


    @Test
    public void testActivityExists() {
        assertNotNull("MainMenuGame is null", mTestActivity);
    }

    @Test
    public void testTextView() {
        assertNotNull("TextView could not be found", mTestEmptyText);
        assertEquals("TextView is empty", "2 Players",mTestEmptyText.getText().toString());
    }

}