package com.coworking.hotdesk.dao;

import com.coworking.hotdesk.dao.mapper.UserRowMapper;
import com.coworking.hotdesk.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDao {

    private static final String SELECT_ALL_COLUMNS =
            "SELECT user_id, full_name, email, wallet_balance FROM users";

    private static final String INSERT =
            "INSERT INTO users (full_name, email, wallet_balance) VALUES (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper = new UserRowMapper();

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(String fullName, String email, BigDecimal initialBalance) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setBigDecimal(3, initialBalance);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Optional<User> findById(int userId) {
        return jdbcTemplate.query(SELECT_ALL_COLUMNS + " WHERE user_id = ?", rowMapper, userId)
                .stream().findFirst();
    }

    /** Row-locks the member (SELECT ... FOR UPDATE) so concurrent wallet changes are serialized. */
    public Optional<User> findByIdForUpdate(int userId) {
        return jdbcTemplate.query(SELECT_ALL_COLUMNS + " WHERE user_id = ? FOR UPDATE", rowMapper, userId)
                .stream().findFirst();
    }

    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL_COLUMNS + " ORDER BY user_id", rowMapper);
    }

    /** Guarded debit: succeeds only if the balance is sufficient. Returns true when a row was updated. */
    public boolean debit(int userId, BigDecimal amount) {
        int rows = jdbcTemplate.update(
                "UPDATE users SET wallet_balance = wallet_balance - ? WHERE user_id = ? AND wallet_balance >= ?",
                amount, userId, amount);
        return rows == 1;
    }

    public boolean credit(int userId, BigDecimal amount) {
        int rows = jdbcTemplate.update(
                "UPDATE users SET wallet_balance = wallet_balance + ? WHERE user_id = ?",
                amount, userId);
        return rows == 1;
    }
}
