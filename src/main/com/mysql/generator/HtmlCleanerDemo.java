package com.mysql.generator;

import java.io.File;
import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class HtmlCleanerDemo
{

	public static void main(String[] args) throws Exception
	{
		try
		{


			HtmlCleaner cleaner = new HtmlCleaner();

			TagNode node = cleaner.clean(new File("/Users/quyixiao/Desktop/kaka.html"), "GBK");
			//按tag取.
			Object[] ns = node.getElementsByName("title", true);    //标题

			if(ns.length > 0) {
				System.out.println("title="+((TagNode)ns[0]).getText());
			}
			System.out.println("ul/li:");
			//按xpath取
			ns = node.evaluateXPath("//div[@class='d_1']//li");
			System.out.println(ns.length);
			for(Object on : ns) {
				TagNode n = (TagNode) on;
				System.out.println("\ttext="+n.getText());
			}
			System.out.println("a:");
			//按属性值取
			ns = node.getElementsByAttValue("name", "my_href", true, true);
			for(Object on : ns) {
				TagNode n = (TagNode) on;
				System.out.println("\thref="+n.getAttributeByName("href")+", text="+n.getText());
			}

		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 * @param s
	 *            需格式化的字符串
	 * @param dStart
	 *            需删除字符串的开头
	 * @param dEnd
	 *            需删除字符串的结尾
	 * @return 格式化后的字符串
	 */
	public static StringBuffer formatContent(StringBuffer s, String dStart,
			String dEnd)
	{

		int start = s.indexOf(dStart);
		int end = 0;
		if (start > end)
		{
			end = s.indexOf(dEnd);
			if (start < end && start >= 0)
			{
				s.delete(start, end + dEnd.length());
			}
		}
		return s;
	}
}
