package com.GameOfThrones.Trivia;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.GameOfThrones.Trivia.R;
import com.GameOfThrones.Trivia.Core.Session;

/**
 * Used to change the drawable that is used as background image periodically
 * throughout the applications runtime
 * 
 * @author andre
 * 
 */
public abstract class DynamicBackgroundActivity extends Activity {
	/**
	 * Used to store which background drawables are being used
	 */
	protected Session session = Session.getInstance();

	protected int backgroundPort;
	/**
	 * drawable reference to background image when in landscape mode
	 */
	protected int backgroundLand;
	/**
	 * GameCharacters created created in application
	 */

	/**
	 * Stores drawable references for landscape mode
	 */
	// @TODO must obtain these at runtime
	protected static final int[] landscapePics = new int[] {
			R.drawable.dany_land, R.drawable.eddard_land,
			R.drawable.eddard_land1, R.drawable.jamie_land,
			R.drawable.jamie_land1, R.drawable.ygritte_land,
			R.drawable.y_john_land };

	/**
	 * Stores drawable references for landscape mode
	 */
	// @TODO must obtain these at runtime
	protected static final int[] portraitPics = new int[] {
			R.drawable.dany_port, R.drawable.eddard_port,
			R.drawable.jamie_port, R.drawable.tyrion_port };

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * Sets the background drawable that is pre-determined
	 */
	public void onStart() {
		super.onStart();
		setBackground(getBackgroundLayout());
	}

	/**
	 * Sets the background for the current activity
	 * 
	 * @param backgroundId
	 *            - background drawable reference
	 */
	protected void setBackground(int backgroundId) {
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			if (backgroundPort == 0) {
				refreshBackground();
			}
			findViewById(backgroundId).setBackgroundResource(backgroundPort);
		} else {
			if (backgroundLand == 0) {
				refreshBackground();
			}
			findViewById(backgroundId).setBackgroundResource(backgroundLand);
		}
	}

	/**
	 * Randomly selects new background drawables for both orientations
	 */
	protected void refreshBackground() {
		int resourcePort = portraitPics[new Random()
				.nextInt(portraitPics.length)];
		int resourceLand = landscapePics[new Random()
				.nextInt(landscapePics.length)];

		backgroundPort = resourcePort;
		backgroundLand = resourceLand;
		
		
	}

	/**
	 * Subclasses will implement this to return the appropriate view background
	 * view object
	 * 
	 * @return
	 */
	protected abstract int getBackgroundLayout();

	/**
	 * Starts the next activity and finishes current one
	 * 
	 * @param cls
	 *            - the Class signature of Activity to start
	 */
	protected void nextActivity(Class<?> cls) {
		Intent aboutIntent = new Intent(this, cls);
		startActivity(aboutIntent);
		finish();
	}

	/**
	 * Starts the next activity, finishes current one and passes information in
	 * the form of a Bundle
	 * 
	 * @param bundle
	 *            - Bundle used to send info to next activity
	 * @param cls
	 *            - the Class signature of Activity to start
	 */
	protected void nextActivity(Bundle bundle, Class<?> cls) {
		Intent aboutIntent = new Intent(this, cls);
		aboutIntent.putExtras(bundle);
		startActivity(aboutIntent);
		finish();
	}
}