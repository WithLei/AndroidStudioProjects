package com.jxepub.paperlessconference.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jxepub.paperlessconference.entity.UserInfo;
import com.jxepub.paperlessconference.entity.WenJianInfo;

public class DBUtils {
	private static final String URL = "jdbc:jtds:sqlserver://192.168.60.69:1433/GuFenHuiYi_2018;charset=utf8";
	private static final String USER = "sa";
	private static final String PWD = "qidanet.sql";

	public static Connection getSQLConnection() {
		Connection conn = null;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 获取会议基本信息
	 *
	 * @param sql
	 * @return List<HuiYiInfo>
	 */
//	public static List<HuiYiInfo> GetHuiYiInfo(String sql) {
//		List<HuiYiInfo> huiYiInfos = new ArrayList<HuiYiInfo>();
//		try {
//			Connection conn = getSQLConnection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				String huiYiNum = rs.getString("HuiYiNum");
//				String huiYiName = rs.getString("HuiYiName");
//				String huiYiRenYuan = rs.getString("HuiYiRenYuan");
//				String huiYiNeiRong = rs.getString("HuiYiNeiRong");
//				String huiYiTimeK = rs.getString("HuiYiTimeK");
//				String huiYiTimeJ = rs.getString("HuiYiTimeJ");
//				String huiYiShiNum = rs.getString("HuiYiShiNum");
//				String faQiRen = rs.getString("FaQiRen");
//				String shiChang = rs.getString("ShiChang");
//				String addAdmin = rs.getString("AddAdmin");
//				String beiZhu = rs.getString("BeiZhu");
//				String zT = rs.getString("ZT");
//				String addTime = rs.getString("AddTime");
//				String huiYiShiXiao = rs.getString("HuiYiShiXiao");
//				String huiYiRenShu = rs.getString("HuiYiRenShu");
//				HuiYiInfo huiYiInfo = new HuiYiInfo(huiYiNum, huiYiName, huiYiRenYuan, huiYiNeiRong, huiYiTimeK,
//						huiYiTimeJ, huiYiShiNum, faQiRen, shiChang, addAdmin, beiZhu, zT, addTime, huiYiShiXiao,
//						huiYiRenShu);
//				huiYiInfos.add(huiYiInfo);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return huiYiInfos;
//	}

	/**
	 * 获取会议室信息
	 *
	 * @param sql
	 * @return List<HuiYiShiInfo>
	 */
	// public static List<HuiYiShiInfo> getHuiYiShiInfo(String sql) {
	// List<HuiYiShiInfo> huiYiShiInfos = new ArrayList<HuiYiShiInfo>();
	// Connection conn = null;
	// Statement stmt = null;
	// ResultSet rs = null;
	// try {
	// conn = getSQLConnection();
	// stmt = conn.createStatement();
	// rs = stmt.executeQuery(sql);
	// while (rs.next()) {
	// String huiYiShiNum = rs.getString("HuiYiShiNum");
	// String huiYiShiName = rs.getString("HuiYiShiName");
	// String huiYiShiZT = rs.getString("HuiYiShiZT");
	// String huiYiShiShuoMing = rs.getString("HuiYiShiShuoMing");
	// String huiYiShiFZR = rs.getString("HuiYiShiFZR");
	// String addAdmin = rs.getString("AddAdmin");
	// String xH = rs.getString("XH");
	// String addTime = rs.getString("AddTime");
	// String rongNaRenShu = rs.getString("RongNaRenShu");
	// HuiYiShiInfo huiYiShiInfo = new HuiYiShiInfo(huiYiShiNum, huiYiShiName,
	// huiYiShiZT, huiYiShiShuoMing,
	// huiYiShiFZR, addAdmin, xH, addTime, rongNaRenShu);
	// huiYiShiInfos.add(huiYiShiInfo);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (rs != null) {
	// rs.close();
	// }
	// if (stmt != null) {
	// stmt.close();
	// }
	// if (conn != null) {
	// conn.close();
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// return huiYiShiInfos;
	// }

	/**
	 * 获取会议文件信息
	 *
	 * @param sql
	 * @return List<WenJianInfo>
	 */
	public static List<WenJianInfo> getWenJianInfo(String sql) {
		List<WenJianInfo> wenJianInfos = new ArrayList<WenJianInfo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getSQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String wenJianNum = rs.getString("WenJianNum");
				String wenJianName = rs.getString("WenJianName");
				String url = rs.getString("Url");
				String huiYiNum = rs.getString("HuiYiNum");
				String wenJianDaXiao = rs.getString("WenJianDaXiao");
				WenJianInfo wenJianInfo = new WenJianInfo(wenJianNum, wenJianName, url, huiYiNum, wenJianDaXiao);
				wenJianInfos.add(wenJianInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return wenJianInfos;
	}

	/**
	 * 获取登录信息
	 */
	public static UserInfo getLoginInfo(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getSQLConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String adminID = rs.getString("AdminID");
				String adminName = rs.getString("AdminName");
				String adminPass = rs.getString("AdminPass");
				String zhiWei = rs.getString("ZhiWei");
				UserInfo userInfo = new UserInfo(adminID, adminName, adminPass, zhiWei);
				return userInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取与会人员名单
	 */
	public static String getPersonStr(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = getSQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result += rs.getString("AdminName") + "|";
			}
			result = result.substring(0, result.lastIndexOf("|"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取会议地点
	 */
	public static String getDiDian(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = getSQLConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getString("HuiYiShiName");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
