package com.mesim.bp.web.service;

import com.mesim.bp.db.mapper.BoardMapper;
import com.mesim.bp.web.dto.BoardData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MisunKim
 * Date: 2019-01-24 오후 2:23
 *
 * 게시판 조회 서비스
 */
@Service
public class BoardService {
    @Autowired
    BoardMapper mapper;

    public List<BoardData> getBoardList () throws SQLException{
        return mapper.getBoardList();
    }
}
