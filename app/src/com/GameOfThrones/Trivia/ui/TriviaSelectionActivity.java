package com.GameOfThrones.Trivia.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.GameOfThrones.Trivia.R;
import com.GameOfThrones.Trivia.core.GameCharacter;

/**
 * Activity to select which character the trivia questions would focus on
 * 
 * @author andre
 * 
 */
public class TriviaSelectionActivity extends DynamicBackgroundActivity
		implements OnItemClickListener {
	/**
	 * QuestionCollection of characters
	 */
	ArrayList<String> charactersName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.GameOfThrones.Trivia.SuperActivities.DynamicBackgroundActivity#onCreate
	 * (android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia_selection);

		storeCharactersName();

		final ListView listview = (ListView) findViewById(R.id.charactersList);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.clickable_row, charactersName);
		// Bind to our new adapter.

		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	/**
	 * Obtains each character name from characterlist in session and places that
	 * information in the charactersName instance variable.
	 */
	private void storeCharactersName() {
		ArrayList<GameCharacter> gameCharacters = session.getCharacters();

		charactersName = new ArrayList<String>();
		charactersName.add("all");

		for (GameCharacter g : gameCharacters) {
			charactersName.add(g.getName());
		}
	}

	/**
	 * Starts the trivia app for character option selected
	 * 
	 * @param character
	 */
	private void startGameForCharacter(int character) {
		Bundle bundle = new Bundle();
		bundle.putInt("gameCharacters", character);
		nextActivity(bundle, GameActivity.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				finish();
			}
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.GameOfThrones.Trivia.SuperActivities.DynamicBackgroundActivity#
	 * getBackgroundLayout()
	 */
	@Override
	protected int getBackgroundLayout() {
		return R.id.triviaSelection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	public void onItemClick(AdapterView<?> arg0, View arg1, int row, long arg3) {
		Toast.makeText(getBaseContext(), "Clicked " + charactersName.get(row),
				Toast.LENGTH_LONG).show();
		startGameForCharacter(row);
	}
}