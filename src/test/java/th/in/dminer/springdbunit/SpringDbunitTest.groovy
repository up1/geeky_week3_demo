package th.in.dminer.springdbunit

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import groovy.sql.Sql
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener

import javax.sql.DataSource

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = [ th.in.dminer.config.TestJdbcConfig.class ])
@TestExecutionListeners([ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class ])
class SpringDbunitTest {
    @Autowired
    DataSource dataSource

    @Test
    public void testWithoutDataset() {
        def sql = new Sql(dataSource)
        def results =  sql.rows("select * from AGENT_DATA")

        assert null != results
        assert 15 == results.size
    }

    @Test
    @DatabaseSetup(value="/dataset/user.xml", type=DatabaseOperation.CLEAN_INSERT)
    public void testCount() {
        def sql = new Sql(dataSource)
        def results =  sql.rows("select * from AGENT_DATA")

        assert null != results
        assert 15 == results.size
    }

    @Test
    @DatabaseSetup(value=["/dataset/user.xml","/dataset/user2.xml"], type=DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value="/dataset/user.xml", type=DatabaseOperation.DELETE_ALL)
    public void testMultiDataset() {
        def sql = new Sql(dataSource)
        def results =  sql.rows("select * from AGENT_DATA")

        assert null != results
        assert 28 == results.size
    }

    @Test
    @DatabaseSetup(value="/dataset/user2.xml", type=DatabaseOperation.CLEAN_INSERT)
    public void testWithCleanInsert() {
        def sql = new Sql(dataSource)
        def results =  sql.rows("select * from AGENT_DATA")

        assert null != results
        assert 13 == results.size
    }


}
