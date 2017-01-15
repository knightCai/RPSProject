package com.service.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;


import com.service.common.JdbcUtils;
import com.service.model.Logisticslisting;
import com.service.service.LogisticslistingService;

@WebService
public class LogisticslistingServiceImpl implements LogisticslistingService {

	private JdbcUtils jdbcutil = new JdbcUtils();;
	private String sql;
	private PreparedStatement ps;
	private OddnumberServiceImpl oddnumSer;
	
	
	public void saveLlist(Logisticslisting Llist) {
	}
	
	/**
	 * 批量数据插入
	 * 防SQL注入
	 * 防止内存不足：分批提交，批量大小为1000，每1000个查询语句为一批插入提交。
	 * @param Llists
	 * @return
	 */
	public String saveBatch(List<Logisticslisting> Llists){
		try{
			oddnumSer = new OddnumberServiceImpl();
			String tmpSerialnum = "0";	//记录上一条数据的导入序号，用于判断是否归类为同一面单
			String tmpUsenum = "0";		//记录上一条数据的运单号码，用于判断是否获取新号码
			sql = "insert into t_zy_logisticslisting values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = jdbcutil.getConnection().prepareStatement(sql);
			final int batchSize = 1000;
			int count = 0;
			for(Logisticslisting llist : Llists) {
				ps.setString(1, llist.getPkid());
				//相同序列号使用原报关单号
				if(!tmpSerialnum.equals(llist.getSerialnum())){
					tmpUsenum = oddnumSer.getUseOddnumber("admin").getUsenum();
				}
				tmpSerialnum = llist.getSerialnum();
				ps.setString(2, tmpUsenum);
			  	ps.setString(3, tmpSerialnum);
				ps.setString(4, llist.getExpressnum());
				ps.setString(5, llist.getBrand());
				ps.setString(6, llist.getCargoname());
				ps.setString(7, llist.getCargotype());
				ps.setString(8, llist.getDeclareweight());
				ps.setString(9, llist.getDeclareprice());
				ps.setString(10, llist.getDeclarepricesum());
				ps.setString(11, llist.getLegalnum());
				ps.setString(12, llist.getLegalunit());
				ps.setString(13, llist.getNetweight());
				ps.setString(14, llist.getCargoid());
				ps.setString(15, llist.getPackagecount());
				ps.setString(16, llist.getCount());
				ps.setString(17, llist.getConsigneename());
			   	ps.setString(18, llist.getConsigneeaddr());
			   	ps.setString(19, llist.getConsigneephone());
			   	ps.setString(20, llist.getConsignercardid());
			   	ps.setString(21, llist.getConsignername());
			   	ps.setString(22, llist.getConsigneraddr());
			   	ps.setString(23, llist.getConsignerphone());
			   	ps.setString(24, llist.getConsignercountry());
			   	ps.setString(25, llist.getConsigneecountry());
			   	ps.setString(26, llist.getIsprint());
			   	ps.setString(27, llist.getImportnum());
			   	ps.setDate(28, new java.sql.Date(llist.getCreatetime().getTime()));
			   	ps.setString(29, llist.getImportuser());
			    ps.addBatch();
			    if(++count % batchSize == 0) {
			        ps.executeBatch();
			    }
			}
			ps.executeBatch();
			ps.close();
			jdbcutil.releaseConn();
		} catch (Exception e) {
			e.printStackTrace();
			return "999," + e.getMessage();
		}
		return "000";
	}
	public void deleteLlist(Logisticslisting Llist) {
	}
	public List<Logisticslisting> findAllLlist() {
		return null;
	}
	
	/**
	 * 根据参数查询
	 * 由于webservice不支持map参数，故使用数组来代替，格式为 {"key:value","key1:value1"}
	 */
	public List<Logisticslisting> findLlistByParams(String[] params) {
		List<Logisticslisting> list = new ArrayList<Logisticslisting>();
		try{
			sql = "select * from t_zy_logisticslisting where 1=1";
			for(int i = 0; i < params.length ; i++){
				String[] str = params[i].split(":");
				if(!str[1].equals("")){
					sql = sql + " and " + str[0] + " ='" + str[1] + "'";
				}
			}
			sql = sql + " order by importnum,CONVERT(serialnum,SIGNED)";
			jdbcutil.getConnection();
			ResultSet rs = jdbcutil.findMoreResult(sql,null);
			while(rs.next()){
				Logisticslisting logis = new Logisticslisting();
				logis.setPkid(rs.getString(1));
				logis.setDeclarenum(rs.getString(2));
				logis.setSerialnum(rs.getString(3));
				logis.setExpressnum(rs.getString(4));
				logis.setBrand(rs.getString(5));
				logis.setCargoname(rs.getString(6));
				logis.setCargotype(rs.getString(7));
				logis.setDeclareweight(rs.getString(8));
				logis.setDeclareprice(rs.getString(9));
				logis.setDeclarepricesum(rs.getString(10));
				logis.setLegalnum(rs.getString(11));
				logis.setLegalunit(rs.getString(12));
				logis.setNetweight(rs.getString(13));
				logis.setCargoid(rs.getString(14));
				logis.setPackagecount(rs.getString(15));
				logis.setCount(rs.getString(16));
				logis.setConsigneename(rs.getString(17));
				logis.setConsigneeaddr(rs.getString(18));
				logis.setConsigneephone(rs.getString(19));
				logis.setConsignercardid(rs.getString(20));
				logis.setConsignername(rs.getString(21));
				logis.setConsigneraddr(rs.getString(22));
				logis.setConsignerphone(rs.getString(23));
				logis.setConsignercountry(rs.getString(24));
				logis.setConsigneecountry(rs.getString(25));
				logis.setIsprint(rs.getString(26));
				logis.setImportnum(rs.getString(27));
				logis.setCreatetime(rs.getDate(28));
				logis.setImportuser(rs.getString(29));
		   		list.add(logis);
			}
			jdbcutil.releaseConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public Logisticslisting findLlistByPkId(String pkId) {
		return null;
	}
	@Override
	public void updateLlist(Logisticslisting llist) {
		/*try{
			sql = "update t_zy_logisticslisting set ";
			for(int i = 0; i < params.length ; i++){
				String[] str = params[i].split(":");
				if(!str[1].equals("")){
					sql = sql + " and " + str[0] + " ='" + str[1] + "'";
				}
			}
			sql = sql + " order by importnum,serialnum";
			jdbcutil.getConnection();
			ResultSet rs = jdbcutil.findMoreResult(sql,null);
			//LogisticslistingDao.updateLlist(Llist);
		}*/
	}
	
	public String updateLlistBySql(String param){
		try{
			sql = "update t_zy_logisticslisting set ";
			sql = sql + param;
			ps = jdbcutil.getConnection().prepareStatement(sql);
			ps.executeUpdate(sql);
			ps.close();
			jdbcutil.releaseConn();
		} catch (Exception e) {
			e.printStackTrace();
			return "999," + e.getMessage();
		}
		return "000";
	}
	public String findMaxImportnum(String userid){
		String maximportnum = "0";
		try{
			sql = "select max(importnum) from t_zy_logisticslisting where importuser = '"+userid+"'";
			ps = jdbcutil.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				maximportnum = rs.getString(1);
			}
			ps.close();
			jdbcutil.releaseConn();
		} catch (Exception e) {
			e.printStackTrace();
			return "9999";
		}
		return maximportnum;
	}
}
