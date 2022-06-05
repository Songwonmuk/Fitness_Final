package net.scit.vo;

import java.util.List;

public class Program {
	private int programID;
	private String programName;
	private String programInfo;
	private List<Exercise> exerList;
	private int totalCalory;
	private double totalMuscle;
	private int totalStress;
	private double totalBalance;

	public Program() {
		super();
	}

	public Program(String programName, String programInfo) {
		this.programName = programName;
		this.programInfo = programInfo;
	}

	public Program(int programID, String programName, String programInfo, List<Exercise> exerList, int totalCalory,
				   double totalMuscle, int totalStress, double totalBalance) {
		super();
		this.programID = programID;
		this.programName = programName;
		this.programInfo = programInfo;
		this.exerList = exerList;
		this.totalCalory = totalCalory;
		this.totalMuscle = totalMuscle;
		this.totalStress = totalStress;
		this.totalBalance = totalBalance;
	}

	public int getProgramID() {
		return programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramInfo() {
		return programInfo;
	}

	public void setProgramInfo(String programInfo) {
		this.programInfo = programInfo;
	}

	public List<Exercise> getExerList() {
		return exerList;
	}

	public void setExerList(List<Exercise> exerList) {
		this.exerList = exerList;
	}

	public int getTotalCalory() {
		return totalCalory;
	}

	public void setTotalCalory(int totalCalory) {
		this.totalCalory = totalCalory;
	}

	public double getTotalMuscle() {
		return totalMuscle;
	}

	public void setTotalMuscle(double totalMuscle) {
		this.totalMuscle = totalMuscle;
	}

	public int getTotalStress() {
		return totalStress;
	}

	public void setTotalStress(int totalStress) {
		this.totalStress = totalStress;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	@Override
	public String toString() {
		return String.format("%6d    %-15s  \t   %-8d \t   %-8.2f \t   %-8d  \t  %-8.2f", programID, programName, totalCalory, totalMuscle,
				totalStress, totalBalance);
	}
}
