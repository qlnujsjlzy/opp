package edu.exam.online.professional.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.StringTokenizer;




/**   
*    
* 项目名称：DQTZ   
* 类名称：Db2EntityMysql   
* @Description: TODO(数据库表生成实体类)   
* 创建人：lizhaoyang 
* 创建时间：2016年1月5日 下午5:47:35   
* 修改人：lizhaoyang  
* 修改时间：2016年1月5日 下午5:47:35   
* 修改备注： <p>修改历史：对程序的修改历史进行记录</p>  
* @version 1.0
*    
*/
public class Db2EntityMysql {
	/**
	* 使用方式：
	* packageOutPath 指定实体生成所在包的路径 修改
	* tablenames     指定需要生成实体的数据库表名
	* authorName     作者名字
	*/
	private String packageOutPath = "edu.exam.online.professional.domain";// 指定实体生成所在包的路径
	private static String[] tablenames = {"totalscores"};// 表名
	private String authorName = "guodandan";// 作者名字

	private static String tablename = "";// 表名
	private String[] colnames; // 列名数组
	private String[] colcomments; // 列名注释数组
	private String[] colTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	// 数据库连接
	private static final String URL = "jdbc:mysql://localhost:3306/opp";
	private static final String NAME = "root";
	private static final String PASS = "root";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/*
	 * 构造函数
	 */
	public Db2EntityMysql() {
		// 创建连接
		Connection con;
		// 查要生成实体类的表
		String sql = "select * from " + tablename;
		
		PreparedStatement pStemt = null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			con = DriverManager.getConnection(URL, NAME, PASS);
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			int size = rsmd.getColumnCount(); // 统计列
			colnames = new String[size];
			colTypes = new String[size];
			colcomments = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);

				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image")
						|| colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}
			//字段注释
			DatabaseMetaData dms = con.getMetaData();
			ResultSet set = dms.getTables(null, null, null,new String[] { "TABLE" });
			while (set.next()) {
				// 查要生成实体类的表
				ResultSet columns = dms.getColumns(null, null,set.getString(3), null);
				int i = 0;
				while (columns.next()) {
					String tname = columns.getString(3);
					if (tablename.equals(tname)) {
						colcomments[i] = columns.getString(12);
						i++;
					}
				}
			}
			String content = parse(colnames, colTypes, colSizes);
			try {
				File directory = new File("");
				String path = this.getClass().getResource("").getPath();

				String outputPath = directory.getAbsolutePath() + "/src/main/java/"
						+ this.packageOutPath.replace(".", "/") + "/"
						+ firstLetterCaps(tablename) + ".java";
				FileWriter fw = new FileWriter(outputPath);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + this.packageOutPath + ";\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		sb.append("import java.io.Serializable;\r\n");
		sb.append("\r\n");
		// 注释部分
		sb.append("   /**\r\n");
		sb.append("    * " + tablename + " 实体类\r\n");
		sb.append("    * authorName " +  this.authorName + "\r\n");
		sb.append("    */ \r\n");
		// 实体部分
		sb.append("public class "
				+ firstLetterCaps(tablename)
				+ " implements Serializable {\r\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
		processAllAttrs(sb);// 属性
		processAllMethod(sb);// get set方法
		processtoString(sb);
		sb.append("}\r\n");
		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			sb.append("   /**\r\n");
			sb.append("    * " + colcomments[i] + "\r\n");
			sb.append("    */ \r\n");
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
					+ dbNameToJavaName(colnames[i]) + ";\r\n");
		}

	}
	

	
	
	/**
	* @Title: processtoString
	* @Description: TODO(生成toString)
	* @param sb  void  
	* 创建人：lizhaoyang 
	* 创建时间：2016年1月5日 下午6:48:04   
	* @exception 异常类名 说明
	*/
	private void processtoString(StringBuffer sb) {
		sb.append("\tpublic String toString() {\r\n");
		sb.append("\t\treturn \" " + dbNameToJavaName(tablename) + " [");
		sb.append(dbNameToJavaName(colnames[0])+"=\"+"+dbNameToJavaName(colnames[0])+"+ ");
		for (int i = 1; i < colnames.length; i++) {
			sb.append(" \", "+dbNameToJavaName(colnames[i])+"=\"+"+dbNameToJavaName(colnames[i])+"+");			
		}
		sb.append("\"]\"; \r\n");
		sb.append("\t}\r\n");
	}
	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + dbNameToJavaNameSetGet(colnames[i])
					+ "(" + sqlType2JavaType(colTypes[i]) + " "
					+ dbNameToJavaName(colnames[i]) + "){\r\n");
			sb.append("\t\tthis." + dbNameToJavaName(colnames[i]) + "="
					+ dbNameToJavaName(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
					+ dbNameToJavaNameSetGet(colnames[i]) + "(){\r\n");
			sb.append("\t\treturn " + dbNameToJavaName(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
		}

	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int unsigned")
				  ||sqlType.equalsIgnoreCase("int signed")
				  ||sqlType.equalsIgnoreCase("int")) {
			return "int";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}

		return null;
	}

	/**
	 * @Title: dbNameToJavaName
	 * @Description: TODO(数据库表名转换成实体类）
	 * @param schemaName
	 * @return String 创建人：lizhaoyang 创建时间：
	 * 2016年1月5日 下午2:24:45
	 * @exception 异常类名
	 *                说明
	 */
	public static String dbNameToJavaName(String schemaName) {
		StringBuffer name = new StringBuffer();
		String namePart;
		int i = 0;
		for (StringTokenizer tok = new StringTokenizer(schemaName,
				String.valueOf('_')); tok.hasMoreTokens(); i++) {
			namePart = ((String) tok.nextElement()).toLowerCase();
			if (i != 0){
				namePart = firstLetterCaps(namePart);
			}
			name.append(namePart);
		}

		return name.toString();
	}

	public static String dbNameToJavaNameSetGet(String schemaName) {
		StringBuffer name = new StringBuffer();
		String namePart;
		int i = 0;
		for (StringTokenizer tok = new StringTokenizer(schemaName,
				String.valueOf('_')); tok.hasMoreTokens(); i++) {
			namePart = ((String) tok.nextElement()).toLowerCase();
			namePart = firstLetterCaps(namePart);
			name.append(namePart);
		}

		return name.toString();
	}

	public static String firstLetterCaps(String data) {
		String firstLetter = data.substring(0, 1).toUpperCase();
		String restLetters = data.substring(1).toLowerCase();
		return firstLetter + restLetters;
	}

	/**
	 * 出口 TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        for(String tb:tablenames){
        	tablename = tb;// 表名
        	new Db2EntityMysql();
        	System.out.println("数据库表"+tablename+"成功转换成实体");
        }
	}

}