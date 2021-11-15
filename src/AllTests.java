import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_Analyze_Data_Write_JSON_File.class, Test_Read_From_Category_ReST_API.class,
		Test_Read_From_Document_ReST_API.class })
public class AllTests {

}
