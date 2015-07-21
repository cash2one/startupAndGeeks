package com.cmcc.edu.entity;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String pubstate;

    private Date publishtime;

    private String sendtype;

    private Date sendtime;

    private String hidden;

    private String noticetype;

    private Long sendId;

    private String smstype;

    private Date creatdate;
   
    private Date starttime;

    private Date endtime;
    
    
    private int total=0;
    
    private Date receivetime;

    private String isread;
    
    private Long receiveid;

    private String receivetype;
    
    private Long pId;
    
    private String sendName;
    
    private String searchType;
    
    private String time;
    
    private String personStr;
    
    private String personName;
    
    private Integer type;

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

    public String getNoticetype() {
        return noticetype;
    }

    public void setNoticetype(String noticetype) {
        this.noticetype = noticetype == null ? null : noticetype.trim();
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }


    public String getSmstype() {
        return smstype;
    }

    public void setSmstype(String smstype) {
        this.smstype = smstype == null ? null : smstype.trim();
    }


    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
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


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}

	public Long getReceiveid() {
		return receiveid;
	}

	public void setReceiveid(Long receiveid) {
		this.receiveid = receiveid;
	}

	public String getReceivetype() {
		return receivetype;
	}

	public void setReceivetype(String receivetype) {
		this.receivetype = receivetype;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPersonStr() {
		return personStr;
	}

	public void setPersonStr(String personStr) {
		this.personStr = personStr;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
    
    
}