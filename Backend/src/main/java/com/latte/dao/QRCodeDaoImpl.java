package com.latte.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.QRCode;
import com.latte.payload.UserInfoUpdateRequest;
import com.latte.payload.UserRoleUpdateRequest;

@Repository
public class QRCodeDaoImpl {

	String ns = "mapper.qrcode."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public int addQRCode(QRCode qrcode) {
		return sqlSession.insert(ns + "addQRCode", qrcode);
	}

	public int deleteQRCode(QRCode qrcode) {
		return sqlSession.delete(ns + "deleteQRCode", qrcode);
	}

	public int isExist(String encryptedCode) {
		return sqlSession.selectOne(ns + "isExist", encryptedCode);
	}

}
