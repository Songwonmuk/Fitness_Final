package net.scit.vo;

public class Exercise {
	private int exerNum;
	private int programID;
	private String exerName;
	private ExerType exerType;
	private int exerTime;
	private int consumedCalory;
	private double gainedMuscle;
	private int lowerStress;
	private double getBalance;

	public Exercise() {
		super();
	}
		
	public Exercise(String exerName, ExerType exerType, int exerTime, int consumedCalory, double gainedMuscle,
			int lowerStress, double getBalance) {
		super();
		this.exerName = exerName;
		this.exerType = exerType;
		this.exerTime = exerTime;
		this.consumedCalory = consumedCalory;
		this.gainedMuscle = gainedMuscle;
		this.lowerStress = lowerStress;
		this.getBalance = getBalance;
	}


	public Exercise(int exerNum, int programID, String exerName, ExerType exerType, int exerTime, int consumedCalory,
			double gainedMuscle, int lowerStress, double getBalance) {
		super();
		this.exerNum = exerNum;
		this.programID = programID;
		this.exerName = exerName;
		this.exerType = exerType;
		this.exerTime = exerTime;
		this.consumedCalory = consumedCalory;
		this.gainedMuscle = gainedMuscle;
		this.lowerStress = lowerStress;
		this.getBalance = getBalance;
	}
	

	public int getExerNum() {
		return exerNum;
	}

	public void setExerNum(int exerNum) {
		this.exerNum = exerNum;
	}

	public int getProgramID() {
		return programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public String getExerName() {
		return exerName;
	}

	public void setExerName(String exerName) {
		this.exerName = exerName;
	}

	public ExerType getExerType() {
		return exerType;
	}

	public void setExerType(ExerType exerType) {
		this.exerType = exerType;
	}

	public int getExerTime() {
		return exerTime;
	}

	public void setExerTime(int exerTime) {
		this.exerTime = exerTime;
	}

	public int getConsumedCalory() {
		return consumedCalory;
	}

	public void setConsumedCalory(int consumedCalory) {
		this.consumedCalory = consumedCalory;
	}

	public double getGainedMuscle() {
		return gainedMuscle;
	}

	public void setGainedMuscle(double gainedMuscle) {
		this.gainedMuscle = gainedMuscle;
	}

	public int getLowerStress() {
		return lowerStress;
	}

	public void setLowerStress(int lowerStress) {
		this.lowerStress = lowerStress;
	}

	public double getGetBalance() {
		return getBalance;
	}

	public void setGetBalance(double getBalance) {
		this.getBalance = getBalance;
	}

	@Override
	public String toString() {
		return String.format("%2d   %4d   %7s    %s    %d    %d    %f    %f    %d", exerNum, programID, exerName, exerType,
				exerTime, consumedCalory, gainedMuscle, getBalance, lowerStress);
	}

}
