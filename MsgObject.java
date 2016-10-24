package message;

public class MsgObject {

	public int value;
	public String name;
	public boolean isHandled=false;
	public MsgObject(String s,int i){
		this.name=s;
		this.value=i;
	}
	

	public void setMsgValue(int value) {
		this.value = value;
	}
	public void setMsgName(String name) {
		this.name = name;
	}
	public void isNowHandled(){
		this.isHandled=true;
	}

}
