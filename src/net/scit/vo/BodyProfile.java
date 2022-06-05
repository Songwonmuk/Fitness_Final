package net.scit.vo;

public class BodyProfile {
	// FK
	private int memberId; // 멤버 id
	private int programId;

	// 직접 설정
	private int profileNum; // pk
	private int weight;
	private int height;
	private String opinion;

	// 계산 데이터
	private double smm; // 골격 근량
	private String smmResult;
	private double bmi; // 체중/(키(m))*(키(m))
	private String bmiResult; // bmi에 의해 계산
	private double balance;
	private String balanceResult;
	private double stress;
	private String stressResult;

//생성자
	public BodyProfile() {
		super();
	}

	public BodyProfile(int memberId, int programId, int profileNum, int weight, int height, String opinion) {
		super();
		this.memberId = memberId;
		this.programId = programId;
		this.profileNum = profileNum;
		this.weight = weight;
		this.height = height;
		this.opinion = opinion;
	}

	// db
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

//직접 설정
	public int getProfileNum() {
		return profileNum;
	}

	public void setProfileNum(int profileNum) {
		this.profileNum = profileNum;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;

		calcBmi(); // bmi 계산 메소드
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;

		calcBmi();
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

//계산 데이터
	public double getSmm() {
		return smm;
	}

	public void setSmm(double d) {
		this.smm = d;
	}

// 골격 근량 계산 : (남자 : 체중 * 0.48 // 여자 : 체중 * 0.36)
//	private void calSmm() {
//		if (member.getGender().equals("남")) {
//			this.smm = this.weight * 0.48;
//		} else {
//			this.smm = this.weight * 0.36;
//		}
//
//		resultSmm();
//	}

//	private void resultSmm() {
//		String gender = member.getGender();
//
//		// 성별이 남자일 때
//		if (gender.equals("남")) {
//			if (smm >= 34.8) {
//				this.smmResult = "높음";
//			} else if (smm >= 28.5) {
//				this.smmResult = "표준";
//			} else {
//				this.smmResult = "낮음";
//			}
//		}
//		// 성별이 여자일 때
//		else {
//			if (smm >= 17.02) {
//				this.smmResult = "높음";
//			} else if (smm >= 15.41) {
//				this.smmResult = "표준";
//			} else {
//				this.smmResult = "낮음";
//			}
//		}
//	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(int bmi) {
		this.bmi = bmi;
	}

	// bmi 계산 : 체중 / (신장)*(신장)
	private void calcBmi() {
		double tmp;
		tmp = this.height * 0.01;
		this.bmi = this.weight * (tmp * tmp);

		resultBmi();
	}

	private void resultBmi() {
		if (bmi >= 35)
			bmiResult = "고도비만";
		else if (bmi >= 30)
			bmiResult = "중도비만";
		else if (bmi >= 25)
			bmiResult = "경도비만";
		else if (bmi >= 23)
			bmiResult = "과체중";
		else if (bmi >= 18.5)
			bmiResult = "정상";
		else
			bmiResult = "저체중";
	}

	public String getBmiResult() {
		return bmiResult;
	}

	public void setBmiResult(String bmiResult) {
		this.bmiResult = bmiResult;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBalanceResult() {
		return balanceResult;
	}

	public void setBalanceResult(String balanceResult) {
		this.balanceResult = balanceResult;
	}

	public double getStress() {
		return stress;
	}

	public void setStress(double stress) {
		this.stress = stress;
	}

	public String getStressResult() {
		return stressResult;
	}

	public void setStressResult(String stressResult) {
		this.stressResult = stressResult;
	}

	@Override
	public String toString() {
		return "BodyProfile [memberId=" + memberId + ", programId=" + programId + ", profileNum=" + profileNum
				+ ", weight=" + weight + ", height=" + height + ", opinion=" + opinion + ", smm=" + smm + ", smmResult="
				+ smmResult + ", bmi=" + bmi + ", bmiResult=" + bmiResult + ", balance=" + balance + ", balanceResult="
				+ balanceResult + ", stress=" + stress + ", stressResult=" + stressResult + "]";
	}

	
}
