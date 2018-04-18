package database;

import entity.Entity;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author AezA
 */
@ManagedBean(name = "dbq")
@SessionScoped
public class DatabaseQuaries {

    @ManagedProperty(value = "#{db}")
    private DatabaseConnection db;
    
    @ManagedProperty(value = "#{en}")
    private Entity en;
    
    public DatabaseConnection getDb() {
        return db;
    }

    public void setDb(DatabaseConnection db) {
        this.db = db;
    }

    public Entity getEn() {
        return en;
    }

    public void setEn(Entity en) {
        this.en = en;
    }
    
    public void insertColumnsClean() {
        
        HashMap<String, ArrayList> tableMap =new HashMap<>();
        HashMap<String, String[]> columnMap = new HashMap<>();
        HashMap<String, Integer> columnIndexMap = new HashMap<>();

        try {
            String table2 = en.getTable2(); 
            String getTableInfo = "SELECT * FROM " + en.getTable1();

            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTableInfo);
            ResultSetMetaData rsm = rs.getMetaData();
            for(int i=1; i<=rsm.getColumnCount(); i++) {
            
                columnIndexMap.put(rsm.getColumnName(i), i);
            }
            for(Entity en : en.getEntityList()) {
                
                String tempC1 = en.getColumn1();
                
                tableMap.put(tempC1, new ArrayList());
                String[] ar = {en.getColumn2(), rsm.getColumnTypeName(columnIndexMap.get(tempC1))};
                columnMap.put(tempC1, ar);
            }
           
            while (rs.next()) {

                if(en.idT1 > 0) {
                    en.idT1--;
                    continue;
                }
                for (Entity en : en.getEntityList()) {
                    String tempC1 = en.getColumn1();
                    tableMap.get(tempC1).add(rs.getObject(tempC1));
                }
            }
            
            String columns = "";
           
            ArrayList<String> columnList = new ArrayList<>();
            for(Entity en : en.getEntityList()) {
                String tempC1 = en.getColumn1();
                columns += columnMap.get(tempC1)[0] + ", ";
                columnList.add(tempC1); 
            }
            columns = columns.substring(0, columns.lastIndexOf(","));

            String insertQuary;
            
            for (int i = 0; i < tableMap.get(en.getEntityList().get(0).getColumn1()).size(); i++) {

                String values = "";

                for(String cl : columnList) {
 
                    switch (columnMap.get(cl)[1]) {

                        case "INTEGER":
                            values += (Integer) tableMap.get(cl).get(i) + ", ";
                            break;
                        case "VARCHAR":
                            values += "'" + (String) tableMap.get(cl).get(i) + "', ";
                            break;
                        case "NVARCHAR":
                            values += "N'" + (String) tableMap.get(cl).get(i) + "', ";
                            break;
                    }
                    
                }
                values = values.substring(0, values.lastIndexOf(","));
                
                insertQuary = "INSERT INTO " + table2 + " (" + columns + ") VALUES (" + values + ")";
                
                st.executeUpdate(insertQuary);
            }
    
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
    
    public void insertColumnsStair() {

        HashMap<String, ArrayList> tableMap = new HashMap<>();
        HashMap<String, String[]> columnMap = new HashMap<>();
        HashMap<String, Integer> columnIndexMap = new HashMap<>();
        
        try {

            String getTableInfo = "SELECT * FROM " + en.getTable1();

            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTableInfo);
            ResultSetMetaData rsm = rs.getMetaData();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {

                columnIndexMap.put(rsm.getColumnName(i), i);
            }
            for (Entity en : en.getEntityList()) {

                String tempC1 = en.getColumn1();

                tableMap.put(tempC1, new ArrayList());
                String[] ar = {en.getColumn2(), rsm.getColumnTypeName(columnIndexMap.get(tempC1))};
                columnMap.put(tempC1, ar);
            }

            while (rs.next()) {
                
                if (en.idT1 > 0) {
                    en.idT1--;
                    continue;
                }
                for (Entity en : en.getEntityList()) {
                    String tempC1 = en.getColumn1();
                    tableMap.get(tempC1).add(rs.getObject(tempC1));
                }
            }

            String table2 = en.getTable2();
            for (Entity en : en.getEntityList()) {
                String tempC1 = en.getColumn1();

                for (Object ob : tableMap.get(tempC1)) {

                    String insertQuary = null;
                    switch (columnMap.get(tempC1)[1]) {

                        case "INTEGER":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES (" + (Integer) ob + ")";
                            break;
                        case "VARCHAR":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES ('" + (String) ob + "')";
                            break;
                        case "NVARCHAR":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES (N'" + (String) ob + "')";
                            break;
                    }

                    st.executeUpdate(insertQuary);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertColumnsEachRow() {

        HashMap<String, ArrayList> tableMap = new HashMap<>();
        HashMap<String, String[]> columnMap = new HashMap<>();
        HashMap<String, Integer> columnIndexMap = new HashMap<>();

        try {

            String getTableInfo = "SELECT * FROM " + en.getTable1();

            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTableInfo);
            ResultSetMetaData rsm = rs.getMetaData();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {

                columnIndexMap.put(rsm.getColumnName(i), i);
            }
            for (Entity en : en.getEntityList()) {

                String tempC1 = en.getColumn1();

                tableMap.put(tempC1, new ArrayList());
                String[] ar = {en.getColumn2(), rsm.getColumnTypeName(columnIndexMap.get(tempC1))};
                columnMap.put(tempC1, ar);
            }

            while (rs.next()) {

                if (en.idT1 > 0) {
                    en.idT1--;
                    continue;
                }
                for (Entity en : en.getEntityList()) {
                    String tempC1 = en.getColumn1();
                    tableMap.get(tempC1).add(rs.getObject(tempC1));
                }
            }

            String table2 = en.getTable2();
            for (int i = 0; i < tableMap.get(en.getEntityList().get(0).getColumn1()).size(); i++) {
                
                for(int k = 0; k < en.getEntityList().size(); k++) {
                    
                    String tempC1 = en.getEntityList().get(k).getColumn1();
                    String insertQuary = null;
                    switch (columnMap.get(tempC1)[1]) {

                        case "INTEGER":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES (" + (Integer) tableMap.get(tempC1).get(i) + ")";
                            break;
                        case "VARCHAR":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES ('" + (String) tableMap.get(tempC1).get(i) + "')";
                            break;
                        case "NVARCHAR":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES (N'" + (String) tableMap.get(tempC1).get(i) + "')";
                            break;
                    }

                    st.executeUpdate(insertQuary);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertColumnsEachRowClean() {

        HashMap<String, ArrayList> tableMap = new HashMap<>();
        HashMap<String, String[]> columnMap = new HashMap<>();
        HashMap<String, Integer> columnIndexMap = new HashMap<>();

        try {

            String getTableInfo = "SELECT * FROM " + en.getTable1();

            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTableInfo);
            ResultSetMetaData rsm = rs.getMetaData();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {

                columnIndexMap.put(rsm.getColumnName(i), i);
            }
            for (Entity en : en.getEntityList()) {

                String tempC1 = en.getColumn1();

                tableMap.put(tempC1, new ArrayList());
                String[] ar = {en.getColumn2(), rsm.getColumnTypeName(columnIndexMap.get(tempC1))};
                columnMap.put(tempC1, ar);
            }

            while (rs.next()) {

                if (en.idT1 > 0) {
                    en.idT1--;
                    continue;
                }
                for (Entity en : en.getEntityList()) {
                    String tempC1 = en.getColumn1();
                    tableMap.get(tempC1).add(rs.getObject(tempC1));
                }
            }

            String table2 = en.getTable2();
            for (int i = 0; i < tableMap.get(en.getEntityList().get(0).getColumn1()).size(); i++) {
                
                for(int k = 0; k < en.getEntityList().size(); k++) {
                    
                    String tempC1 = en.getEntityList().get(k).getColumn1();
                    String insertQuary = null;
                    switch (columnMap.get(tempC1)[1]) {

                        case "INTEGER":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES (" + (Integer) tableMap.get(tempC1).get(i) + ")";
                            break;
                        case "VARCHAR":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES ('" + (String) tableMap.get(tempC1).get(i) + "')";
                            break;
                        case "NVARCHAR":
                            insertQuary = "INSERT INTO " + table2 + " (" + columnMap.get(tempC1)[0] + ") VALUES (N'" + (String) tableMap.get(tempC1).get(i) + "')";
                            break;
                    }

                    st.executeUpdate(insertQuary);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertColumnsFk() {
        
        HashMap<String, ArrayList> tableMap = new HashMap<>();
        HashMap<String, String[]> columnMap = new HashMap<>();
        HashMap<String, Integer> columnIndexMap = new HashMap<>();
        ArrayList<Long> fkList = new ArrayList<>();

        try {

            String getTableInfo = "SELECT * FROM " + en.getTable1();

            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTableInfo);
            ResultSetMetaData rsm = rs.getMetaData();
            //getting the index of columns
            for (int i = 1; i <= rsm.getColumnCount(); i++) {

                columnIndexMap.put(rsm.getColumnName(i), i);
            }
            //getting column type and putting it in map with the name of the column
            for (Entity en : en.getEntityList()) {

                String tempC1 = en.getColumn1();

                tableMap.put(tempC1, new ArrayList());
                String[] ar = {en.getColumn2(), rsm.getColumnTypeName(columnIndexMap.get(tempC1))};
                columnMap.put(tempC1, ar);
            }
            

            //getting each row records
            while (rs.next()) {

                if (en.idT1 > 0) {
                    en.idT1--;
                    continue;
                }
                for (Entity en : en.getEntityList()) {
                    String tempC1 = en.getColumn1();
                    tableMap.get(tempC1).add(rs.getObject(tempC1));
                }
                fkList.add((Long)rs.getObject(en.fkT1));
            }

            String table2 = en.getTable2();
            for (int i = 0; i < fkList.size(); i++) {

                for (int k = 0; k < en.getEntityList().size(); k++) {

                    String insertQuary = null;
                    String tempC1 = en.getEntityList().get(k).getColumn1();
//                    System.out.println(columnMap.get(tempC1)[1]);
                    switch (columnMap.get(tempC1)[1]) {

                        case "INTEGER":
                            insertQuary = "INSERT INTO " + table2 + " (" + en.fkT2 + ", " + columnMap.get(tempC1)[0] + ") VALUES (" + fkList.get(i) + ", " + tableMap.get(tempC1).get(i) + ")"; 
                            break;
                        case "VARCHAR":
                            insertQuary = "INSERT INTO " + table2 + " (" + en.fkT2 + ", " + columnMap.get(tempC1)[0] + ") VALUES (" + fkList.get(i) + ", '" + tableMap.get(tempC1).get(i) + "')";
                            break;
                        case "nvarchar":
                            insertQuary = "INSERT INTO " + table2 + " (" + en.fkT2 + ", " + columnMap.get(tempC1)[0] + ") VALUES (" + fkList.get(i) + ", N'" + tableMap.get(tempC1).get(i) + "')";
                            break;
                    }
//                    System.out.println(insertQuary);
                    st.executeUpdate(insertQuary);
                }  
            }         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void updateColumn() {

        HashMap<String, ArrayList> tableMap = new HashMap<>();
        HashMap<String, String[]> columnMap = new HashMap<>();
        HashMap<String, Integer> columnIndexMap = new HashMap<>();
        ArrayList<ArrayList<String>> arrX = new ArrayList<>();
        ArrayList<String> arrY = null;
        ArrayList<Integer> idList = new ArrayList<>();
        
        try {
            String table2 = en.getTable2();
            
            String getTableInfo = "SELECT * FROM " + en.getTable1();
            String getTable2IdInfo = "SELECT " + en.fkT1 + " FROM " + en.getTable2();

            Statement st = db.getConnection().createStatement();
            ResultSet rs2 = st.executeQuery(getTable2IdInfo);
            while (rs2.next()) {

                idList.add((Integer) rs2.getObject(en.fkT1));
            }
            rs2.close();
            ResultSet rs1 = st.executeQuery(getTableInfo);
            ResultSetMetaData rsm = rs1.getMetaData();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {

                columnIndexMap.put(rsm.getColumnName(i), i);
            }
            for (Entity en : en.getEntityList()) {

                String tempC1 = en.getColumn1();

                tableMap.put(tempC1, new ArrayList());
                String[] ar = {en.getColumn2(), rsm.getColumnTypeName(columnIndexMap.get(tempC1))};
                columnMap.put(tempC1, ar);
            }

            while (rs1.next()) {

                if (en.idT1 > 0) {
                    en.idT1--;
                    continue;
                }
                for (Entity en : en.getEntityList()) {
                    String tempC1 = en.getColumn1();
                    tableMap.get(tempC1).add(rs1.getObject(tempC1));
                }
            }
                    

            ArrayList<String> columnList = new ArrayList<>();
            for (Entity en : en.getEntityList()) {
                String tempC1 = en.getColumn1();
                columnList.add(columnMap.get(tempC1)[0] + " = ");
            }

            for (int i = 0; i < en.getEntityList().size(); i++) {
                arrY = new ArrayList<>();
                for (int k = 0; k < tableMap.get(en.getEntityList().get(0).getColumn1()).size(); k++) {
                    
                    String tempC1 = en.getEntityList().get(i).getColumn1();
                    String values = "";
                    switch (columnMap.get(tempC1)[1]) {

                        case "INTEGER":
                            values = columnList.get(i) + tableMap.get(tempC1).get(k) + ", ";
                            break;
                        case "VARCHAR":
                            values = columnList.get(i) + "'" + tableMap.get(tempC1).get(k) + "', ";
                            break;
                        case "NVARCHAR":
                            values = columnList.get(i) + "N'" + tableMap.get(tempC1).get(k) + "', ";
                            break;
                    }
                    
                    arrY.add(values);
                }
                arrX.add(arrY);

            }
            
            for(int i = 0; i < arrY.size(); i++) {
                String temp1 = "";
                for(int k = 0; k < arrX.size(); k++) {
                    
                    temp1 += arrX.get(k).get(i);
                    
                }
                temp1 = temp1.substring(0, temp1.lastIndexOf(","));
                String finalQuary = "UPDATE " + table2 + " SET " + temp1 + " WHERE " + en.fkT1 + " = " + idList.get(i);
                st.executeUpdate(finalQuary);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}