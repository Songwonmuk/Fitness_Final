package net.scit.vo;

public class Member {

	//db에서 자동 설정
	int memberId; //멤버 id
	//직접 설정
	String memberPw; //멤버 pw
	String memberName; //멤버 이름
	String phone; //전화번호
	String gender; //성별 (남, 여)
	String birth; //생일
	String joindate; //가입 날짜
	
	int managerId; //fk

	public Member() {
		super();
	}

	public Member(String member_pw, String member_name, String phone, String gender, String birth) {
		super();
		this.memberPw = member_pw;
		this.memberName = member_name;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
	}

	public Member(int memberId, String memberPw, String memberName, String phone, String gender, String birth,
			String joindate, int managerId) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.joindate = joindate;
		this.managerId = managerId;
	}
	
//setter getter
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", phone=" + phone + ", gender=" + gender + ", birth=" + birth + ", joindate=" + joindate + "]";
	}
	
	public boolean isMatched(String password) {
		return this.memberPw.equals(password);
	}
}
