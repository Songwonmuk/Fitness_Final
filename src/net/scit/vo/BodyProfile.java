package net.scit.vo;

public class BodyProfile {
	// FK
	private int memberId; // ��� id
	private int programId;

	// ���� ����
	private int profileNum; // pk
	private int weight;
	private int height;
	private String opinion;

	// ��� ������
	private double smm; // ��� �ٷ�
	private String smmResult;
	private double bmi; // ü��/(Ű(m))*(Ű(m))
	private String bmiResult; // bmi�� ���� ���
	private double balance;
	private String balanceResult;
	private double stress;
	private String stressResult;

//������
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

//���� ����
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

		calcBmi(); // bmi ��� �޼ҵ�
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

//��� ������
	public double getSmm() {
		return smm;
	}

	public void setSmm(double d) {
		this.smm = d;
	}

// ��� �ٷ� ��� : (���� : ü�� * 0.48 // ���� : ü�� * 0.36)
//	private void calSmm() {
//		if (member.getGender().equals("��")) {
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
//		// ������ ������ ��
//		if (gender.equals("��")) {
//			if (smm >= 34.8) {
//				this.smmResult = "����";
//			} else if (smm >= 28.5) {
//				this.smmResult = "ǥ��";
//			} else {
//				this.smmResult = "����";
//			}
//		}
//		// ������ ������ ��
//		else {
//			if (smm >= 17.02) {
//				this.smmResult = "����";
//			} else if (smm >= 15.41) {
//				this.smmResult = "ǥ��";
//			} else {
//				this.smmResult = "����";
//			}
//		}
//	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(int bmi) {
		this.bmi = bmi;
	}

	// bmi ��� : ü�� / (����)*(����)
	private void calcBmi() {
		double tmp;
		tmp = this.height * 0.01;
		this.bmi = this.weight * (tmp * tmp);

		resultBmi();
	}

	private void resultBmi() {
		if (bmi >= 35)
			bmiResult = "����";
		else if (bmi >= 30)
			bmiResult = "�ߵ���";
		else if (bmi >= 25)
			bmiResult = "�浵��";
		else if (bmi >= 23)
			bmiResult = "��ü��";
		else if (bmi >= 18.5)
			bmiResult = "����";
		else
			bmiResult = "��ü��";
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
