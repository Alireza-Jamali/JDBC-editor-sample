package entity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AezA
 */
@ManagedBean(name = "en")
@SessionScoped
public class Entity {

    private String column1;
    private String column2;
    public int idT1;
    public String fkT1;
    public String fkT2;

    public String getFkT1() {
        return fkT1;
    }

    public void setFkT1(String fkT1) {
        this.fkT1 = fkT1;
    }

    public String getFkT2() {
        return fkT2;
    }

    public void setFkT2(String fkT2) {
        this.fkT2 = fkT2;
    }

    public int getIdT1() {
        return idT1;
    }

    public void setIdT1(int idT1) {
        this.idT1 = idT1;
    }
    
    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    private List<Entity> entityList;

    private String table1;
    private String table2;

    public String getTable1() {
        return table1;
    }

    public void setTable1(String table1) {
        this.table1 = table1;
    }

    public String getTable2() {
        return table2;
    }

    public void setTable2(String table2) {
        this.table2 = table2;
    }
    
    @PostConstruct
    public void init() {
        entityList = new ArrayList();
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }
    
    public void add() {
        entityList.add(new Entity());
    }
}