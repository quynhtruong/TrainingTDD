package com.qsoft.DAOTest;

import com.qsoft.persistence.dao.TransactionDAO;
import com.qsoft.persistence.model.TransactionEntity;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/11/13
 * Time: 7:44 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TransactionDAOTest
{
    @Autowired
    TransactionDAO transactionDAO;
    @Autowired
    private DataSource dataSourceTest;

    private IDatabaseTester databaseTester;

    @Before
    public void setup() throws Exception
    {
        IDataSet dataSet = readDataSet();  // read data from xml file
        cleanlyInsert(dataSet);  // empty the db and insert data
    }

    public IDataSet readDataSet() throws Exception
    {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception
    {
        databaseTester = new DataSourceDatabaseTester(dataSourceTest);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void testGetAllTransactionByAccountNumber()
    {
        List<TransactionEntity> transactionEntityList = transactionDAO.getTransactionOccurred("0123456781");
        assertEquals(3, transactionEntityList.size());

        assertEquals("0123456781", transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(123), transactionEntityList.get(0).getTimestamp());
        assertEquals(1D, transactionEntityList.get(0).getAmount());
        assertEquals("justATest", transactionEntityList.get(0).getDescription());

        assertEquals("0123456781", transactionEntityList.get(2).getAccountNumber());
        assertEquals(new Long(123), transactionEntityList.get(2).getTimestamp());
        assertEquals(100D, transactionEntityList.get(2).getAmount());
        assertEquals("justATestThirdTime", transactionEntityList.get(2).getDescription());

    }

    @Test
    public void testSaveTransaction()
    {
        transactionDAO.save("123456789", 1234L, 111D, "testTransaction");
        List<TransactionEntity> transactionEntityList = transactionDAO.getTransactionOccurred("123456789");

        assertEquals("123456789", transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(1234), transactionEntityList.get(0).getTimestamp());
        assertEquals(111D, transactionEntityList.get(0).getAmount());
        assertEquals("testTransaction", transactionEntityList.get(0).getDescription());
    }

    @Test
    public void testGetTransactionInAPeridOfTime()
    {
        List<TransactionEntity> transactionEntityList = transactionDAO.getTransactionOccurred("0123456781", 1L, 10000L);

        assertEquals("0123456781", transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(123), transactionEntityList.get(0).getTimestamp());
        assertEquals(1D, transactionEntityList.get(0).getAmount());
        assertEquals("justATest", transactionEntityList.get(0).getDescription());

        assertEquals("0123456781", transactionEntityList.get(2).getAccountNumber());
        assertEquals(new Long(123), transactionEntityList.get(2).getTimestamp());
        assertEquals(100D, transactionEntityList.get(2).getAmount());
        assertEquals("justATestThirdTime", transactionEntityList.get(2).getDescription());
    }

    @Test
    public void testGetNCloesetTransactins()
    {
        List<TransactionEntity> transactionEntityList = transactionDAO.getNClosestTransactions("0123456781",2L);
        assertEquals("0123456781", transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(123), transactionEntityList.get(0).getTimestamp());
        assertEquals(1D, transactionEntityList.get(0).getAmount());
        assertEquals("justATest", transactionEntityList.get(0).getDescription());

        assertEquals("0123456781", transactionEntityList.get(1).getAccountNumber());
        assertEquals(new Long(123), transactionEntityList.get(1).getTimestamp());
        assertEquals(10D, transactionEntityList.get(1).getAmount());
        assertEquals("justATestSecondTime", transactionEntityList.get(1).getDescription());

    }
}
