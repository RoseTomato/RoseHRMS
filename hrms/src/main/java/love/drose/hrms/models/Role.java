package love.drose.hrms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 角色模型
 * Created by lovedrose on 12/15/15.
 */
public class Role {
    @Id
    @Column(name = "Id")
    private Integer id;
    /**
     * 角色名
     */
    private String name;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
