/*
 * Author:capesky
 * Date:2014-09-28
 * Description:This class is about file compress
 */
package com.cape.trans_compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class CompressFile {
	
	/*
	 * parameter: inputFile为输入文件名
	 */
	public void compress(String inputFile) throws IOException{
		//可以提取出输入文件名，然后以输入的文件名+zip作为压缩的文件名
		String outputFile = "test.zip";
		System.out.println(outputFile);
		compress(outputFile, new File(inputFile));
		
	}
	
	/*
	 * parameter: inputFile为输入文件
	 * parameter: outputFile为输出文件名
	 */
	public void compress(String outputFile,File inputFile) throws IOException{
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
		compress(out,inputFile,"");
		System.out.println("zip done");
		out.close();
	}
	
	
	/*
	 * parameter: out为压缩文件输输出流
	 * parameter: File为需要压缩的文件
	 * parameter: base为输出文件的路径
	 */
	public void compress(ZipOutputStream out, File f, String base) throws IOException{
		if (f.isDirectory()) {
			if(base==""){		
				out.putNextEntry(new ZipEntry(f.getName()+"/"));
				base += f.getName()+"/";
				System.out.println(base);
			}
			else {
				out.putNextEntry(new ZipEntry(base + "/"+f.getName()));
	        	base = base + "/";
	        	System.out.println(base);
			}
		        File[] fl = f.listFiles();
		       
		        for (int i = 0; i < fl.length; i++) {
		        	compress(out, fl[i], base + fl[i].getName());
		        }
		        
	     }else {
	    	 out.putNextEntry(new ZipEntry(base));
	         FileInputStream in = new FileInputStream(f);
	         int b;
	         //System.out.println(base);
	         while ( (b = in.read()) != -1) {
	            out.write(b);
	         }
	         in.close();
	     }
	}
	public static void main(String [] temp){
		CompressFile book = new CompressFile();
        try {
           book.compress("E:\\Workspace\\Projects");//你要压缩的文件夹
        }catch (Exception ex) {
           ex.printStackTrace();
       }
    }
}