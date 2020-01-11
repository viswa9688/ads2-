import java.io.*;
import java.util.HashMap;

import sun.awt.SunHints.Value;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*; 
public class WordNet
{
	static ArrayList<String> key=new ArrayList<String>();
	static ArrayList<String[]> value=new ArrayList<String[]>();
	public void parseHypernyms(String filename) throws IOException 
  { 
		File read= new File(filename);
		
		BufferedReader buf_read=new BufferedReader(new FileReader(read));
		
		String line_reader;
		int count=0;
		
		while((line_reader=buf_read.readLine())!=null)
		{
			
			String[] read_content=line_reader.split(",",2);
			count++;
			if(read_content.length>1) {
				key.add(read_content[0]);
				value.add(read_content[1].split(","));
				
			}
			else {
				key.add(read_content[0]);
				value.add(null);
			}
		}
		
  }
	public void parse_emails(String filename) throws IOException
	{
		File read= new File(filename);
		
		BufferedReader buf_read=new BufferedReader(new FileReader(read));
		
		String line_reader;
		int count=0;
		
		while((line_reader=buf_read.readLine())!=null)
		{
			String filtered = line_reader.replaceAll("[^0-9,]","");
			String[] numbers = filtered.split(",");
			count++;
			if(numbers.length>1) {
				key.add(numbers[0]);
				value.add(numbers[1].split(","));
				
			}
			else {
				key.add(numbers[0]);
				value.add(null);
			}
			
		}
		//System.out.println(key+" "+value);	
		
		
	}

  public static void main(String[] args) throws Exception
  {
    WordNet Wn_obj =new WordNet();
    int count1 = 0;
    //Wn_obj.parseHypernyms("D:\\ADS -2\\wordnet\\hypernyms.txt");
    Wn_obj.parse_emails("D:\\ADS -2 - Copy_uncorrupted\\ADS - 2 Exam - 1\\email-logs.txt");
    //obj.readFileWithScanner("D:\\ADS -2\\wordnet\\Synsets.txt");
    Digraph d= new Digraph(key.size());
    int count=0;
    for(int i=0;i<key.size();i++)
    {
    	count1++;
    	if(value.get(i)!=null)
    	{
    		
    		
    		for(int j=0;j<value.get(i).length;j++)
    		{
    			int k=Integer.parseInt(key.get(i));
    			int v=Integer.parseInt(value.get(i)[j]);
    			d.addEdge(k,v);
    			count++;
    			
    		}
    	}
    }
    System.out.println(count);
//    In in = new In("digraph1.txt");
//    Digraph d1 = new Digraph(in);
//    SAP sap = new SAP(d1);
//
//    System.out.println(sap.length(3,11) + " " + sap.ancestor(3,11));
//    System.out.println(sap.length(9,12) + " " + sap.ancestor(9,12));
//    System.out.println(sap.length(7,2) + " " + sap.ancestor(7,2));
//    System.out.println(sap.length(1,6) + " " + sap.ancestor(1,6));

  }
  
}