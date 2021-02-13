package com.example.unittesting;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.unittesting.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void addTest(){
        onView(withId(R.id.first_num)).perform(typeText("4"));
        onView(withId(R.id.second_num)).perform(typeText("6"));
        onView(withId(R.id.btn_add)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("10")));
    }

    @Test
    public void addInvalid(){
        onView(withId(R.id.first_num)).perform(typeText("4"));
        onView(withId(R.id.second_num)).perform(typeText("t"));
        onView(withId(R.id.btn_add)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("please provide valid input")));

    }

    @Test
    public void subtract(){
        onView(withId(R.id.first_num)).perform(typeText("20"));
        onView(withId(R.id.second_num)).perform(typeText("3"));
        onView(withId(R.id.btn_subtract)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("17")));
    }

    @Test
    public void multiplication(){
        onView(withId(R.id.first_num)).perform(typeText("10"));
        onView(withId(R.id.second_num)).perform(typeText("42"));
        onView(withId(R.id.btn_multiple)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("420")));
    }

    @Test
    public void multiplicationToLetters(){
        onView(withId(R.id.first_num)).perform(typeText("10"));
        onView(withId(R.id.second_num)).perform(typeText("b"));
        onView(withId(R.id.btn_multiple)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("please provide valid input")));
    }



    @Test
    public void emptyField(){
        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("field can't be empty")));
    }

    @Test
    public void divideToLetter(){
        onView(withId(R.id.first_num)).perform(typeText("3"));
        onView(withId(R.id.second_num)).perform(typeText("ee"));
        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("please provide valid input")));
    }

    @Test
    public void divide(){
        onView(withId(R.id.first_num)).perform(typeText("40"));
        onView(withId(R.id.second_num)).perform(typeText("5"));
        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.txt_sumView)).check(matches(withText("8")));
    }


}
