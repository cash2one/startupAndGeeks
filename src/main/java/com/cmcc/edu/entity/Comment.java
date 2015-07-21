package com.cmcc.edu.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String pubstate;

    private Date publishtime;

    private String sendtype;

    private Date sendtime;

    private String hidden;

    private Integer sendId;

    private String receiveStuId;

    private String isread;

    private Date creatdate;

    private Date receivetime;
    
    private Date starttime;

    private Date endtime;
    
    private int total=0;
    
    private String publishtimeName;//发送时间
    
    private String sendtimeName;//定时发送
    
    private String receiveStuIdName;//接收人name
    

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPubstate() {
        return pubstate;
    }

    public void setPubstate(String pubstate) {
        this.pubstate = pubstate == null ? null : pubstate.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getSendtype() {
        return sendtype;
    }

    public void setSendtype(String sendtype) {
        this.sendtype = sendtype == null ? null : sendtype.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden == null ? null : hidden.trim();
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public String getReceiveStuId() {
        return receiveStuId;
    }

    public void setReceiveStuId(String receiveStuId) {
        this.receiveStuId = receiveStuId == null ? null : receiveStuId.trim();
        if(receiveStuId!=null&&!receiveStuId.trim().equals("")){
        	String[] StuIdnum=receiveStuId.trim().split(",");
        	total+=StuIdnum.length;
        }
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread == null ? null : isread.trim();
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getPublishtimeName() {
		return publishtimeName;
	}

	public void setPublishtimeName(String publishtimeName) {
		this.publishtimeName = publishtimeName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSendtimeName() {
		return sendtimeName;
	}

	public void setSendtimeName(String sendtimeName) {
		this.sendtimeName = sendtimeName;
	}

	public String getReceiveStuIdName() {
		return receiveStuIdName;
	}

	public void setReceiveStuIdName(String receiveStuIdName) {
		this.receiveStuIdName = receiveStuIdName;
	}
    
    
    
}