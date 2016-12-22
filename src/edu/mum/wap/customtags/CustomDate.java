package edu.mum.wap.customtags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomDate extends SimpleTagSupport {

	String color;
	String size;
	@Override
	public void doTag() throws JspException, IOException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd");
		JspWriter out = getJspContext().getOut();
		
			out.write(String.format("<span style='color:%s ; font-size:%s'> %s </span>", color, size+"px", ft.format(dNow)));	
	}
	          
	public void setColor(String color) {
		this.color = color;
	}
	public void setSize(String size) {
		this.size = size;
	}
		
	
}
