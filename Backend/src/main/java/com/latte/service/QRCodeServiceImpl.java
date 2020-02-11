package com.latte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.QRCodeDaoImpl;
import com.latte.dto.QRCode;

@Service
public class QRCodeServiceImpl implements IQRCodeService {

	@Autowired
	QRCodeDaoImpl qrcodeDao;

	@Override
	@Transactional
	public int addQRCode(QRCode qrcode) {
		return qrcodeDao.addQRCode(qrcode);
	}

	@Override
	@Transactional
	public int deleteQRCode(QRCode qrcode) {
		return qrcodeDao.deleteQRCode(qrcode);
	}

	@Override
	@Transactional(readOnly = true)
	public int isExist(String encryptedCode) {
		return qrcodeDao.isExist(encryptedCode);
	}

}
