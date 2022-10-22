package com.spring.board.vo;

import java.util.List;

public class BoardVo {
	private int pageNo = 0;
	private String 	boardType;
	private int 	boardNum;
	private String 	boardTitle;
	private String 	boardComment;
	private String 	creator;
	private String	modifier;
	private String codeName;
	private int totalCnt;
	private List<String> boardArr;
	
	private List<BoardVo> insertList;
	
	private int insertAllNum;
	
	


	

	

	public int getInsertAllNum() {
		return insertAllNum;
	}

	public void setInsertAllNum(int insertAllNum) {
		this.insertAllNum = insertAllNum;
	}

	public List<BoardVo> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<BoardVo> insertList) {
		this.insertList = insertList;
	}

	public List<String> getBoardArr() {
		return boardArr;
	}

	public void setBoardArr(List<String> boardArr) {
		this.boardArr = boardArr;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
}
