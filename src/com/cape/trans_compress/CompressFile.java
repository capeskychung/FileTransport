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
	 * parameter: inputFileΪ�����ļ���
	 */
	public void compress(String inputFile) throws IOException{
		//������ȡ�������ļ�����Ȼ����������ļ���+zip��Ϊѹ�����ļ���
		String outputFile = "test.zip";
		System.out.println(outputFile);
		compress(outputFile, new File(inputFile));
		
	}
	
	/*
	 * parameter: inputFileΪ�����ļ�
	 * parameter: outputFileΪ����ļ���
	 */
	public void compress(String outputFile,File inputFile) throws IOException{
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
		compress(out,inputFile,"");
		System.out.println("zip done");
		out.close();
	}
	
	
	/*
	 * parameter: outΪѹ���ļ��������
	 * parameter: FileΪ��Ҫѹ�����ļ�
	 * parameter: baseΪ����ļ���·��
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
           book.compress("E:\\Workspace\\Projects");//��Ҫѹ�����ļ���
        }catch (Exception ex) {
           ex.printStackTrace();
       }
    }
}