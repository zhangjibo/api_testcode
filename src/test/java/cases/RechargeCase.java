package cases;

import java.math.BigDecimal;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONPath;

import pojo.API;
import pojo.Case;
import utils.DataUtils;

public class RechargeCase extends BaseCase {
    
    @BeforeSuite
    public void init() {
        
        super.init();
    }

    @Test(dataProvider = "data", description = "充值测试")
    public void test(API api, Case c) {

	super.test(api, c);
    }

    @Override
    public boolean assertSql(Object beforeRequestSqlCheck, Object afterRequestSqlCheck, Case c) {
	
	String amountStr = JSONPath.read(c.getParams(), "$.amount").toString();
	System.out.println(amountStr);
	System.out.println((BigDecimal)beforeRequestSqlCheck);
	System.out.println((BigDecimal) afterRequestSqlCheck);
	String subtract = ((BigDecimal) afterRequestSqlCheck).subtract((BigDecimal)beforeRequestSqlCheck).stripTrailingZeros().toPlainString();
	System.out.println(subtract);
	return subtract.equals(amountStr);
    }

    @AfterSuite
    public void finish() {

	super.finish();
    }

    @DataProvider(name = "data")
    public Object[][] data() throws Exception {
	Object[][] data = DataUtils.getCaseByApiId("3");
	return data;
    }
    

}
