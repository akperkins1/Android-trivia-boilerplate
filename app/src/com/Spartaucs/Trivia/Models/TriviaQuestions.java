package com.Spartaucs.Trivia.Models;

import java.util.ArrayList;

import com.Spartacus.Trivia.Exceptions.OutOfQuestionsException;
import com.Spartacus.Trivia.util.GeneralAlgorithms;

/**
 * 
 * @author Andre Perkins - akperkins1@gmail.com
 */
public class TriviaQuestions extends Questions {
	int numQuestions;

	public TriviaQuestions(String[] array) {
		numQuestions = array.length / 6;
		this.allQuestions = array;
		used = new ArrayList<Integer>();
		questionIndex = 0;
		prepareOrder(questionIndex);
	}

	public int getSize() {
		return numQuestions;
	}

	public String[] getQuestion() throws OutOfQuestionsException {
		if (used.size() == numQuestions) {
			System.err
					.println("All triviaQuestion are used! Program terminating.....");
			throw new OutOfQuestionsException();
		}

		String[] str = new String[6];

		int tempPos = questionOrder[questionIndex];
		used.add(questionIndex++);

		str[0] = allQuestions[tempPos * 6 + 0];
		str[1] = allQuestions[tempPos * 6 + 1];
		str[2] = allQuestions[tempPos * 6 + 2];
		str[3] = allQuestions[tempPos * 6 + 3];
		str[4] = allQuestions[tempPos * 6 + 4];
		str[5] = allQuestions[tempPos * 6 + 5];

		return str;
	}

	public boolean isUsed(int input) {
		return used.contains(input);
	}

	/**
	 * Each allQuestions position in the array is the ID this is used if an
	 * activity needs to be restarted in the middle a game. Adds each question
	 * in the position to the used array
	 * 
	 * @param usedPos
	 */
	public void setUsedQuestions(int[] usedPos) {
		for (int pos : usedPos) {
			used.add(pos);
		}
		// These used allQuestions have to get placed in the back of the array
		questionIndex = used.size();
		prepareOrder(questionIndex);
	}

	public void prepareOrder(int start) {
		Integer[] temp = new Integer[numQuestions];
		for (int i = 0; i < numQuestions; i++) {
			temp[i] = i;
		}
		Integer[] tempPos = (Integer[]) GeneralAlgorithms.shuffleArray(temp);
		questionOrder = tempPos;
	}

	@Override
	public int unusedLeft() {
		return questionOrder.length - (questionIndex + 1);
	}
}