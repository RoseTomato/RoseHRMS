package love.drose.hrms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 管理员模型
 * Created by lovedrose on 12/15/15.
 */
public class Manager {

    @Id
    @Column(name = "Id")
    private Integer id;
    /**
     * 管理员账号
     */
    private String account;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 管理员名称
     */
    private String name;

    public Manager() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Manager(Integer id, String account, String password, String name) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
    }
}
