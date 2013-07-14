package th.in.dminer.dbunit;


import groovy.sql.Sql
import org.dbunit.IDatabaseTester
import org.dbunit.JdbcDatabaseTester
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder
import org.dbunit.operation.DatabaseOperation
import org.h2.engine.Constants
import org.h2.tools.RunScript
import org.junit.BeforeClass
import org.junit.Test


public class DbUnitBaseTest {
    private static final String JDBC_DRIVER = "org.h2.Driver"
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=Oracle";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static final String resourcesPath = new File("src/test/resources").absolutePath


    @BeforeClass
    public static void setupBeforeClass() {
        RunScript.execute(JDBC_URL, USER, PASSWORD, "$resourcesPath/dataset/schema.sql", Constants.UTF8, false)
    }

    public void importDataSet(String resource) throws Exception {
        IDataSet dataSet = readDataSet(resource)
        cleanlyInsert(dataSet)
    }

    private IDataSet readDataSet(String resource) throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("$resourcesPath/$resource"))
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void testCount() {
        importDataSet("dataset/user.xml")

        def db = [url:'jdbc:h2:mem:test', user:'sa', password:'', driver:'org.h2.Driver']
        def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
        def results =  sql.rows("select * from AGENT_DATA")

        assert null != results
        assert 15 == results.size
    }
}
