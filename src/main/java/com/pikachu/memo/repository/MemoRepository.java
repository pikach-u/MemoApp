package com.pikachu.memo.repository;

import com.pikachu.memo.model.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Memo> memoRowMapper = (resultSet, rowNum) ->
        new Memo(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("content")
        );

    public List<Memo> findAll(){
        return jdbcTemplate.query(
                "SELECT * FROM memo ORDER BY id DESC",  //최신순(내림차순)으로 조회
                memoRowMapper   //가져온 데이터를 Mapping
        );
    }

    public void save(String title, String content) {
        jdbcTemplate.update(
                "INSERT INTO memo (title, content) VALUES (?, ?)",
                title, content
        );  //DB의 정보 변경. 조회는 Query
    }
}
