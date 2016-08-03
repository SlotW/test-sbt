package SimpleCalcTest;

import SimpleCalc.SimpleCalc;
import FileReader.FileReader;

import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.yandex.qatools.allure.annotations.*;

@Features("�����. ��������")
@Stories(value = { "1" })
@Title(value = "�������� �������������� ��������")
@RunWith(Parameterized.class)
public class SimpleCalcTest {

	private String operand1;
	private String operand2;
	private String operation;
	private String result;
	private static SimpleCalc SC = null;
	
	public SimpleCalcTest(String operand1, String operand2, String operation, String result){
		this.operand1=operand1;
		this.operand2=operand2;
		this.operation=operation;
		this.result=result;
	}
	
	@Parameterized.Parameters
	public static List<String[]> testData(){
		List<String[]> data = FileReader.readFile("src/test/resources/testfile");
		return data;
	}
	
	@BeforeClass
	public static void CreateSC(){
		SC = new SimpleCalc();
	}
	
	@AfterClass
	public static void DestrSC(){
		SC = null;
	}
	
	@Test
	public void Test(){
		
		if(operation.equals("+")){
			
			TestSumm(operand1, operand2, operation, result);
		
		} else if (operation.equals("-")){
			
			TestSubt(operand1, operand2, operation, result);
		
		} else if (operation.equals("*")){
			
			TestMult(operand1, operand2, operation, result);
		
		} else if (operation.equals("/")){
			
			TestDiv(operand1, operand2, operation, result);
			
		} else {
			
			UnknowOperation(operation);
		}
	}

	@Step("�������� ����� {0} {2} {1} = {3}")
	public void TestSumm(String x, String y, String oper, String res){
		int resSumm = SC.Summ(Integer.parseInt(x),Integer.parseInt(y));
		assertEquals("��������� ����� "+x+oper+y+"="+resSumm+", � ������ ���� "+res, Integer.parseInt(res), resSumm);
	}
	
	@Step("�������� �������� {0} {2} {1} = {3}")
	public void TestSubt(String x, String y, String oper, String res){
		int resSubt = SC.Subt(Integer.parseInt(x),Integer.parseInt(y));
		assertEquals("��������� ��������� "+x+oper+y+"="+resSubt+", � ������ ���� "+res, Integer.parseInt(res), resSubt);
	}
	
	@Step("�������� ��������� {0} {2} {1} = {3}")
	public void TestMult(String x, String y, String oper, String res){
		int resMult = SC.Mult(Integer.parseInt(x),Integer.parseInt(y));
		assertEquals("��������� ��������� "+x+oper+y+"="+resMult+", � ������ ���� "+res, Integer.parseInt(res), resMult);
	}
	
	@Step("�������� ������� {0} {2} {1} = {3}")
	public void TestDiv(String x, String y, String oper, String res){
		
		if(Integer.parseInt(y) == 0){
				
			TestDivByZero(x, y, oper, res);
			
		} else {
			
			try{
				
				int resDiv = SC.Div(Integer.parseInt(x),Integer.parseInt(y));
				assertEquals("��������� ������� "+x+oper+y+"="+resDiv+", � ������ ���� "+res, Integer.parseInt(res), resDiv);
			
			}  catch(ArithmeticException ex) {
				
				Assert.assertFalse("����������� ������� "+x+oper+y+" ����� ����������, � ������ ���� "+res,true);
				
			}
			
		}
	}
	
	@Step("�������� ������� �� ����. {0}{2}{1}={3}")
	public void TestDivByZero(String x, String y, String oper, String res){
		
		try {
			
			int resDiv = SC.Div(Integer.parseInt(x),Integer.parseInt(y));
			Assert.assertFalse("��������� ������� "+x+oper+y+"="+resDiv+", � ������ ���� ���������� - ������� �� ����",true);
			
		} catch(ArithmeticException ex) {
		
            assertEquals(res, "/0");
		
		}
		
	}
	
	@Step("����������� �������� {0}, �������� ��������")
	public void UnknowOperation(String message){
		Assume.assumeTrue(false);
	}
	
}
