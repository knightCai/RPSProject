package com.service.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.service.common.JdbcUtils;
import com.service.model.Oddnumber;
import com.service.service.OddnumberService;

@WebService
public class OddnumberServiceImpl implements OddnumberService {

	private JdbcUtils jdbcutil = new JdbcUtils();;
	private String sql;
	
	public String saveOddnum(Oddnumber Oddnum) {
		//oddnumberDao.saveOddnum(Oddnum);
		Oddnum.setCreatetime(new Date());
		sql = "insert into t_zy_oddnumber values(?,?,?,?,?,?,?,?,?,?)";
		List params = new ArrayList();
		params.add(Oddnum.getPkid());
		params.add(Oddnum.getStartnum());
		params.add(Oddnum.getEndnum());
		params.add(Oddnum.getSuplusnum());
		params.add(Oddnum.getAmountnum());
		params.add(Oddnum.getUsenum());
		params.add(Oddnum.getState());
		params.add(Oddnum.getWarnnum());
		params.add(Oddnum.getCreatetime());
		params.add(Oddnum.getUserid());
		try {
			jdbcutil.getConnection();
			jdbcutil.updateByPreparedStatement(sql, params);
			jdbcutil.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
			return "999," + e.getMessage();
		}
		return "000";
	}
	public String deleteOddnum(Oddnumber Oddnum) {
		try {
			sql = "delete from t_zy_oddnumber where PKID = ?";
			List params = new ArrayList();
			params.add(Oddnum.getPkid());
			jdbcutil.getConnection();
			jdbcutil.updateByPreparedStatement(sql, params);
			jdbcutil.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
			return "999," + e.getMessage();
		}
		return "000";
	}
	public List<Oddnumber> findAllOddnum() {
		List<Oddnumber> list = new ArrayList();
		try {
			sql = "select * from t_zy_oddnumber";
			jdbcutil.getConnection();
			ResultSet rs = jdbcutil.findMoreResult(sql,null);
			while(rs.next()){
	                 Oddnumber Oddnum=new Oddnumber();
	                 Oddnum.setPkid(rs.getString(1));
	         		Oddnum.setStartnum(rs.getString(2));
	         		Oddnum.setEndnum(rs.getString(3));
	         		Oddnum.setSuplusnum(rs.getInt(4));
	         		Oddnum.setAmountnum(rs.getInt(5));
	         		Oddnum.setUsenum(rs.getString(6));
	         		Oddnum.setState(rs.getString(7));
	         		Oddnum.setWarnnum(rs.getInt(8));
	         		Oddnum.setCreatetime(rs.getDate(9));
	         		Oddnum.setUserid(rs.getString(10));
	                list.add(Oddnum);
			}
			jdbcutil.releaseConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		//return oddnumberDao.findAllOddnum();
	}
	public Oddnumber findOddnumByPkId(String pkId) {
		return null;
	}
	public String updateOddnum(Oddnumber Oddnum) {
		try{
			List params = new ArrayList();
			params.add(Oddnum.getSuplusnum());
			params.add(Oddnum.getUsenum());
			params.add(Oddnum.getState());
			params.add(Oddnum.getPkid());
			sql = "update t_zy_oddnumber set suplusnum=?,usenum=?,state=? where PKID=?";
			jdbcutil.getConnection();
			jdbcutil.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return "999," + e.getMessage();
		}
		return "000";
	}
	
	/**
	 * 获取当前可用报关单号，并更新其使用情况
	 * @param userid
	 * @return
	 */
	public Oddnumber getUseOddnumber(String userid) {
		Oddnumber Oddnum=new Oddnumber();
		List list = new ArrayList();
		list.add(userid);
		try {
			sql = "select * from t_zy_oddnumber where userid = ? and state = '0' order by createtime";
			jdbcutil.getConnection();
			ResultSet rs = jdbcutil.findMoreResult(sql,list);
			if(rs.next()){
                Oddnum.setPkid(rs.getString(1));
         		Oddnum.setStartnum(rs.getString(2));
         		Oddnum.setEndnum(rs.getString(3));
         		Oddnum.setSuplusnum(rs.getInt(4));
         		Oddnum.setAmountnum(rs.getInt(5));
         		Oddnum.setUsenum(rs.getString(6));
         		Oddnum.setState(rs.getString(7));
         		Oddnum.setWarnnum(rs.getInt(8));
         		Oddnum.setCreatetime(rs.getDate(9));
         		Oddnum.setUserid(rs.getString(10));
				
				String usenum = Oddnum.getUsenum();
				int suplusnum = Oddnum.getSuplusnum();
				//更新最后一次使用的号码
				if(usenum.equals("")){
					Oddnum.setUsenum(Oddnum.getStartnum());
				}else{
					Oddnum.setUsenum((Long.parseLong(Oddnum.getUsenum())+1)+"");
				}
				//剩余量为1时，修改状态为 1-已用完
				if(suplusnum==1){
					Oddnum.setSuplusnum(0);
					Oddnum.setState("1");
				}else{
					Oddnum.setSuplusnum(suplusnum-1);
				}
				//更新报关单号信息
				updateOddnum(Oddnum);
				jdbcutil.releaseConn();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Oddnum;
	}

}
