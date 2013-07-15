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
 * Date: 7/15/13
 * Time: 1:21 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TransactionDAOTest
{
    @Autowired
    private TransactionDAO transactionDAO;
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
    public void testGetTransactionByAccountNumber()
    {
        List<TransactionEntity> transactionEntityList =  transactionDAO.getTransactionOccurred("0123456781");

        assertEquals(3,transactionEntityList.size());

        assertEquals("0123456781",transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(125),transactionEntityList.get(0).getTimestamp());
        assertEquals(100D,transactionEntityList.get(0).getAmount());
        assertEquals("justATestThirdTime",transactionEntityList.get(0).getDescription());

        assertEquals("0123456781",transactionEntityList.get(2).getAccountNumber());
        assertEquals(new Long(123),transactionEntityList.get(2).getTimestamp());
        assertEquals(1D,transactionEntityList.get(2).getAmount());
        assertEquals("justATest",transactionEntityList.get(2).getDescription());
    }

    @Test
    public void testGetTransactionByAccountNumberAndAnIntervalOfTime()
    {
        List<TransactionEntity> transactionEntityList = transactionDAO.getTransactionOccurred("0123456781",1L,124L);
        assertEquals(2,transactionEntityList.size());

        assertEquals("0123456781",transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(124),transactionEntityList.get(0).getTimestamp());
        assertEquals(10D,transactionEntityList.get(0).getAmount());
        assertEquals("justATestSecondTime",transactionEntityList.get(0).getDescription());

        assertEquals("0123456781",transactionEntityList.get(1).getAccountNumber());
        assertEquals(new Long(123),transactionEntityList.get(1).getTimestamp());
        assertEquals(1D,transactionEntityList.get(1).getAmount());
        assertEquals("justATest",transactionEntityList.get(1).getDescription());
    }

    @Test
    public void testSaveTransation()
    {
        transactionDAO.save("0123456790",1234L,111D,"JustATestForSaveTransaction");
        List<TransactionEntity> transactionEntityList =  transactionDAO.getTransactionOccurred("0123456790");

        assertEquals("0123456790",transactionEntityList.get(0).getAccountNumber());
        assertEquals(new Long(1234),transactionEntityList.get(0).getTimestamp());
        assertEquals(111D,transactionEntityList.get(0).getAmount());
        assertEquals("JustATestForSaveTransaction",transactionEntityList.get(0).getDescription());

    }

}
