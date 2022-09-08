package com.perfios.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.perfios.test.pojo.Worker;

public class WorkerData {
	
	// create connection and statement and return the statement object
	public Statement getStatement() {
		// initializing connection and statement objects
		Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //not required from mysql 8 onwards
            
        	// creating connection object using DriverManager
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WorkerMgt?autoReconnect=true&useSSL=false", "root", "password");
            
        	// creating statement object
        	stmt = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //returning statement object
        return stmt;
	}
	
	public int addWorker(Worker worker) {
		String sql = null;
		try {
			// insertig record into table
			sql = "insert into worker(first_name, last_name, salary, joining_date, department)"
					+ " values('" + worker.getFname() 
					+ "', '" + worker.getLname()
					+ "', " + worker.getSal() 
					+ ", '" + worker.getJoinDate()
					+ "', '" + worker.getDept() + "');";
			return getStatement().executeUpdate(sql);
		} catch (Exception e) {
			//catching the exception
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateWorker(Worker worker) {
		try {
			String sql = "update worker set salary = " + worker.getSal() 
						+ ", joining_date = '" + worker.getJoinDate() 
						+ "', department = '" + worker.getDept() 
						+"' where worker_id = " + worker.getWorkerId() + ";";
			return getStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int removeWorker(int workerId) {
		try {
            String sql = "delete from worker where worker_id = " + workerId + ";";
            return getStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
	}
	
	public List<Worker> getAllWorkers() {
		List<Worker> workerList = new ArrayList<Worker>();
        try {
            String sql = "select * from worker;";
            ResultSet rs = getStatement().executeQuery(sql);

            while(rs.next()) {
                Worker worker = new Worker();
                worker.setWorkerId(rs.getInt(1));
                worker.setFname(rs.getString(2));
                worker.setLname(rs.getString(3));
                worker.setSal(rs.getDouble(4));
                worker.setJoinDate(rs.getString(5));
                worker.setDept(rs.getString(6));
                workerList.add(worker);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workerList;
	}
	
	public List<String> getFnameLname() {
		List<String> workerList = new ArrayList<String>();
        try {
            String sql = "select concat(upper(first_name), '  ', upper(last_name)) as FULL_NAME "
            		+ "from worker;";
            ResultSet rs = getStatement().executeQuery(sql);

            while(rs.next()) {
                workerList.add(rs.getString("FULL_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workerList;
	}
	
	public List<String> getDepts() {
		List<String> depts = new ArrayList<String>();
		try {
            String sql = "select distinct department as DEPARTMENT from worker;";
            ResultSet rs = getStatement().executeQuery(sql);

            while(rs.next()) {
                depts.add(rs.getString("DEPARTMENT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return depts;
	}
	
	public int getCharPos(char ch, int workerId) {
		int pos = -1;
		try {
			String sql = "select position('" + ch + "' in first_name)\n"
					+ "from worker\n"
					+ "where worker_id = " + workerId + ";";
			
			ResultSet rs = getStatement().executeQuery(sql);
			if(rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pos;
	}

}
