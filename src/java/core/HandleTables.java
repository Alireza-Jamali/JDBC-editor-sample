package core;

import database.DatabaseQuaries;
import entity.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AezA
 */
@ManagedBean(name = "handle")
@SessionScoped
public class HandleTables {
    
    @ManagedProperty(value = "#{en}")
    private Entity entity;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    @ManagedProperty(value = "#{dbq}")
    private DatabaseQuaries dbq;

    public DatabaseQuaries getDbq() {
        return dbq;
    }

    public void setDbq(DatabaseQuaries dbq) {
        this.dbq = dbq;
    }
    
    public void startClean() {
        
        dbq.insertColumnsClean();
    }
    
    public void startStair() {
        
        dbq.insertColumnsStair();
    }
    
    public void startEach() {
        
        dbq.insertColumnsEachRow();
    }
    
    public void startFk() {
        
        dbq.insertColumnsFk();
    }
    
    public void startUp() {
        
        dbq.updateColumn();
    }
    
    public void reset() {
    
        entity.setEntityList(new ArrayList());
        entity.setColumn1("");
        entity.setColumn2("");
        entity.setTable1("");
        entity.setTable2("");
    }
}