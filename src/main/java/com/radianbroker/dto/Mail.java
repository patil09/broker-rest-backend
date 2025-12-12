package com.radianbroker.dto;

import java.util.Map;

public class Mail {

	private String from;
	private String[] to;
	private String[] cc;
	private String subject;
	private String content;
	private Map<String, Object> model;
	private String template;;

	public Mail() {
		super();
	}

	public Mail(String from, String[] to, String[] cc, String subject, String content, Map<String, Object> model,
                String template) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.content = content;
		this.model = model;
		this.template = template;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	@Override
	public String toString() {
		return "Mail [from=" + from + ", to=" + to + ", subject=" + subject + ", content=" + content + ", model="
				+ model + ", template = " + template + "]";
	}

}