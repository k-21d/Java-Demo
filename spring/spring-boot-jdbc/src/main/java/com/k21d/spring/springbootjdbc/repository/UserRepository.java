package com.k21d.spring.springbootjdbc.repository;

import com.k21d.spring.springbootjdbc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Collection;
import java.util.Collections;

/**
 * 用户的仓储（SQL、NoSQL、或内存）
 */
@Repository
public class UserRepository {
    private final DataSource dataSource;
    private final DataSource masterDataSource;
    private final DataSource slaveDataSource;
    private final JdbcTemplate jdbcTemplate;

    private final PlatformTransactionManager platformTransactionManager;


    /**
     * 构造器注入
     * @param dataSource
     * @param masterDataSource
     * @param slaveDataSource
     * @param jdbcTemplate
     * @param platformTransactionManager
     */
    @Autowired
    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("slaveDataSource") DataSource slaveDataSource, JdbcTemplate jdbcTemplate, PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;
        this.slaveDataSource = slaveDataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }

    public boolean jdbcSave(User user){
        Connection connection = null;
        boolean success = false;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint("T1");
            try {
                transacationSave(user);
            }catch (Exception e){
                connection.rollback(savepoint);
            }
            connection.commit();
            connection.releaseSavepoint(savepoint);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(name) VALUES (?)");
            preparedStatement.setString(1,user.getName());

            success = preparedStatement.executeUpdate()>0;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;
    }

    @Transactional
    public boolean transacationSave(User user){
        boolean success = false;
        success = jdbcTemplate.execute("INSERT INTO user(name) VALUES (?)",
                new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ps.setString(1,user.getName());
                        return ps.executeUpdate()>0;
                    }
                });
        return success;
    }

    public boolean save(User user){
        System.out.printf("[Thread : %s ] save user: %s\n",Thread.currentThread().getName(),user);
        boolean success = false;
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transaction = platformTransactionManager.getTransaction(defaultTransactionDefinition);

        success = jdbcTemplate.execute("INSERT INTO user(name) VALUES (?)",
                new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,user.getName());
                return ps.executeUpdate()>0;
            }
        });
        platformTransactionManager.commit(transaction);
        return success;
    }

    public Collection<User> findAll(){
        return  Collections.emptyList();
    }
}
