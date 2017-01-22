package com.xie.tool;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

public class DB2JavaBean {

    private String dir = "src/main/java/com/xie/bean/";
    private String pack = "com.xie.bean";
    private String userName = "homestead"; //数据库用户名
    private String password = "secret"; //数据库密码
    private String tableName = "users;goods;order;address;banner;bonus;cart"; //要生成jopo对象的表名,使用;进行分割
    private String tableMatchPattern = "T_%"; //数据库表名匹配模式
    private String matchPattern = "%"; //是否启用数据库表名匹配模式功能,启用后tableName属性不被使用


    public DB2JavaBean() {
    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        DB2JavaBean d2j = new DB2JavaBean();
//        d2j.init(StaticVar.OBJECTTYPE);
//        //d2j.init(StaticVar.COMMONLYTYPE);
//        System.out.println("ok");
//    }


    public void init(int ObjectTypeOrCommonlyType) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf8";
            Connection conn = DriverManager.getConnection(url, this.userName, this.password);
            String[] tables = null;
            ArrayList tableal = new ArrayList(20);
            if ("true".equals(this.matchPattern)) {
                DatabaseMetaData dbmd = conn.getMetaData();
                ResultSet dbmdrs = dbmd.getTables(null, this.userName.toUpperCase(), this.tableMatchPattern, new String[]{"TABLE"});
                while (dbmdrs.next()) {
                    tableal.add(dbmdrs.getString(3));
                }
                dbmdrs.close();
                if (tableal.size() == 0) {
                    dbmdrs = dbmd.getTables(null, this.userName.toLowerCase(), this.tableMatchPattern, new String[]{"TABLE"});
                    while (dbmdrs.next()) {
                        tableal.add(dbmdrs.getString(3));
                    }
                    dbmdrs.close();
                }
                if (tableal.size() == 0) {
                    dbmdrs = dbmd.getTables(null, this.userName, this.tableMatchPattern, new String[]{"TABLE"});
                    while (dbmdrs.next()) {
                        tableal.add(dbmdrs.getString(3));
                    }
                    dbmdrs.close();
                }
                tables = new String[tableal.size()];
                for (int ti = 0; ti < tableal.size(); ti++) {
                    tables[ti] = tableal.get(ti).toString();
                }
            } else {
                tables = this.tableName.split(";");
            }
            String strType;
            String strName;
            String className;
            String[] nameSect = {};
            StringBuilder tbn = new StringBuilder();
            StringBuilder tstr1 = new StringBuilder();
            StringBuilder tstr2 = new StringBuilder();
            File file = new File(dir);
            if (!file.exists() || !file.isDirectory()) {
                file.mkdir();
            }
            for (int i = 0; i < tables.length; i++) {
                nameSect = tables[i].split("_");
                for (String ns : nameSect) {
                    tbn.append(ns.substring(0, 1).toUpperCase() + ns.substring(1).toLowerCase());
                }
                className = tbn.toString();
                tbn.delete(0, tbn.length());
                tstr1.append("package " + pack+";");
                tstr1.append("\n");
                tstr1.append("import java.sql.*; ");
                tstr1.append("\n");
                tstr1.append("import javax.sql.*; ");
                tstr1.append("\n");
                tstr1.append("import java.io.*; ");
                tstr1.append("\n");
                tstr1.append("public class " + className + " implements Serializable{ ");
                tstr1.append("\n");
                try {
                    System.out.println(tables[i]);
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery("select * from " + tables[i]);
                    ResultSetMetaData rsd = rs.getMetaData();
                    int cc = rsd.getColumnCount();
                    for (int j = 1; j <= cc; j++) {
                        if (ObjectTypeOrCommonlyType == StaticVar.OBJECTTYPE) {
                            strType = this.getObjectType(rsd.getColumnType(j));
                        } else {
                            strType = this.getCommonlyType(j);
                        }
                        if (strType == null) continue;
                        strName = rsd.getColumnName(j);
                        tstr1.append(" private " + strType + " " + strName.toLowerCase() + ";");
                        tstr1.append("\n");
                        tstr2.append(" public void set" + strName.substring(0, 1).toUpperCase() + strName.substring(1).toLowerCase() + "(" + strType + " " + strName.toLowerCase() + "){");
                        tstr2.append("\n");
                        tstr2.append("    this." + strName.toLowerCase() + " = " + strName.toLowerCase() + ";");
                        tstr2.append("\n");
                        tstr2.append(" }");
                        tstr2.append("\n");
                        tstr2.append(" public " + strType + " get" + strName.substring(0, 1).toUpperCase() + strName.substring(1).toLowerCase() + "(){");
                        tstr2.append("\n");
                        tstr2.append("    return this." + strName.toLowerCase() + ";");
                        tstr2.append("\n");
                        tstr2.append(" }");
                        tstr2.append("\n");
                    }
                    rs.close();
                    statement.close();

                } catch (Exception tableE) {
                    tableE.printStackTrace();
                }
                tstr2.append("} ");
                tstr2.append("\n");
                tstr1.append(tstr2.toString());
                tstr1.append("\n");
                file = new File(dir + className + ".java");
                FileWriter fw = new FileWriter(file);
                fw.write(tstr1.toString());
                fw.flush();
                fw.close();
                tstr1.delete(0, tstr1.length());
                tstr2.delete(0, tstr2.length());
            }
            conn.close();
        } catch (Exception driverE) {
            driverE.printStackTrace();
        }
    }


    public String getObjectType(int type) {
        switch (type) {
            case Types.ARRAY:
                return null;
            case Types.BIGINT:
                return "Long";
            case Types.BINARY:
                return null;
            case Types.BIT:
                return "Byte";
            case Types.BLOB:
                return "Blob";
            case Types.BOOLEAN:
                return "Boolean";
            case Types.CHAR:
                return "String";
            case Types.CLOB:
                return "Clob";
            case Types.DATALINK:
                return null;
            case Types.DATE:
                return "Date";
            case Types.DECIMAL:
                return "Double";
            case Types.DISTINCT:
                return null;
            case Types.DOUBLE:
                return "Double";
            case Types.FLOAT:
                return "Float";
            case Types.INTEGER:
                return "Integer";
            case Types.NUMERIC:
                return "Integer";
            case Types.JAVA_OBJECT:
                return null;
            case Types.LONGVARBINARY:
                return null;
            case Types.LONGVARCHAR:
                return null;
            case Types.NULL:
                return null;
            case Types.OTHER:
                return null;
            case Types.REAL:
                return null;
            case Types.REF:
                return null;
            case Types.SMALLINT:
                return "Short";
            case Types.STRUCT:
                return null;
            case Types.TIME:
                return "Time";
            case Types.TIMESTAMP:
                return "Timestamp";
            case Types.TINYINT:
                return "Short";
            case Types.VARBINARY:
                return null;
            case Types.VARCHAR:
                return "String";
            default:
                return null;
        }
    }

    public String getCommonlyType(int type) {
        switch (type) {
            case Types.ARRAY:
                return null;
            case Types.BIGINT:
                return "long";
            case Types.BINARY:
                return null;
            case Types.BIT:
                return "byte";
            case Types.BLOB:
                return "String";
            case Types.BOOLEAN:
                return "boolean";
            case Types.CHAR:
                return "String";
            case Types.CLOB:
                return "String";
            case Types.DATALINK:
                return null;
            case Types.DATE:
                return "Date";
            case Types.DECIMAL:
                return "double";
            case Types.DISTINCT:
                return null;
            case Types.DOUBLE:
                return "double";
            case Types.FLOAT:
                return "float";
            case Types.INTEGER:
                return "int";
            case Types.NUMERIC:
                return "int";
            case Types.JAVA_OBJECT:
                return null;
            case Types.LONGVARBINARY:
                return null;
            case Types.LONGVARCHAR:
                return null;
            case Types.NULL:
                return null;
            case Types.OTHER:
                return null;
            case Types.REAL:
                return null;
            case Types.REF:
                return null;
            case Types.SMALLINT:
                return "short";
            case Types.STRUCT:
                return null;
            case Types.TIME:
                return "Time";
            case Types.TIMESTAMP:
                return "Timestamp";
            case Types.TINYINT:
                return "short";
            case Types.VARBINARY:
                return null;
            case Types.VARCHAR:
                return "String";
            default:
                return null;
        }
    }
}