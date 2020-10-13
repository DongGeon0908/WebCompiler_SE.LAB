package bulletinBoard;

import java.util.ArrayList;

public class forCommentList {
	String id;
	String text;
	int postNum;
	int commentNum;
	ArrayList <forComment2List> comment2List = new ArrayList<forComment2List>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public ArrayList<forComment2List> getComment2List() {
		return comment2List;
	}
	public void setComment2List(ArrayList<forComment2List> comment2List) {
		this.comment2List = comment2List;
	}
}
