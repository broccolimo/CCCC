package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

import assist.Assist;

public class CCCC extends Assist{
	
	//二分查找 求某元素出现的最后一个位置
	public int C001(int[] a, int target){
		int l = a.length;
		if(l == 0) return -1;
		int lo = 0;
		int hi = l - 1;
		//如果是lo<=hi 可能会无限循环
		while(lo < hi){
			//不加1可能会陷入死循环
			int mid = (lo + hi + 1) / 2;
			if(target < a[mid]) hi = mid - 1;
			//因为要找的是最后一次出现的位置，所以要把mid放入后半段
			else lo = mid;
		}
		//这里写lo或者hi都行 因为最后lo肯定是等于hi才跳出了循环
		if(a[lo] == target) return lo;
		return -1;
	}
	
	//二分查找 求某元素出现的第一个位置
	public int C002(int[] a, int target){
		int l = a.length;
		if(l == 0) return -1;
		int lo = 0;
		int hi = l - 1;
		//若为lo<=hi可能会死循环 最后是2个相同的下标在比较
		while(lo < hi){
			//若为lo+hi+1可能会死循环 
			//经过一次循环发现lo和hi的状态没有变化 即为死循环
			//可以试 就2种情况 加1与不加1
			int mid = (lo + hi) / 2;
			if(target <= a[mid]) hi = mid;
			else lo = mid + 1;
		}
		//这里写lo或者hi都行 因为最后lo肯定是等于hi才跳出了循环
		if(a[lo] == target) return lo;
		return -1;
	}
	
	@Test
	//IO COPY
	public void C003(){
		String source = "E:\\MyEclipse_default_workspace\\新建文本文档.txt";
		String target = "E:\\MyEclipse_default_workspace\\c.txt";
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(new File(source));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			os = new FileOutputStream(new File(target));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		int r = 0;
		try {
			while((r = is.read(buffer)) != -1){
				os.write(buffer, 0, r);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	//NIO COPY
	public void C004(){
		String source = "E:\\MyEclipse_default_workspace\\c.txt";
		String target = "E:\\MyEclipse_default_workspace\\CCCC.txt";
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			is = new FileInputStream(new File(source));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			os = new FileOutputStream(new File(target));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FileChannel ifc = is.getChannel();
		FileChannel ofc = os.getChannel();
		ByteBuffer bb = ByteBuffer.allocate(1024);
		try {
			while(ifc.read(bb) != -1){
				bb.flip();
				ofc.write(bb);
				bb.clear();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	//给个文件 找出文件中某单词出现的次数
	public void C005(){
		String source = "E:\\MyEclipse_default_workspace\\q.txt";
		String word = "线程";
		FileReader fr = null;
		try {
			fr = new FileReader(source);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		int count = 0;
		String line = null;
		try {
			while((line = br.readLine()) != null){
				int index = -1;
				while(line.length() >= word.length() && (index = line.indexOf(word)) >= 0){
					count++;
					line = line.substring(index + word.length());
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(count);
	}
	
	@Test
	//列出某目录下的所有文件名（仅限当前目录）
	public void C006(){
		String pathname = "E:\\MyEclipse_default_workspace\\LeetCode";
		File file = new File(pathname);
		for(File f : file.listFiles()){
			if(f.isFile()){
				System.out.println(f.getName());
			}
		}
	}
	
	@Test
	//列出某目录下的所有文件名 （需要展开文件夹）
	public void C007(){
		String pathname = "E:\\MyEclipse_default_workspace\\CCCC";
		File file = new File(pathname);
		WorkDirectory(file);
	}
	
	@Test
	public void t1(){
		String source = "E:\\MyEclipse_default_workspace\\q.txt";
		String target = "E:\\MyEclipse_default_workspace\\w.txt";
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			is = new FileInputStream(new File(source));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			os = new FileOutputStream(new File(target));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FileChannel ifc = is.getChannel();
		FileChannel ofc = os.getChannel();
		ByteBuffer bb = ByteBuffer.allocate(4096);
		System.out.println("init-----------------");
		System.out.println("position: " + bb.position());
		System.out.println("limit: " + bb.limit());
		System.out.println("capacity: " + bb.capacity());
		System.out.println("---------------------");
		try {
			System.out.println("ifc_init_position: " + ifc.position());
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		int count = 1;
		int read = 0;
		try {
			while((read = ifc.read(bb)) != -1 && count <= 3){
				System.out.println("read: " + read);
				System.out.println("filp前-----------------");
				System.out.println("position: " + bb.position());
				System.out.println("limit: " + bb.limit());
				System.out.println("capacity: " + bb.capacity());
				System.out.println("---------------------");
				System.out.println("ifc_beforeFlip_position: " + ifc.position());
				bb.flip();
				System.out.println("filp后write前-----------------");
				System.out.println("position: " + bb.position());
				System.out.println("limit: " + bb.limit());
				System.out.println("capacity: " + bb.capacity());
				System.out.println("---------------------");
				System.out.println("ifc_flip~write_position: " + ifc.position());
				ofc.write(bb);
				System.out.println("write后-----------------");
				System.out.println("position: " + bb.position());
				System.out.println("limit: " + bb.limit());
				System.out.println("capacity: " + bb.capacity());
				System.out.println("---------------------");
				System.out.println(ifc.position());
				System.out.println("ifc_write~clear_position: " + ifc.position());
				bb.clear();
				System.out.println(ifc.position());
				System.out.println("clear后-----------------");
				System.out.println("position: " + bb.position());
				System.out.println("limit: " + bb.limit());
				System.out.println("capacity: " + bb.capacity());
				System.out.println("---------------------");
				System.out.println("ifc_afterClear_position: " + ifc.position());
				count++;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				ifc.close();
				ofc.close();
				is.close();
				os.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void e1(){
		String source = "E:\\MyEclipse_default_workspace\\q.txt";
		String word = "线程";
		FileReader fr = null;
		try {
			fr = new FileReader(new File(source));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		int count = 0;
		String line = null;
		try {
			while((line = br.readLine()) != null){
				int index = -1;
				while(line.length() >= word.length() && (index = line.indexOf(word)) >= 0){
					line = line.substring(index + word.length());
					count++;
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(count);
	}
	
}
