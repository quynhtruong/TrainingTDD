package com.qsoft.daoTest;

import com.qsoft.persistence.dao.TransactionDAO;
import com.qsoft.persistence.model.TransactionEntity;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
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
 * Date: 7/16/13
 * Time: 1:44 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TransactionDAOTest
{
    @Autowired
    public TransactionDAO transactionDAO;

    @Autowired
    private DataSource dataSourceTest;

    private IDatabaseTester databaseTester;

    @Before
    public void setup() throws Exception
    {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
        databaseTester = new DataSourceDatabaseTester(dataSourceTest);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception
    {
        databaseTester.onTearDown();
    }

    @Test
    public void testGetAllTransactionByAccountNumber()
    {
        List<TransactionEntity> transactionEntityList = transactionDAO.getTransactionOccurred("0123456781");

        assertEquals(transactionEntityList.size(), 3L);
        assertEquals("0123456781", transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(125), transactionEntityList.get(0).getTimestamp());
        assertEquals(new Double(100), transactionEntityList.get(0).getAmount());
        assertEquals("justATestThirdTime", transactionEntityList.get(0).getDescription());

        assertEquals("0123456781", transactionEntityList.get(2).getAccountNumber());
        assertEquals(new Long(123), transactionEntityList.get(2).getTimestamp());
        assertEquals(new Double(1), transactionEntityList.get(2).getAmount());
        assertEquals("justATest", transactionEntityList.get(2).getDescription());
    }

    @Test
    public void testGetAllAccountByAccountNumberAndAnIntervalOfTime()
    {
        List<TransactionEntity> transactionEntityList = transactionDAO.getTransactionOccurred("0123456781", 124L, 125L);

        assertEquals(transactionEntityList.size(), 2L);
        assertEquals("0123456781", transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(125), transactionEntityList.get(0).getTimestamp());
        assertEquals(new Double(100), transactionEntityList.get(0).getAmount());
        assertEquals("justATestThirdTime", transactionEntityList.get(0).getDescription());

        assertEquals("0123456781", transactionEntityList.get(1).getAccountNumber());
        assertEquals(new Long(124), transactionEntityList.get(1).getTimestamp());
        assertEquals(new Double(10), transactionEntityList.get(1).getAmount());
        assertEquals("justATestSecondTime", transactionEntityList.get(1).getDescription());

    }

    @Test
    public void testSaveATransaction()
    {
        transactionDAO.save("0123456781",126L,1000D,"justATestTracsactionFourthTime");
        List<TransactionEntity> transactionEntityList = transactionDAO.getTransactionOccurred("0123456781", 126L, 126L);

        assertEquals(transactionEntityList.size(),1L);
        assertEquals("0123456781", transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(126), transactionEntityList.get(0).getTimestamp());
        assertEquals(new Double(1000), transactionEntityList.get(0).getAmount());
        assertEquals("justATestTracsactionFourthTime", transactionEntityList.get(0).getDescription());

    }
}
