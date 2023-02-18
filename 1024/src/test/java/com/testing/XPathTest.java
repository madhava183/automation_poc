package com.testing;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.nio.file.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class XPathTest 
{
    public static void main(String[] args) throws Exception 
    {
        //
    	
    	String returnStatus = getSummaryDetails(".//test-output//testng-results.xml");
    	String finalUpdate = updateTestcaseDetails(".//test-output//testng-results.xml",returnStatus);
    	writeToHtml(finalUpdate);
    	
        FileWriter fWriter = null;
        BufferedWriter writer = null;
        try {
            fWriter = new FileWriter(".//reports//Dashboard.html");
            writer = new BufferedWriter(fWriter);
            writer.write(finalUpdate);
            //writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
            writer.close(); //make sure you close the writer object 
        } catch (Exception e) {
          //catch any exceptions here
        }
    	//Build DOM
 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(".//test-output//testng-results.xml");
 
        //Create XPath
 
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
 
        XPathExpression expr1 = xpath.compile("//test-method");
        Object result1 = expr1.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes1 = (NodeList) result1;
//        System.out.println(nodes1.getLength());
//    	System.out.println(getNode(doc,xpath,"//testng-results[" + 1 + "]/@skipped"));
//    	System.out.println(getNode(doc,xpath,"//testng-results[" + 1 + "]/@failed"));
//    	System.out.println(getNode(doc,xpath,"//testng-results[" + 1 + "]/@passed"));
        
        // ['sample','2019-11-07T23:55:10Z','2019-11-07T23:55:10Z','24',"Passed"]
        
        String str1 = "";
        for(int intCnt=1;intCnt<=nodes1.getLength();intCnt++){
        	
        	String name = getNode(doc,xpath,"//test-method[" + intCnt + "]/@name");
        	String startTime = getNode(doc,xpath,"//test-method[" + intCnt + "]/@started-at");
        	String endTime = getNode(doc,xpath,"//test-method[" + intCnt + "]/@finished-at");
        	String status = getNode(doc,xpath,"//test-method[" + intCnt + "]/@status");
        	String duration = getNode(doc,xpath,"//test-method[" + intCnt + "]/@duration-ms");
        	String startArray = "['"+name+"','"+ startTime+"','"+endTime+"','"+duration+"','"+status+"]," ;
            	
        	str1 = str1+startArray;
        	
        	
//        XPathExpression expr = xpath.compile("//test-method[" + intCnt + "]//line");
//        Object result = expr.evaluate(doc, XPathConstants.NODESET);
//        NodeList nodes = (NodeList) result;
//        for (int i = 0; i < nodes.getLength(); i++) {           
//        	String str = nodes.item(i).getTextContent();
//        	System.out.println(str.trim());
//        }
//        System.out.println("End of Node");
       
        }
        System.out.println(str1);
        System.out.println(str1.substring(0,str1.length()-1));
 
        
 
    }
    
    
    public static String getNode(Document doc,XPath xpath,String xpathOfNode) throws XPathExpressionException{
    	XPathExpression expr = xpath.compile(xpathOfNode);
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        return nodes.item(0).getTextContent();
    }
    
   private static String getSummaryDetails(String sPath) throws Exception{
	   
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       factory.setNamespaceAware(true); // never forget this!
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document doc = builder.parse(sPath);

       //Create XPath

       XPathFactory xpathfactory = XPathFactory.newInstance();
       XPath xpath = xpathfactory.newXPath();

       XPathExpression expr1 = xpath.compile("//testng-results");
       Object result1 = expr1.evaluate(doc, XPathConstants.NODESET);
       NodeList nodes1 = (NodeList) result1;
       System.out.println(nodes1.getLength());
       System.out.println(getNode(doc,xpath,"//testng-results[" + 1 + "]/@skipped"));
       System.out.println(getNode(doc,xpath,"//testng-results[" + 1 + "]/@failed"));
       System.out.println(getNode(doc,xpath,"//testng-results[" + 1 + "]/@passed"));
       String summary = readFileAsString("C:\\Users\\hemanth\\Desktop\\Re\\Archive\\Dashboard_Template.HTML");
       String pass = summary.replace("strPass",getNode(doc,xpath,"//testng-results[" + 1 + "]/@passed"));
       String fail = pass.replace("strFail",getNode(doc,xpath,"//testng-results[" + 1 + "]/@failed"));
       String skipped = fail.replace("StrSkip",getNode(doc,xpath,"//testng-results[" + 1 + "]/@skipped"));
       
	return skipped;
       

   } 
   
   
   private static String readFileAsString(String fileName)throws Exception 
   { 
     String data = ""; 
     data = new String(Files.readAllBytes(Paths.get(fileName))); 
     return data; 
   }
   
   private static String updateTestcaseDetails(String inputPath,String result) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
	   String finalResult;
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       factory.setNamespaceAware(true); // never forget this!
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document doc = builder.parse(inputPath);

       //Create XPath

       XPathFactory xpathfactory = XPathFactory.newInstance();
       XPath xpath = xpathfactory.newXPath();

       XPathExpression expr1 = xpath.compile("//test-method");
       Object result1 = expr1.evaluate(doc, XPathConstants.NODESET);
       NodeList nodes1 = (NodeList) result1;
       String str1 = "";
       
       for(int intCnt=1;intCnt<=nodes1.getLength();intCnt++){       	
       	String name = getNode(doc,xpath,"//test-method[" + intCnt + "]/@name");
       	String startTime = getNode(doc,xpath,"//test-method[" + intCnt + "]/@started-at");
       	String endTime = getNode(doc,xpath,"//test-method[" + intCnt + "]/@finished-at");
       	String status = getNode(doc,xpath,"//test-method[" + intCnt + "]/@status");
       	String duration = getNode(doc,xpath,"//test-method[" + intCnt + "]/@duration-ms");
       	String startArray = "['"+name+"','"+ startTime+"','"+endTime+"','"+duration+"','"+status+"']," ;           	
       	str1 = str1+startArray;	      
       }
       
       System.out.println(str1);
       System.out.println(str1.substring(0,str1.length()-1));
       
       
	return finalResult = result.replace("strTestCases",str1.substring(0,str1.length()-1));
       

   }
   
   private static void writeToHtml(String output){
       FileWriter fWriter = null;
       BufferedWriter writer = null;
       try {
           fWriter = new FileWriter(".//reports//Dashboard.html");
           writer = new BufferedWriter(fWriter);
           writer.write(output);
           //writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
           writer.close(); //make sure you close the writer object 
       } catch (Exception e) {
         //catch any exceptions here
       }
   }
    
    
}