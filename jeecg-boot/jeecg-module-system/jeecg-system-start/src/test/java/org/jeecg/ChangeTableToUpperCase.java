package org.jeecg;

import lombok.var;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ChangeTableToUpperCase {
    {
    var sql = "INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) \n" +
            "                       VALUES ('a5a9733d2dec405dba9aacaa818b1548', NULL, '测试建表', '/online/cgformList/5f9ef74ffe114c1d9013604d908c63a8', '1', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 0, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', null, NULL, NULL, 0)";
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        var url =  "jdbc:mysql://127.0.0.1:3306/jeecg-boot?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
        var uname = "root";
        var pwd = "123456";
        var driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        var conn = DriverManager.getConnection(url,uname,pwd);
        var sm = conn.createStatement();
        sm.execute("select t.table_name \n" +
                "\t\tfrom information_schema.tables as t \n" +
                "\t\twhere t.table_schema = 'jeecg-boot' \n" +
                "\t\t\tand t.table_name like 'qrtz_%'");
        var res = sm.getResultSet();
        conn.setAutoCommit(false);
        try{
            while(res.next()){
                var tableName = res.getString(1);
                var updater = conn.createStatement();
                updater.execute(String.format("rename table %s to %s",tableName,tableName.toUpperCase()));
                updater.close();
            }
            conn.commit();
        }catch (Throwable t){
            conn.rollback();
        }
        sm.close();
        conn.close();
    }
}
