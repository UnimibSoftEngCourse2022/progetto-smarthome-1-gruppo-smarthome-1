package io.patriot_framework.generator.dataFeed;

import java.util.Random;

import io.patriot_framework.generator.Data;

public class BinaryDataFeed implements DataFeed {
	private int prob;
	private String label;
	private boolean lastValue;
	private Random random;
	
	public BinaryDataFeed(int n) {
		this.prob = n;
		this.random = new Random();
	}
	
	@Override
	public Data getNextValue(Object... params) {
		// TODO Auto-generated method stub
		Boolean result = random.nextInt(prob) == 0;
		lastValue = result;
		
		return new Data(Boolean.class, result);
	}

	@Override
	public Data getPreviousValue() {
		// TODO Auto-generated method stub
		return new Data( Boolean.class ,lastValue);
	}

	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		this.label = label;
		
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return label;
	}

}
