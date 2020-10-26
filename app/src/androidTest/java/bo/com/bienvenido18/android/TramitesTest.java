package bo.com.bienvenido18.android;

import android.app.Activity;
import android.content.Intent;
import android.view.View;


import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import bo.com.bienvenido18.android.ui.activities.ActivityTramites;
import bo.com.bienvenido18.android.ui.activities.ActivityU;
import bo.com.bienvenido18.android.ui.activities.TramitesDetail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertNotNull;

public class TramitesTest {
    @Rule
    public ActivityTestRule<ActivityTramites> mActivityTestRule = new ActivityTestRule<ActivityTramites>(ActivityTramites.class);
    private ActivityTramites mActivity =null;
    @Rule
    public IntentsTestRule<ActivityTramites> mActivityRule = new IntentsTestRule<>(
            ActivityTramites.class);
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    //La activity se lanza correctamente
    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.RecyclerIdTramites);
        assertNotNull(view);
    }

    @Test
    public void intentSent(){
        onView(withId(R.id.RecyclerIdTramites)).perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_CALL)));

    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}