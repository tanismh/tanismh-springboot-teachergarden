package com.hwb.tg.Service;

import com.hwb.tg.pojo.AddAdminAccount;
import com.hwb.tg.pojo.AddTeacher;
import org.apache.commons.math3.analysis.function.Add;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/22 23:51
 */

public interface AccountService {
    /**
     * 解析批量添加账号xls模板
     *
     * @param path
     * @return
     */
    public List<AddTeacher> uploadTeacherAccountFile(String path);

    /**
     * 批量添加教师
     *
     * @param teachers
     */
    public void batchAddTeacher(List<AddTeacher> teachers) throws SQLIntegrityConstraintViolationException;

    /**
     * 添加管理员账号
     *
     * @param adminAccount
     */
    public Boolean addAdmin(AddAdminAccount adminAccount);

    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    public Boolean checkUserName(String userName);
}
