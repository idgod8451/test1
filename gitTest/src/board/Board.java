package board;

import java.sql.Date;

public class Board {
	private int num;
	private String writer;
	private Date wdate;
	private String content;
	private String title;

	public Board() {
	}

	public Board(int num, String writer, Date wdate, String content, String title) {
		super();
		this.num = num;
		this.writer = writer;
		this.wdate = wdate;
		this.content = content;
		this.title = title;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", writer=" + writer + ", wdate=" + wdate + ", content=" + content + ", title="
				+ title + "]";
	}

}
