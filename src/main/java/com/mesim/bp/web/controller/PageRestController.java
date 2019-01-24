package com.mesim.bp.web.controller;

import com.mesim.bp.message.ResponseMessage;
import com.mesim.bp.web.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created by MisunKim
 * Date: 2019-01-24 오후 2:01
 */
@RequestMapping("/rest")
@RestController
public class PageRestController {

    static final Logger logger = LoggerFactory.getLogger(PageRestController.class);

    @Autowired
    BoardService service;

    @RequestMapping(value = "/getBoardList", method = RequestMethod.GET)
    public ResponseMessage getBoardList() {

        ResponseMessage res = new ResponseMessage();
        try {
            res.setData(service.getBoardList());
            res.setSuccess(true);

        }catch (SQLException e) {
            logger.error("게시판 조회 중 오류 발생", e);
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping(value = "/getDetailInfo", method = RequestMethod.GET)
    public ResponseMessage getDetailInfo(@RequestParam String id) {

        return new ResponseMessage(null, true);
    }
}
