package com.jraska.vsb.or1.schedule;

import com.jraska.common.ArgumentCheck;
import com.jraska.vsb.or1.schedule.abc.ILocalSearchStrategy;

import java.util.Arrays;
import java.util.Random;

public class Swap implements ILocalSearchStrategy
{
	//region Fields

	private final Random mRandom;

	//endregion

	//region Constructors

	public Swap()
	{
		this(new Random());
	}

	public Swap(Random random)
	{
		ArgumentCheck.notNull(random);

		mRandom = random;
	}

	//endregion

	//region ILocalSearchStrategy impl

	@Override
	public int[] getNext(int[] currentSolution)
	{
		int length = currentSolution.length;

		int[] copyOf = Arrays.copyOf(currentSolution, currentSolution.length);

		int firstIndex = mRandom.nextInt(length);

		//uniformly select from remaining indexes
		int secondIndex = mRandom.nextInt(length - 1);
		if (secondIndex >= firstIndex)
		{
			secondIndex++;
		}

		copyOf[secondIndex] = currentSolution[firstIndex];
		copyOf[firstIndex] = currentSolution[secondIndex];

		return copyOf;
	}

	//endregion
}
