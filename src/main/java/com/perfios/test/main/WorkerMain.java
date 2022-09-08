package com.perfios.test.main;

import java.util.List;
import java.util.Scanner;

import com.perfios.test.dao.WorkerData;

public class WorkerMain {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		WorkerData wd = new WorkerData();
		
		List<String> names = wd.getFnameLname();
		System.out.println("FULL_NAME");
		System.out.println("------------------");
		for(int i = 0; i < names.size(); ++i)
			System.out.println(names.get(i));
		
		System.out.println();
		
		
		List<String> depts = wd.getDepts();
		System.out.println("DEPARTMENTS");
		System.out.println("-----------------");
		for(int i = 0; i < depts.size(); ++i)
			System.out.println(depts.get(i));
		
		System.out.println();
		
		System.out.print("Enter worker id: ");
		int id = sc.nextInt();
		System.out.print("Enter character: ");
		char ch = sc.next().charAt(0);
		int pos = wd.getCharPos(ch, id);
		System.out.println(pos);
		
	}

}
