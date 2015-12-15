package love.drose.hrms.models;

/**
 * Created by lovedrose on 12/15/15.
 */
public class ManagerRole {
    /**
     * 管理员id
     */
    private Integer managerId;
    /**
     * 角色id
     */
    private Integer roleId;

    public ManagerRole(Integer managerId, Integer roleId) {
        this.managerId = managerId;
        this.roleId = roleId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ManagerRole{" +
                "managerId=" + managerId +
                ", roleId=" + roleId +
                '}';
    }

    public ManagerRole() {
    }
}
