package com.zcmzjp.wx.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private String joinData;

	public String getJoinData() {
		return joinData;
	}

	public void setJoinData(String joinData) {
		this.joinData = joinData;
	}

	private static final Map<String, String> dataProps =Cache.getDataProps();

	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		String string=doFileter();
		ServletInputStream bf=new BufferedServletInputStream(string.getBytes(super.getCharacterEncoding()));
		return bf;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] conte = super.getParameterValues(name);
		String[] s = new String[conte.length];
		for (int i = 0; i < conte.length; i++) {
			s[i] = filter(conte[i]);
		}
		return s;
	}

	@Override
	public int getContentLength(){
		int contentLen=super.getContentLength();
		try{
			String string=doFileter();
                        if(string==null) {return 0;}
			contentLen=string.getBytes(super.getCharacterEncoding()).length;
		}catch (IOException e){}
		return contentLen;
	}

	public String filter(String content) {
		for (String key : dataProps.keySet()) {
			if (content != null && content.contains(key)) {
				content = content.replaceAll(key, dataProps.get(key));
			}
		}
		return content;
	}
	public  String doFileter() throws  IOException {
		if (null!=joinData&&!joinData.trim().isEmpty()){
			return joinData;
		}
		int clen = super.getContentLength();
		//过滤json对象中的非法字符串
		if (clen > 0) {
			byte buffer[] = new byte[clen];
			for (int i = 0; i < clen; i++) {
				int readlen = super.getInputStream().read(buffer, i, clen - i);
				if (readlen == -1) {
					break;
				}
				i += readlen;
			}
			String string = new String(buffer, super.getCharacterEncoding());
			string = filter(string);
			setJoinData(string);
			return string;
		}
		return null;
	}
}
