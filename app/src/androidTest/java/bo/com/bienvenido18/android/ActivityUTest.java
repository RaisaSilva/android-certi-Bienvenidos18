package bo.com.bienvenido18.android;


import android.content.Intent;
import android.view.View;

import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.AllOf.allOf;



import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.content.Intent;



import bo.com.bienvenido18.android.ui.activities.ActivityTramites;
import bo.com.bienvenido18.android.ui.activities.ActivityU;
import bo.com.bienvenido18.android.ui.activities.TramitesDetail;

import androidx.test.rule.GrantPermissionRule;

import static org.junit.Assert.*;

public class ActivityUTest {

    @Rule
    public ActivityTestRule<ActivityU> mActivityTestRule = new ActivityTestRule<ActivityU>(ActivityU.class);
    private ActivityU mActivity =null;
    @Rule
    public IntentsTestRule<ActivityU> mActivityRule = new IntentsTestRule<>(
            ActivityU.class);
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    //La activity se lanza correctamente
    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.RecyclerId);
        assertNotNull(view);
    }

    @Test
    public void intentSent(){
        onView(withId(R.id.imageView)).perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_CALL)));

    }





    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}