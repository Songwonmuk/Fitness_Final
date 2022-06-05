package net.scit.vo;

public enum ExerType {
	근력(1, "골격근량 증가"), 다이어트(2, "살을 빼자"), 밸런스(3, "몸의 균형과 코어 강화"), 마음안정(4, "스트레스 해소");

	private int code;
	private String Type;

	 ExerType(int code, String Type) {
	        this.code = code;
	        this.Type = Type;
	    }

	    public String getType() {
	        return this.Type;
	    }

	    public int getCode() {
	        return code;
	    }

	    public static ExerType of(int code) {
	        for (ExerType exerType : ExerType.values()) {
	            if (exerType.getCode() == code) {
	                return exerType;
	            }
	        }
	        //찾는 코드가 없는 경우
	        throw new IllegalArgumentException("운동 타입이 올바르지 않습니다.");
	    }
}
