package com.hwb.tg.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwb.tg.Dao.AccountDao;
import com.hwb.tg.Service.AccountService;
import com.hwb.tg.Utils.ImportExcel;
import com.hwb.tg.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/22 23:53
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    /**
     * 解析批量添加账号xls模板
     *
     * @param path
     * @return
     */
    @Override
    public List<AddTeacher> uploadTeacherAccountFile(String path) {
        ImportExcel poi = new ImportExcel();
        List<List<String>> list = poi.read(path, 0, 0);
        List<AddTeacher> teacherList = new ArrayList<>();
        if (list != null) {
            // 遍历行
            for (int i = 2; i < list.size(); i++) {
                List<String> cellList = list.get(i);
                AddTeacher addTeacher = new AddTeacher();

                addTeacher.setTeacherName(cellList.get(0));
                addTeacher.setJobNumber(cellList.get(1));
                addTeacher.setPassword(cellList.get(2));
                addTeacher.setPosition(cellList.get(3));
                addTeacher.setLongTel(cellList.get(5));
                addTeacher.setShortTel(cellList.get(6));
                addTeacher.setEmail(cellList.get(7));

                //处理日期
                String empDate = cellList.get(4);
                String format = null;
                if (empDate != null && empDate.contains(".")) {
                    format = "yyyy.MM.dd";
                } else if (empDate != null && empDate.contains("/")) {
                    format = "yyyy/MM/dd";
                } else {
                    empDate = "";
                    format = "yyyy/MM/dd";
                }
                addTeacher.setEmpTime(empDate);

                teacherList.add(addTeacher);
            }
        }

        return teacherList;
    }

    /**
     * 批量添加教师
     *
     * @param teachers
     */
    @Override
    @Transactional
    public void batchAddTeacher(List<AddTeacher> teachers) throws SQLIntegrityConstraintViolationException, DuplicateKeyException {
        for (AddTeacher teacher :
                teachers) {
            if (teacher.getEmpTime() == null) {
                teacher.setEmpTime("1977/01/01");
            }
            if (teacher.getEmpTime().contains(".")) {
                teacher.setEmpTime(teacher.getEmpTime().replace(".", "/"));
            } else if (teacher.getEmpTime().contains("/")) {

            } else {
                teacher.setEmpTime("1977/01/01");
            }
            accountDao.batchAddTeacher(teacher);

        }
    }

    /**
     * 添加管理员账号
     *
     * @param adminAccount
     */
    @Override
    public Boolean addAdmin(AddAdminAccount adminAccount) {
        if (checkUserName(adminAccount.getUserName())){
            accountDao.addAdmin(adminAccount);
            return true;
        }else
            return false;
    }

    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    @Override
    public Boolean checkUserName(String userName) {
        return accountDao.checkUserName(userName)==null;
    }

    /**
     * 获取教师信息
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public PageInfo<TeacherInfoAdmin> getTeacherInfo(Integer pageSize, Integer pageNumber) {
        PageHelper.startPage(pageNumber,pageSize);
        List<TeacherInfoAdmin> infoAdmins = accountDao.getTeacher();
        PageInfo<TeacherInfoAdmin> pageInfo = new PageInfo<>(infoAdmins);
        return pageInfo;
    }

    /**
     * 冻结教师
     *
     * @param teacherId
     */
    @Override
    public void freezeTeacher(Integer teacherId) {
        accountDao.freezeTeacher(teacherId);
    }

    /**
     * 解冻教师
     *
     * @param teacherId
     */
    @Override
    public void unFreezeTeacher(Integer teacherId) {
        accountDao.unFreezeTeacher(teacherId);
    }

    /**
     * 获取单个教师全部信息
     *
     * @param teacherId
     * @return
     */
    @Override
    public TeacherInfoAdmin getATeacherInfo(Integer teacherId) {
        return accountDao.getATeacherInfo(teacherId);
    }

    /**
     * 更新教师信息
     *
     * @param infoAdmin
     */
    @Override
    public void updateTeacher(TeacherInfoAdmin infoAdmin) {
        infoAdmin.setEmploymentDate(
                infoAdmin.getEmploymentDate().split(" ")[0].replace("/","-")
        );
        accountDao.updateTeacher(infoAdmin);
    }

}
