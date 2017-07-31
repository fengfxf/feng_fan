package com.websocket.test.model;

public class ReceiveMessage {
	//消息id
	private String id;
	//1私聊2群聊
	private Integer groupType;
	//群聊的id
	private Integer groupId;
	//发送消息的用户id
	private Integer fromUser;
	//私聊时接收者id
	private Integer toUser;
	//消息类型1文本2图片3语音4视频5url
	private Integer msgType;
	private String msgText;
	private Object msgMultimedia;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getGroupType() {
		return groupType;
	}
	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getFromUser() {
		return fromUser;
	}
	public void setFromUser(Integer fromUser) {
		this.fromUser = fromUser;
	}
	public Integer getToUser() {
		return toUser;
	}
	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public String getMsgText() {
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	public Object getMsgMultimedia() {
		return msgMultimedia;
	}
	public void setMsgMultimedia(Object msgMultimedia) {
		this.msgMultimedia = msgMultimedia;
	}
}
