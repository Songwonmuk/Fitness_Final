package net.scit.vo;

public class Manager {

	// db에서 자동 설정
	private int managerId;

	// 직접 설정
	private String managerName;
	private int jobPeriod;

	// 계산 데이터
	private int influenceLevel; // 경력기간에 따라 설정됨
	private String feedback;
	public Manager() {
		super();
	}
	public Manager(int managerId, String managerName, int jobPeriod, int influenceLevel, String feedback) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.jobPeriod = jobPeriod;
		this.influenceLevel = influenceLevel;
		this.feedback = feedback;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public int getJobPeriod() {
		return jobPeriod;
	}
	public void setJobPeriod(int jobPeriod) {
		this.jobPeriod = jobPeriod;
	}
	public int getInfluenceLevel() {
		return influenceLevel;
	}
	public void setInfluenceLevel(int influenceLevel) {
		this.influenceLevel = influenceLevel;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", managerName=" + managerName + ", jobPeriod=" + jobPeriod
				+ ", influenceLevel=" + influenceLevel + ", feedback=" + feedback + "]";
	}
	
	

}