package com.hwb.tg;

import com.hwb.tg.Bean.Financial;
import com.hwb.tg.Utils.ImportExcel;
import com.hwb.tg.pojo.EveryMonthFinancialDetail;
import com.hwb.tg.pojo.FinancialUpload;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TgApplicationTests {

    @Test
    void contextLoads() {
        double d = 1812190403;
        System.out.println(d);
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

}
