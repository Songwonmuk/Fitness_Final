package net.scit.vo;

public enum ExerType {
	�ٷ�(1, "��ݱٷ� ����"), ���̾�Ʈ(2, "���� ����"), �뷱��(3, "���� ������ �ھ� ��ȭ"), ��������(4, "��Ʈ���� �ؼ�");

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
	        //ã�� �ڵ尡 ���� ���
	        throw new IllegalArgumentException("� Ÿ���� �ùٸ��� �ʽ��ϴ�.");
	    }
}
