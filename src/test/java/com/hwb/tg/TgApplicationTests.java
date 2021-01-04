package com.hwb.tg;

import com.alibaba.fastjson.JSON;
import com.hwb.tg.Bean.Financial;
import com.hwb.tg.Dao.AccountDao;
import com.hwb.tg.Dao.FinancialDao;
import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Service.AccountService;
import com.hwb.tg.Service.FinancialService;
import com.hwb.tg.Utils.ImportExcel;
import com.hwb.tg.pojo.*;
import org.apache.commons.math3.analysis.function.Add;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TgApplicationTests {

    @Autowired
    FinancialDao financialDao;

    @Test
    void contextLoads() {
        System.out.println(financialDao.getFinancialByTeacherId(1, 0, 10));
    }

    @Test
    void sout() {
        ImportExcel poi = new ImportExcel();
        List<List<String>> list = poi.read("D:\\temp.xlsx", 0, 0);
        List<FinancialUpload> financials = new ArrayList<>();
        if (list != null) {

            //获取第一行的标题
            List<String> category = list.get(1);

            // 遍历行
            for (int i = 2; i < list.size(); i++) {
                List<String> cellList = list.get(i);
                FinancialUpload financial = null;

                /* 获取基本参数 */
                int year = Integer.parseInt(cellList.get(0));
                int month = Integer.parseInt(cellList.get(1));
                String jobNumber = cellList.get(2);

                /* 判断是否已经有这个教师 */
                int index = contains(financials, jobNumber, year, month);
                if (index == -1) {
                    financial = new FinancialUpload();
                    financial.setJobNumber(jobNumber);
                    financial.setMonth(month);
                    financial.setYear(year);

                    ArrayList<EveryMonthFinancialDetail> details = new ArrayList<>();
                    for (int j = 3; j < cellList.size(); j++) {

                        /* 如果金额为零的话则跳过 */
                        double money = Double.parseDouble(cellList.get(j));
                        if (money <= 0) {
                            continue;
                        }

                        details.add(new EveryMonthFinancialDetail(category.get(j), money));
                    }
                    financial.setDetailList(details);
                    financials.add(financial);
                } else {
                    /* 如果已经有这个教师 则继续添加 */
                    financial = financials.get(index);

                    List<EveryMonthFinancialDetail> details = financial.getDetailList();
                    for (int j = 3; j < cellList.size(); j++) {
                        details.add(new EveryMonthFinancialDetail(category.get(j), Double.parseDouble(cellList.get(j))));
                    }
                    financial.setDetailList(details);
                }
            }
        }

        System.out.println(financials);
    }

    public Integer contains(List<FinancialUpload> financialUploads,
                            String jobNumber,
                            Integer year,
                            Integer month) {
        Integer index = -1;
        for (int i = 0; i < financialUploads.size(); i++) {
            if (financialUploads.get(i).getJobNumber().equals(jobNumber) && financialUploads.get(i).getMonth() == month && financialUploads.get(i).getYear() == year) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Autowired
    AccountService accountServiceImpl;
    @Autowired
    FinancialService financialServiceImpl;
    @Autowired
    AccountDao accountDao;

    @Test
    public void financial() throws ParseException, SQLIntegrityConstraintViolationException {
        Map<String, Integer> lastMonth = financialDao.getLastMonth();
//        System.out.println(JSON.toJSONString(financialServiceImpl.showAllFinancial(lastMonth.get("year"), lastMonth.get("month"))));
    }

    @Test
    public void addAdmin() {
        AddAdminAccount addAdminAccount = new AddAdminAccount();
        addAdminAccount.setUserName("子管理测试");
        addAdminAccount.setPassword("test");
        addAdminAccount.setDepartment("test");
        accountDao.addAdmin(addAdminAccount);
    }

    @Test
    public void deleteFinancial() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(51);
        integers.add(52);
        financialServiceImpl.deleteFinancial(integers);
    }

    @Test
    public void getTeacher() {
        TeacherInfoAdmin aTeacherInfo = accountServiceImpl.getATeacherInfo(26);
        aTeacherInfo.setName("修改测试1234");
        accountServiceImpl.updateTeacher(aTeacherInfo);
    }

    @Test
    public void delete() {
        System.out.println(JSON.toJSONString(financialServiceImpl.searchTeacherFinancial(null, null, null, 10, 1)));

    }

    @Test
    public void test(){
        System.out.println(JSON.toJSONString(accountServiceImpl.searchTeacher("123",1,1000)));
    }

}
